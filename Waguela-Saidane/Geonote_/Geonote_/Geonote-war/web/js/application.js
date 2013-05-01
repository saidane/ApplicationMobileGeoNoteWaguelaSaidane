
var url="http://localhost:8080/Geonote-war/GeonoteWS";
function SendSalarySMS() // boutton
{
    var pl = new SOAPClientParameters();
    pl.add("name", "0651643662"); 
    SOAPClient.invoke(url, "hello", pl, true, SendSalarySMS_callBack);
}
function SendSalarySMS_callBack(r)
{
    alert(r);
}
function getDocument(){
    return window.document;
}

function EnregistrerNote()
{
    var adr = document.getElementById("ref01").value;
    var tit = document.getElementById("ref02").value;
    var desc = document.getElementById("ref03").value;
    var pl = new SOAPClientParameters();
    pl.add("adresse", adr);
    pl.add("titre", tit);
    pl.add("description", desc);
    SOAPClient.invoke(url,"EnregistrerNote",pl,true,EnregistrerNote_callBack);
}

function EnregistrerNote_callBack(r)
{   
    alert(r);   
}


function showNotes(){
    var pl = new SOAPClientParameters();
    SOAPClient.invoke(url,"showNotes",pl,true,showNotes_callBack);
}
function showNotes_callBack(r){
    $("#nNotes").html(r);
    $("#nNotes").show();    
}

function ajouterNoteParcours()
{
    var tit = document.getElementById("parcourstitre").value;
    var com = document.getElementById("parcourscom").value;
    var pl = new SOAPClientParameters();
    var slt=document.forms[5].elements["multinote"];
    pl.add("tit", tit);
    pl.add("com", com);
    var s=",";
    for(var i=0;i<slt.options.length;i++){
        if(slt.options[i].selected==true)
        {          
           
            s=s+slt.options[i].value+",";
        }
    }
  
    pl.add("idNotes", s);
    SOAPClient.invoke(url,"ajouterNoteParcours",pl,true,ajouterNoteParcours_callBack);
}


function ajouterNoteParcours_callBack(r)
{   
    alert(r);   
}
function modifierParcours(){
    var tit = document.getElementById("modifparcourstitre").value;
    var com = document.getElementById("modifparcourscom").value;
    var pl = new SOAPClientParameters();
    var slt=document.forms[5].elements["multiparcours"];
    var sltt=document.forms[6].elements["multinote"];
    pl.add("tit", tit);
    pl.add("com", com); 
    for(var i=0;i<slt.options.length;i++){
        if(slt.options[i].selected==true)
        {                    
            var  s=slt.options[i].value;
        }
    }   
    var ss=",";
    for(var i=0;i<sltt.options.length;i++){
        if(sltt.options[i].selected==true)
        {                
            ss=ss+sltt.options[i].value+",";
        }
    }  
    pl.add("idParcours", s);
    pl.add("idNotes", ss);    
    SOAPClient.invoke(url,"modifierParcours",pl,true,modifierParcours_callBack);
}
function modifierParcours_callBack(r)
{   
    alert(r);   
}


function adresseChoisi()
{
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
    for (var j=0;j<myArray.length;j++){
        alert(myArray[j]); 
    }
}
function parcoursadresseChoisi(){
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
        alert(myArray[j]);      
    }    
}

function showParcours(){
    var pl = new SOAPClientParameters();
    
    SOAPClient.invoke(url,"showParcours",pl,true,showParcours_callBack);
}
function showParcours_callBack(r){
    $("#nParcours").html(r);
    $("#nParcours").show();    
}

function showParcoursDetail(){
    var slt=document.forms[5].elements["multiparcours"];
    var pl = new SOAPClientParameters();
    for(var i=0;i<slt.options.length;i++){
        if(slt.options[i].selected==true)
        {                    
            var  s=slt.options[i].value;
        }
    }   
    pl.add("idParcours", s);
    SOAPClient.invoke(url,"showParcoursDetail",pl,true,showParcoursDetail_callBack);
}
function showParcoursDetail_callBack(r){
    $("#ParcoursDetail").html(r);
    $("#ParcoursDetail").show();    
}

function parcoursDetail(){
    var slt=document.forms[2].elements["multiparcours"];
    for(var i=0;i<slt.options.length;i++){
        if(slt.options[i].selected==true)
        {                    
            var  s=slt.options[i].text;
            $("#nDetail").html(s);
            $("#nDetail").show(); 
        }
    }   
     
}
