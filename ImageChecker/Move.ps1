$Files = Get-ChildItem .\All -name
$FileNames = Get-Content .\list.txt

foreach($file in $Files) {
    if($FileNames | Select-String "$file" -quiet) {
        Copy-Item .\All\$file .\List
    }
    else {
         Write-Host "$file not in text file"
    }
    
}

