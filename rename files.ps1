
$taglib = "\taglib-sharp-2.1.0.0-windows\Libraries\taglib-sharp.dll"

[System.Reflection.Assembly]::LoadFile($taglib)

$files = Get-ChildItem -Exclude *.ps1
$num = 1

foreach ($file in $files) {
    $file | rename-item -newname { "$num.jpg" }
	$num++
}

