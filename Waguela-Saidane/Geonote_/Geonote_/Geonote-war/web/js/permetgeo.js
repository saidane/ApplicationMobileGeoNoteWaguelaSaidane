var directionsService = new google.maps.DirectionsService();		
var directionsDisplay = new google.maps.DirectionsRenderer();
//var Reference01 = encodeURIComponent(document.getElementById("ref01").value);
//var Reference02 = encodeURIComponent(document.getElementById("ref02").value);	  
var Reference03;
var Reference04;
var map ;
var geocoder = new google.maps.Geocoder();

function showGoogleMap(){
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
    }
}


function searchAddress() {
    var adresse = document.getElementById("map_canvas").value;
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

