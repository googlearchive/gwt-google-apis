@if "%GWT_HOME%"=="" goto needGWTHome
@java -Xmx256m -cp "%~dp0\..\..\src;%~dp0\..\..\bin;%GWT_HOME%\gwt-user.jar;%GWT_HOME%\gwt-dev-windows.jar;..\..\..\..\gwt-visualization.jar" com.google.gwt.dev.GWTShell -out "%~dp0\..\..\www" %* com.google.gwt.visualization.customvisualization.CustomVisualization/CustomVisualization.html
@exit /B %ERRORLEVEL%

:needGWTHome
@echo The environment variable GWT_HOME is not defined, it should point to a valid GWT installation.
@exit /B 1
