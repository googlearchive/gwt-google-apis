@if "%GWT_HOME%"=="" goto needGWTHome

@REM The name of the shell changed in GWT 2.0
@if exist "%GWT_HOME%/gwt-dev.jar" (
  set MAIN="DevMode"
) else (
  set MAIN="HostedMode"
)

@java -Xmx256m -cp "%~dp0\..\..\src;%dp0\..\..\war\WEB-INF\classes;%GWT_HOME%\gwt-user.jar;%GWT_HOME%\gwt-dev-windows.jar;%GWT_HOME%\gwt-dev.jar;..\..\..\..\gwt-ajaxloader.jar" com.google.gwt.dev.%MAIN% -war "%~dp0\..\..\war" %* -startupUrl HelloAjaxLoader.html com.google.gwt.ajaxloader.sample.helloajaxloader.HelloAjaxLoader
@exit /B %ERRORLEVEL%

:needGWTHome
@echo The environment variable GWT_HOME is not defined, it should point to a valid GWT installation.
@exit /B 1
