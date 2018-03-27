import os, shutil, datetime, time

src = '0110000104a5ddc0'
dsts = ['backup1', 'backup2', 'backup3', 'backup4', 'backup5', 'backup6', 'backup7', 'backup8', 'backup9', 'backup10']

while(1):
	for dst in dsts:
		file = os.listdir(src)
		fileStr = ''.join(file)
		filePath = os.path.join(src, fileStr)
		
		
		print("Copying", filePath, "to", os.path.join(dst, fileStr), "at", datetime.datetime.now())
		shutil.copy(filePath, dst)
	
		time.sleep(45)
