/*
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.gadgets.sample.traveler.server;

import net.oauth.OAuth;
import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthException;
import net.oauth.OAuthMessage;
import net.oauth.OAuthProblemException;
import net.oauth.SimpleOAuthValidator;
import net.oauth.server.OAuthServlet;
import net.oauth.signature.RSA_SHA1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filters out the signed requests from opensocial containers.
 */
public class AuthenticationFilter implements Filter {

  private static final Logger log = Logger.getLogger(AuthenticationFilter.class.getName());

  private static CertificateProvider certificateProvider = CertificateProvider.get();

  public void destroy() {
  }

  public void doFilter(ServletRequest req, ServletResponse resp,
      FilterChain chain) throws IOException, ServletException {
    if (req instanceof HttpServletRequest
        && resp instanceof HttpServletResponse) {
      if (isValid((HttpServletRequest) req)) {
        chain.doFilter(req, resp);
      } else {
        HttpServletResponse response = (HttpServletResponse) resp;
        response.setStatus(401);
        response.getWriter().print("Authentication failed");
      }
    } else {
      log.warning("Unexpected: Not HTTP request");
    }
  }

  public void init(FilterConfig arg0) {
  }

  @SuppressWarnings("unchecked")
  private List<OAuth.Parameter> getRequestParameters(HttpServletRequest request) {
    List<OAuth.Parameter> parameters = new ArrayList<OAuth.Parameter>();
    for (Object e : request.getParameterMap().entrySet()) {
      Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) e;
      for (String value : entry.getValue()) {
        parameters.add(new OAuth.Parameter(entry.getKey(), value));
      }
    }
    return parameters;
  }

  private boolean isValid(HttpServletRequest request) {
    try {
      String consumerKey = request.getParameter("oauth_consumer_key");

      String certificate = certificateProvider.getCertificate(consumerKey);
      if (certificate == null) {
        return false;
      }

      OAuthConsumer consumer = new OAuthConsumer(null, consumerKey, null, null);

      consumer.setProperty(RSA_SHA1.X509_CERTIFICATE, certificate);

      OAuthMessage message = new OAuthMessage(request.getMethod(),
          request.getRequestURL().toString(), getRequestParameters(request));

      OAuthAccessor accessor = new OAuthAccessor(consumer);

      SimpleOAuthValidator validator = new SimpleOAuthValidator();
      validator.validateMessage(message, accessor);
      return true;
    } catch (OAuthProblemException ope) {
      log.warning(OAuthServlet.htmlEncode(ope.getProblem()));
      log.warning(ope.toString());
    } catch (OAuthException e) {
      log.warning(e.toString());
    } catch (IOException e) {
      log.warning(e.toString());
    } catch (URISyntaxException e) {
      log.warning(e.toString());
    }
    return false;
  }
}
