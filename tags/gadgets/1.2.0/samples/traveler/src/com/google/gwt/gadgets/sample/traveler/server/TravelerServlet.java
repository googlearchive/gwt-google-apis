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

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet exposing user's {@link Location}s and providing methods to modify
 * them.
 */
@SuppressWarnings("serial")
public class TravelerServlet extends HttpServlet {

  private static class PMF {
    private static final PersistenceManagerFactory pmfInstance = //
    JDOHelper.getPersistenceManagerFactory("transactions-optional");

    public static synchronized PersistenceManager getPersistenceManager() {
      return pmfInstance.getPersistenceManager();
    }

    private PMF() {
    }
  }

  private static final String OWNER_ID_KEY = "opensocial_owner_id";
  private static final String CONTAINER_KEY = "opensocial_container";

  /**
   * DELETE request with <code>key</code> parameter deletes persisted location
   * with provided key. Location will not be deleted unless request is sent by
   * the owner of the location.
   */
  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    PersistenceManager pm = PMF.getPersistenceManager();
    try {
      String ownerId = req.getParameter(OWNER_ID_KEY);
      String container = req.getParameter(CONTAINER_KEY);
      String key = req.getParameter("key");

      Location loc = pm.getObjectById(Location.class, key);
      if (loc != null) {
        if (container.equals(loc.getContainer())
            && ownerId.equals(loc.getOwnerId())) {
          pm.deletePersistent(loc);
        } else {
          resp.setStatus(401);
          resp.getWriter().print("Operation not authorized");
        }
      } else {
        resp.setStatus(403);
        resp.getWriter().print("No object to delete");
      }
    } finally {
      pm.close();
    }
  }

  /**
   * GET request with <code>userId</code> parameter returns the list of
   * locations stored for user. If <code>userId</code> parameter is not
   * provided, locations of the owner of the gadget will be returned.
   */
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {

    PersistenceManager pm = PMF.getPersistenceManager();
    try {
      String ownerId = req.getParameter(OWNER_ID_KEY);
      String container = req.getParameter(CONTAINER_KEY);
      String userId = req.getParameter("userId");
      if (userId == null) {
        userId = ownerId;
      }

      Query q = pm.newQuery(Location.class);
      q.setFilter("ownerId == ownerIdParam && container == containerParam");
      q.declareParameters("String ownerIdParam, String containerParam");
      String json;
      try {
        @SuppressWarnings("unchecked")
        List<Location> result = (List<Location>) q.execute(userId, container);
        json = Encoder.locationsAsJson(result, ownerId.equals(userId));
      } finally {
        q.closeAll();
      }
      resp.getWriter().append(json);
    } finally {
      pm.close();
    }
  }

  /**
   * POST request with {@link Location} object encoded in post-data persists
   * provided location for the owner of the gadget.
   */
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    PersistenceManager pm = PMF.getPersistenceManager();
    try {
      // comments to avoid eclipse formatting
      Location location = new Location(//
          req.getParameter(OWNER_ID_KEY), //
          req.getParameter(CONTAINER_KEY), //
          req.getParameter("title"), //
          req.getParameter("description"), //
          new Date(Long.parseLong(req.getParameter("milis"))), //
          Double.parseDouble(req.getParameter("latitude")), //
          Double.parseDouble(req.getParameter("longitude")));

      pm.makePersistent(location);
    } catch (NumberFormatException e) {
      resp.setStatus(400);
      resp.getWriter().print("Unable to parse request");
    } finally {
      pm.close();
    }
  }
}
