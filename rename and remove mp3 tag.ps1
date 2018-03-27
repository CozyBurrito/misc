
$taglib = "\taglib-sharp-2.1.0.0-windows\Libraries\taglib-sharp.dll"

[System.Reflection.Assembly]::LoadFile($taglib)

$files = Get-ChildItem "D:\Downloads\Nightride FM" -Filter *.mp3

foreach ($file in $files) {
    $media = [taglib.file]::create("D:\Downloads\Nightride FM\$file")
    
    $media.tag.Track = ""

    $media.Save() 

    $file | rename-item -newname { [string]($file.name).substring(4) }
}

