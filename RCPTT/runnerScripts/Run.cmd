@echo off
REM *
REM * The XXX_VM_ARGS are aligned and optimzed on the running jdk 8.
REM *
SET PATH=C:\opt\java\openjdk-11.0.2\bin;%PATH%
IF "x%WORKSPACE%" == "x" SET WORKSPACE=%~dp0..\..\..
SET AUT=C:\opt\eclipse\eclipse-2020-09\eclipse-poosl
SET AUT_VM_ARGS=-Xms1024m;-Xmx1024m;-XX:MaxMetaspaceSize=256m
SET RUNNER=C:\opt\rcptt\rcptt.runner-2.5.0
SET RUNNER_VM_ARGS=-Xms512m -Xmx512m -XX:MaxMetaspaceSize=128m
SET TESTS=%WORKSPACE%\poosl\RCPTT
SET POOSL_PLUGINS=%WORKSPACE%\poosl\nl.esi.poosl.updatesite\target\repository
SET RESULTS=%WORKSPACE%\results\run-tests

REM Remove results dir if present
IF EXIST "%RESULTS%" RMDIR /S /Q "%RESULTS%"
COPY /S /E /I /Y "%WORKSPACE%\poosl\RCPTT\runnerScripts\aut-runner-workspace" "%RESULTS%\runner-workspace"

java %RUNNER_VM_ARGS% -jar "%RUNNER%\plugins\org.eclipse.equinox.launcher_1.5.600.v20191014-2022.jar" ^
-application org.eclipse.rcptt.runner.headless ^
-data "%RESULTS%/runner-workspace/" ^
-aut "%AUT%" ^
-autVMArgs "%AUT_VM_ARGS%" ^
-autWsPrefix "%RESULTS%/aut-workspace" ^
-autConsolePrefix "%RESULTS%/aut-output" ^
-htmlReport "%RESULTS%/report.html" ^
-junitReport "%RESULTS%/report.html" ^
-import "%TESTS%\nl.esi.poosl.rcptt;%TESTS%\nl.esi.poosl.rcptt.run" ^
-injection:dir "%POOSL_PLUGINS%" ^
-suites "Run" ^
-connectTimeout 240 ^
-testOptions "testExecTimeout=240"