@if "%GWT_HOME%"=="" goto needGWTHome
@java -Xmx256M -cp "%~dp0\..\..\src;%~dp0\..\..\war\WEB-INF\classes;%GWT_HOME%\gwt-user.jar;%GWT_HOME%\gwt-dev-windows.jar;%GWT_HOME%\gwt-dev.jar;..\..\..\..\gwt-ajaxloader.jar" com.google.gwt.dev.Compiler -war "%~dp0\..\..\war" %* com.google.gwt.ajaxloader.sample.helloajaxloader.HelloAjaxLoader
@exit /B %ERRORLEVEL%

:needGWTHome
@echo The environment variable GWT_HOME is not defined, it should point to a valid GWT installation.
@exit /B 1
