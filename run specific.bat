@echo off
setlocal enabledelayedexpansion

:: Create necessary directories if they don't exist
if not exist "reports\spray" mkdir "reports\spray"
if not exist "reports\epidemic" mkdir "reports\epidemic"

:: Define spray scenario files
set spray_files=scenario_settings\smallflood_densepopulation_spray.txt scenario_settings\smallflood_sparsepopulation_spray.txt

:: Run spray scenarios and move reports
echo Running spray scenarios...
for %%f in (%spray_files%) do (
    echo Running %%f...
    call one.bat -b 1 %%f
    echo Moving reports for %%f...
    for %%r in (reports\*.txt) do (
        move %%r reports\spray\
    )
)

:: Run epidemic scenarios and move reports
set epidemic_files=scenario_settings\smallflood_sparsepopulation_epidemic.txt
echo Running epidemic scenarios...
for %%f in (%epidemic_files%) do (
    echo Running %%f...
    call one.bat -b 1 %%f
    echo Moving reports for %%f...
    for %%r in (reports\*.txt) do (
        move %%r reports\epidemic\
    )
)

echo All scenarios have been run and reports moved.
endlocal
