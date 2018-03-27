var divs = document.getElementsByTagName("div");
for (var i = 0; i < divs.length; i++) {
  var x = divs[i].getAttribute("data-subreddit");
  if (x == "" || x == "") {
    divs[i].parentNode.removeChild(divs[i]);
  }
}