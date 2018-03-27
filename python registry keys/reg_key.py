from _winreg import *


def write_registry_key(valname,val):
   aReg = ConnectRegistry(None,HKEY_LOCAL_MACHINE)                                               
   aKey = OpenKey(aReg, r"SOFTWARE\", 0, KEY_WRITE)
   try:
      SetValueEx(aKey,valname,0, REG_SZ,val) 
   except EnvironmentError:
      print "Encountered problems writing into the Registry..."
   CloseKey(aKey)
   CloseKey(aReg)     
   
# Write a registry key given a hkey, path, valuename, and a value
def write_registry_key_with_path(hkey, path, valname, val):
   aReg = ConnectRegistry(None, eval(hkey))                                          
   aKey = CreateKey(aReg, path)
   try:
      SetValueEx(aKey,valname,0, REG_SZ,val) 
   except EnvironmentError:
      print "Encountered problems writing into the Registry..."
   CloseKey(aKey)
   CloseKey(aReg) 

