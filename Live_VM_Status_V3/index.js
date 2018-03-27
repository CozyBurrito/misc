function loadXMLDoc() {
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      loadXMLDocHelper(this);
    }
  };
  xmlhttp.open("GET", "live_feed.xml", true);
  xmlhttp.send();
};

function loadXMLDocHelper(xml) {
  var i;
  var xmlDoc = xml.responseXML;
  var output = `<div class="vmCards">`;
  var x = xmlDoc.getElementsByTagName("VM");
  for (i = 0; i <x.length; i++) { 
  
    if (i % 4 == 0) {
      output += `<div class="row">`;
    }
    
    output += `
    <div class="col-3">
      <div class="card mb-4" id="card">
        <div class="card-header" id="cardHead">
          <h3 id="computerName">
    `;
    
    output += x[i].getElementsByTagName("COMPUTERNAME")[0].childNodes[0].nodeValue;
    
    output += `
          </h3>
          <p class="text-muted mr-sm-2" style="display:inline;"> 
    `;
 
    output += x[i].getElementsByTagName("IP")[0].childNodes[0].nodeValue + "</p>";
    
    output +=  `<a class="text-muted" style="display:inline;" href="http:///mediawiki/index.php/#`;
    
    output += x[i].getElementsByTagName("COMPUTERNAME")[0].childNodes[0].nodeValue;
    
    output += `"><i class="fa fa-external-link"></i></a>`;
    
    output += ` 
        </div>
        <div class="card-body" id="cardBody">
          <div class="card-text">
    `;
    
    var state = x[i].getElementsByTagName("STATE")[0].childNodes[0].nodeValue;
    var username = x[i].getElementsByTagName("USERNAME")[0].childNodes[0].nodeValue;
    var session = x[i].getElementsByTagName("SESSION")[0].childNodes[0].nodeValue;
    
    output += `<p style="display:inline;" class="font-weight-bold">`;
    output += "STATUS: ";
    
    if (state == "IN USE") {
      output += `<h5 class="text-danger" style="display:inline;">`;
      output +=  state + "<br>" ;
      output += `</h5>`;
    }
    else if (state == "NOT IN USE" || state == "NOT IN USE (NO LOGGED IN SESSIONS)") {
      output += `<h5 class="text-success" style="display:inline;">`;
      output +=  state + "<br>" ;
      output += `</h5>`;
    }
    else {
      output += `<h5 class="text-warning" style="display:inline;">`;
      output +=  state + "<br>" ;
      output += `</h5>`;
    }
    
    output += `<a href="" class="text-info" data-toggle="collapse" data-target="#vmDetails`;
    output += i;
    output += `">Details</a>`;
    
    output += `<div class="collapse" id="vmDetails`
    output += i;
    output += `">`;
    
    output += `<p class="font-weight-bold" style="display:inline;">`;
    output += "USERNAME: </p>";
    output += username + "<br>";
    
    output += `<p class="font-weight-bold" style="display:inline;">`;
    output +=  "SESSION: </p>";
    output += session;

    output += `
            </div>
          </div>
        </div>
        <div class="card-footer small text-muted" id="cardFoot">
    `;
    
    output += "Last updated at " + x[i].getElementsByTagName("UPDATED")[0].childNodes[0].nodeValue;
          
    output += `    
          </div>
        </div>
      </div>
    `;
    
    if (i % 4 == 3) {
      output += `</div>`;
    }
  }
  
  output += `</div>`;

  document.getElementById("statusList").innerHTML = output;
  
  $('.vmCards').collapse('toggle');
};

function search() {

};