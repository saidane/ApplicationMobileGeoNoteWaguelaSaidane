var map;
var directionsDisplay;
var directionsService = new google.maps.DirectionsService();
var geocoder = new google.maps.Geocoder();
function initialize() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
        navigator.geolocation.watchPosition(showPosition);
    } else {
        console.log("no geolocation support");
    }
    function showPosition(position) {
	
        var lat = position.coords.latitude;
        var lng = position.coords.longitude;
        var options = {
            zoom: 15, 
            center: new google.maps.LatLng(lat, lng), 
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        map = new google.maps.Map(document.getElementById("map_canvas"), options);
        var marker = new google.maps.Marker({
            position: new google.maps.LatLng(lat, lng)
        });
        marker.setMap(map);
		
        var infoWindow = new google.maps.InfoWindow({
            content: "Vous êtes ici"
        });
        infoWindow.open(map, marker);
        directionsDisplay = new google.maps.DirectionsRenderer();
        directionsDisplay.setMap(map);
        directionsDisplay.setPanel(document.getElementById('displayinfo_canvas'));
    }
          
}
function showItineraire(){
    
      var myArray = new Array(); 
    var slt=document.forms[5].elements["multinote"];       
    for(var i=0;i<slt.options.length;i++){
        if(slt.options[i].selected==true)
        {          
            var indexA=slt.options[i].text.indexOf(" @ ");
            var indexB=slt.options[i].text.length;
            var  s=slt.options[i].text.substring(indexA+2, indexB)
            myArray.push(s);          
        }
    }
       var waypts = [];
       if(myArray.length>=2){
    if(myArray.length>=3){
        for (var i=1;i<myArray.length-1;i++)
            {
                waypts.push({
                   location:myArray[i],
                   stopover:true
                });
            }
    }
    directionsService.route({
        origin: myArray[0],       
        destination: myArray[myArray.length-1],
        waypoints: waypts,
        unitSystem: google.maps.DirectionsUnitSystem.METRIC,
        travelMode: google.maps.DirectionsTravelMode.DRIVING
    }, function(result, status){
        if (status == google.maps.DirectionsStatus.OK){
            directionsDisplay.setDirections(result);
        } else {
            alert('Le calcul d\’itinéraire a échoué.');
        }
    }); }
    else {
      var adresse = myArray[0];
    geocoder.geocode({
        address: adresse
    }, function(results, status){
        if (status == google.maps.GeocoderStatus.OK){
            map.setCenter(results[0].geometry.location);
            var marker = new google.maps.Marker({
                position: results[0].geometry.location,
                map: map
            });
        } else {
            alert("Le géocodage a échoué.");
        }
    });
    }
}

function searchAddress() {
    var adresse = document.getElementById("ref01").value;
    geocoder.geocode({
        address: adresse
    }, function(results, status){
        if (status == google.maps.GeocoderStatus.OK){
            map.setCenter(results[0].geometry.location);
            var marker = new google.maps.Marker({
                position: results[0].geometry.location,
                map: map
            });
        } else {
            alert("Le géocodage a échoué.");
        }
    });
}


function searchAddressBdd() {
    var myArray = new Array(); 
    var slt=document.forms[5].elements["multinote"];       
    for(var i=0;i<slt.options.length;i++){
        if(slt.options[i].selected==true)
        {          
            var indexA=slt.options[i].text.indexOf(" @ ");
            var indexB=slt.options[i].text.length;
            var  s=slt.options[i].text.substring(indexA+2, indexB)
            myArray.push(s);          
        }
    }
 var adresse = myArray[0];
    geocoder.geocode({
        address: adresse
    }, function(results, status){
        if (status == google.maps.GeocoderStatus.OK){
            map.setCenter(results[0].geometry.location);
            var marker = new google.maps.Marker({
                position: results[0].geometry.location,
                map: map
            });
            marker.setMap(map);
		
            var infoWindow = new google.maps.InfoWindow({
                content: address
            });
            infoWindow.open(map, marker);
        } else {
            alert("Le géocodage a échoué.");
        }
    });
}
  
function showItineraireParcoursDetail(){
    
     var refTab = document.getElementById("showparcoursDetail");
    row = refTab.rows[1];
    col = row.cells[3];
    var first=col.innerHTML;
    var myArray = new Array();   
    var second=[];   
    var reg=new RegExp("<br>");
    second = first.split(reg); 
    for (var j = 0; j < second.length-1; j++) {   
        var indexA=second[j].indexOf(" @ ");
        var indexB=second[j].length;
        var  s=second[j].substring(indexA+2, indexB)
        myArray.push(s);          
       // alert(myArray[j]);      
    }  
       var waypts = [];
    if(myArray.length>=3){
        for (var i=1;i<myArray.length-1;i++)
            {
                waypts.push({
                   location:myArray[i],
                   stopover:true
                });
            }
    }
    directionsService.route({
        origin: myArray[0],       
        destination: myArray[myArray.length-1],
        waypoints: waypts,
        unitSystem: google.maps.DirectionsUnitSystem.METRIC,
        travelMode: google.maps.DirectionsTravelMode.DRIVING
    }, function(result, status){
        if (status == google.maps.DirectionsStatus.OK){
            directionsDisplay.setDirections(result);
        } else {
            alert('Le calcul d\’itinéraire a échoué.');
        }
    });
}