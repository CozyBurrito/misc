
USE 

SET NOCOUNT ON

BEGIN TRANSACTION;


DECLARE @AGName VARCHAR(40)
DECLARE @AGID INT
DECLARE @AdminUserName VARCHAR(40)
DECLARE @AdminUserID INT
DECLARE @AdminPassword VARCHAR(40)
DECLARE @Permission VARCHAR(100)
DECLARE @DGID INT

CREATE TABLE #TEMP1 (PermissionName VARCHAR(50))


SET @AdminUserName = ''
SET @AdminPassword = ''


SELECT @AGName = UserGroupName, @AGID = UserGroupID FROM UGroups WHERE IsAG = 'True'

IF NOT EXISTS (SELECT * FROM Us WHERE UName = @AdminUserName)
	INSERT INTO Users (UserName, BasicPassword)	VALUES(@AdminUserName, @AdminPassword)

SELECT @AdminUserID = UserID FROM Users WHERE UserName = @AdminUserName

IF NOT EXISTS (SELECT * FROM UserGroupMembership WHERE UserID = @AdminUserID AND UserGroupID = @AGID)
	EXEC Link @AGID, @AGID


INSERT INTO #TEMP1 (PermissionName) Values('Alerts') 
INSERT INTO #TEMP1 (PermissionName) Values('Status')
...


--Add permissions 
DECLARE DG_Cursor CURSOR FOR SELECT DGID FROM DG WHERE DGID <> 0
OPEN DG_Cursor
	FETCH NEXT FROM DG_Cursor INTO @DGID
	WHILE @@FETCH_STATUS = 0
		BEGIN 

		IF EXISTS (SELECT * FROM AccessControl WHERE UserGroupID = @AGID AND DGID = @DGID)
			Update AccessControl SET Cl = 1, PLevel = '', LID = 0 WHERE ID = @AGID AND DGID = @DGID
		ELSE
		INSERT INTO AC (UGID, DGID, Cl, AB, PLevel, LID)
			VALUES(@AGID, @DGID, 2, '', '',1)
		
		DECLARE Permission_Cursor CURSOR FOR SELECT * FROM #TEMP1
		OPEN Permission_Cursor
			FETCH NEXT FROM Permission_Cursor INTO @Permission
			WHILE @@FETCH_STATUS = 0
				BEGIN 
					IF NOT EXISTS (SELECT * FROM Permissions WHERE UID = @AGID AND TT = 'DG' AND TId = @DGID AND P = @Permission)
					INSERT INTO Permissions (UID, TT, TId, P, AB)
						VALUES (@AGID,'DG', @DGID, @Permission, '')

					FETCH NEXT FROM Permission_Cursor INTO @Permission
				END
		CLOSE Permission_Cursor
		DEALLOCATE Permission_Cursor
					
			FETCH NEXT FROM DG_Cursor INTO @DGID
		END
CLOSE DG_Cursor
DEALLOCATE DG_Cursor

DELETE FROM Permissions WHERE Permission = ''			
DROP TABLE #TEMP1

COMMIT;

SET NOEXEC OFF;