call runcrud.bat
if "%ERRORLEVEL%" == "0" goto runchrome
echo.
echo runcrud.bat has errors
goto fail

:runchrome
start http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Cannot open browser.

:fail
echo.
echo There were errors.

:end
echo.
echo Work is finished.