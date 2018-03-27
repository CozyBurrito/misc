ECHO OFF
CLS

REM --------------- VARIABLES ---------------

SET VERSION=470
SET PREV_VERSION=460

SET DB_SERVER=localhost
SET DB_NAME=
SET USER_NAME=sa
SET USER_PASSWORD=12345

SET SCRIPT_CREATE=create.sql
SET SCRIPT_MIGRATE=migrate.sql
SET SCRIPT_FUNCTIONS=functions.sql
SET SCRIPT_DATA=data.sql
SET SCRIPT_SCRIPTS=scripts.sql
SET SCRIPT_QUERIES=queries.sql

SET HAS_SCRIPTS=True

REM --------------- GET INPUT ---------------

SET /p ACTION=DB ACTION [create/migrate]?:

IF NOT %ACTION% == create (
    IF NOT %ACTION% == migrate (
        ECHO ERROR!! INVALID INPUT PLEASE TRY AGAIN
        PAUSE
        EXIT
    )
)

REM --------------- DROP TABLE IF CREATE ---------------

IF %ACTION% == create (
    ECHO WARNING!! This will drop the %DB_NAME% database!
    PAUSE
    ECHO.

    ECHO Dropping %DB_NAME% Database ...
    sqlcmd.exe -S %DB_SERVER% -Q "DROP DATABASE %DB_NAME%" -U %USER_NAME% -P %USER_PASSWORD% -o OUTPUT.log
    type OUTPUT.log
    ECHO %DB_NAME% Database Dropped!
)
ECHO.

REM --------------- CHECK FOR SCRIPT FILES EXISTANCE ---------------

IF %ACTION% == create (
    FOR %%i IN (%SCRIPT_CREATE%, %SCRIPT_FUNCTIONS%, %SCRIPT_DATA%, %SCRIPT_SCRIPTS%, %SCRIPT_QUERIES%) DO (
        IF NOT EXIST %%i (
            ECHO ERROR!! %%i not found in current directory!
            SET HAS_SCRIPTS=False 
        )
    )
) ELSE (
    FOR %%i IN (%SCRIPT_MIGRATE%, %SCRIPT_FUNCTIONS%, %SCRIPT_SCRIPTS%, %SCRIPT_QUERIES%) DO (
        IF NOT EXIST %%i (
            ECHO ERROR!! %%i not found in current directory!
            SET HAS_SCRIPTS=False 
        )
    )
)

IF %HAS_SCRIPTS% == False (
    PAUSE
    EXIT
) ELSE (
    ECHO Ready to run scripts
)
PAUSE
ECHO.

REM --------------- EXECUTE SCRIPTS ---------------

IF %ACTION% == create (
    ECHO Running create ...
    sqlcmd.exe -S %DB_SERVER% -i %SCRIPT_CREATE% -U %USER_NAME% -P %USER_PASSWORD% -o OUTPUT.log
    type OUTPUT.log
) ELSE IF %ACTION% == migrate (
    ECHO Running migrate ...
    sqlcmd.exe -S %DB_SERVER% -i %SCRIPT_MIGRATE% -U %USER_NAME% -P %USER_PASSWORD% -o OUTPUT.log
    type OUTPUT.log
)

ECHO Running %VERSION% functions ...
sqlcmd.exe -S %DB_SERVER% -i %SCRIPT_FUNCTIONS% -U %USER_NAME% -P %USER_PASSWORD% -o OUTPUT.log
type OUTPUT.log

IF %ACTION% == create (
    ECHO Running initial data ...
    sqlcmd.exe -S %DB_SERVER% -i %SCRIPT_DATA% -U %USER_NAME% -P %USER_PASSWORD% -o OUTPUT.log
    type OUTPUT.log
)

ECHO Running scripts ...
sqlcmd.exe -S %DB_SERVER% -i %SCRIPT_SCRIPTS% -U %USER_NAME% -P %USER_PASSWORD% -o OUTPUT.log
type OUTPUT.log

ECHO Running client queries ...
sqlcmd.exe -S %DB_SERVER% -i %SCRIPT_QUERIES% -U %USER_NAME% -P %USER_PASSWORD% -o OUTPUT.log
type OUTPUT.log


ECHO.
ECHO Scripts Executed! 
ECHO EOF
PAUSE