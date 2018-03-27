import os, re, time, subprocess, datetime
from xml.etree.ElementTree import Element, SubElement, Comment
from xml.etree import ElementTree
from xml.dom import minidom

""" Globals """
filename = "live_feed.xml"
vm_list = 

""" Make XML look pretty """
def prettify(elem):
    rough_string = ElementTree.tostring(elem, 'utf-8')
    reparsed = minidom.parseString(rough_string)
    return reparsed.toprettyxml(indent="  ")

""" Write to an XML file """
def writeXML():
    global filename
    global root
    fout = open(filename, 'w')
    fout.write(prettify(root))
    fout.close()

""" Format single vm info to XML style and append to root """
def formatXML(ip_str, computername_str, username_str, session_str, state_str, updated_str):
    global root
    
    vm = SubElement(root, 'VM')

    ip = SubElement(vm, 'IP')
    ip.text = ip_str

    computername = SubElement(vm, 'COMPUTERNAME')
    computername.text = computername_str

    username = SubElement(vm, 'USERNAME')
    username.text = username_str

    session = SubElement(vm, 'SESSION')
    session.text = session_str

    state = SubElement(vm, 'STATE')
    state.text = state_str
    
    updated = SubElement(vm, 'UPDATED')
    updated.text = updated_str

""" Process the output of query for each vm """
def processOutput(vm_ip, vm_name, output):
    username_str = "N/A"
    session_str = "N/A"
    state_str = "NOT IN USE"

    for user in output:
        user = user.strip().split()

        if user[3] == "Active":
            username_str = user[0]
            session_str = user[1]
            state_str = "IN USE"

    updated_str = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    
    formatXML(vm_ip, vm_name, username_str, session_str, state_str, updated_str)
    writeXML()

""" Process the err of query for each vm """
def processErr(vm_ip, vm_name, err):
    username_str = "N/A"
    session_str = "N/A"
    state_str = ""
    
    if "No User exists for *" in err:
        state_str = "NOT IN USE (NO LOGGED IN SESSIONS)"
    elif "Access is denied" in err:
        state_str += "QUERY ACCESS DENIED"
    elif "The RPC server is unavailable" in err:
        state_str += "RPC SERVER UNAVAILABLE"
    else:
        state_str = err
    
    updated_str = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    
    formatXML(vm_ip, vm_name, username_str, session_str, state_str, updated_str)
    writeXML()


if __name__ == "__main__":
    global root
    
    while(1):
        try:
            root = Element('ROOT')
            
            #os.system('cls')
            print("Creating XML")
            
            for vm_ip, vm_name in vm_list:
                quser_cmd = "query user /SERVER:" + vm_ip
                quser_cmd_out = subprocess.run(quser_cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
                output = quser_cmd_out.stdout.decode()
                err = quser_cmd_out.stderr.decode()
                
                output = output.split("\n")[1:]
                output = output[:-1]

                print("Processing", vm_name)

                if output:
                    processOutput(vm_ip, vm_name, output)
                else:
                    processErr(vm_ip, vm_name, err)
                
            #os.system('cls')
            print("Created XML")

            time.sleep(15)

        except:
             continue

    
