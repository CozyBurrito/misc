import subprocess, sys

cmd = "powershell.exe Get-WmiObject -Class win32_logicalDisk -ComputerName "
cmd_out = subprocess.run(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
output = cmd_out.stdout.decode()
err = cmd_out.stderr.decode()

if output:
    print (output)
else:
    print (err)
