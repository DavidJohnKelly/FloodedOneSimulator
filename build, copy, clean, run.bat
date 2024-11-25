@echo off
set source=C:\Users\wwwku\OneDrive - The University of Nottingham\Year 4\Semester 1\COMP4032 - Advanced Computer Networks\Coursework\The ONE\the-one-1.6.0\out\production\the-one-1.6.0
set destination=C:\Users\wwwku\OneDrive - The University of Nottingham\Year 4\Semester 1\COMP4032 - Advanced Computer Networks\Coursework\The ONE\the-one-1.6.0

robocopy "%source%" "%destination%" /E /XC

echo Files copied successfully.

rmdir "C:\Users\wwwku\OneDrive - The University of Nottingham\Year 4\Semester 1\COMP4032 - Advanced Computer Networks\Coursework\The ONE\the-one-1.6.0\out" /S /Q

echo Folder deleted successfully.

call "C:\Users\wwwku\OneDrive - The University of Nottingham\Year 4\Semester 1\COMP4032 - Advanced Computer Networks\Coursework\The ONE\the-one-1.6.0\one.bat" flood_test_settings.txt

echo one.bat has been executed.

