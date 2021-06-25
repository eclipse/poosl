@echo off
SET dir=%~dp0
SET type=%1

@echo on
winscp.exe /console /ini=nul /script=%dir%winscpScript.txt /log=%dir%log.txt