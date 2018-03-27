
-- Create TEMP table and insert values
IF OBJECT_ID('tempdb..#TempTestTable') IS NOT NULL DROP TABLE #TempTestTable
CREATE TABLE #TempTestTable 
(
	TableName VARCHAR(120) NULL
)

INSERT INTO #TempTestTable (TableName) VALUES ('TEST'), ('TEST2')


-- Create CURSOR to iterate through TEMP table
DECLARE @tablename VARCHAR(120)
DECLARE @insertString VARCHAR(max)

DECLARE TableCursor CURSOR FOR SELECT * FROM #TempTestTable
OPEN TableCursor
FETCH NEXT FROM TableCursor INTO @tablename
WHILE @@FETCH_STATUS = 0
BEGIN

	SET @insertString = 'DECLARE @TEMP AS ' + @tablename + 'TableType
	INSERT INTO @TEMP SELECT * FROM ' + @tablename + '
	SELECT * FROM dbo.Convert'+ @tablename +'TableToRecords(@TEMP)'

	print @insertString

	EXECUTE(@insertString)


	FETCH NEXT FROM TableCursor INTO @tablename
END
CLOSE TableCursor
DEALLOCATE TableCursor
