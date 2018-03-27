
import reg_key 

def main():
	reg_key.write_registry_key_with_path("HKEY_LOCAL_MACHINE", "SOFTWARE\", "TestName", "Testval")

if __name__ == "__main__":
    main()
