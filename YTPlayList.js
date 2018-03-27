var divs = document.getElementsByClassName("timestamp");
var spans = [];
var seconds = 0;

for (i = 0; i < divs.length; i++) {
  spans[i] = divs[i].getElementsByTagName("span");
  var time = spans[i][0].innerHTML.split(':');
  seconds += (+time[0]) * 60 + (+time[1]);
}

var hours = parseInt(seconds / 3600) % 24;
var minutes = parseInt(seconds / 60) % 60;
var seconds = seconds % 60;

var result = (hours < 10 ? "0" + hours : hours) + ":" 
              + (minutes < 10 ? "0" + minutes : minutes) + ":" 
              + (seconds < 10 ? "0" + seconds : seconds);

alert(result);