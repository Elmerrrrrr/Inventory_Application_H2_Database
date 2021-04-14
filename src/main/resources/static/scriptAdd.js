
function addData(){

    var tbltop = "";
  
    //main table content we fill from data from the rest call
    document.getElementsByClassName('updateRow')[0].innerHTML ="";
    var main ="<h1>Add New Laptop:</h1>";
    // for (i = 0; i < inventory.length; i++){
      main +=
  
        "<form>" +
          "<div>"+
             "<span style='display:inline-block'>"+
                  "<label for=brand style='display:block'>Brand:</label>"+
                 " <input type=text name=brand id=brandNew placeholder='Add Brand..' value='' required  onChange={dataUpdate()}/>"+
             "</span>"+
              
              "<span style='display:inline-block'>"+
                 "<label for=model style='display:block'>Model:</label>"+
                  "<input type=text name=model id=modelNew placeholder='Add Model..' value=''  required onChange={dataUpdate()}/>"+
              "</span>"+
              "<span style='display:inline-block'>"+
                  "<label for=price style='display:block'>Price:</label>"+
                  "<input type=text name=price id=priceNew placeholder='Add Price..'  value='' required onChange={dataUpdate()}/>"+
              "</span>"+
        "</div>"+
        "</form>";
  
        
        var tblbottom = "<div><button id='saveData' onclick='sendNewData()'>Send</button><button id='cancel' onclick='cancel()'>Cancel</button></div><div id='messageAdd'></div>";
        var tbl = tbltop + main + tblbottom;
        document.getElementsByClassName('updateRow')[0].innerHTML += tbl;
    
  }
    
  


  function sendNewData(){

  //Get new values from form
  let brandNew = document.getElementById("brandNew").value;
  let modelNew= document.getElementById("modelNew").value;
  let priceNew = document.getElementById("priceNew").value;

    
    if(!brandNew || !modelNew || !priceNew){
      document.getElementById("brandNew").style.backgroundColor = 'LightCoral'
      document.getElementById("modelNew").style.backgroundColor = 'LightCoral'
      document.getElementById("priceNew").style.backgroundColor = 'LightCoral'
      document.getElementById("messageAdd").innerHTML = "Please fill in all fields!"
      // alert('Please fill in all fields:')
      
  } else{

     
  
    fetch('http://localhost:8080/inventory/', {
      method: 'POST',
      headers: {
        'content-type': 'application/json'
      }
      ,
      body: JSON.stringify({
//        id: totalAmount+1,
        brand: brandNew,
        model: modelNew,
        price: priceNew
      })
    })
      .then(response => {
        if(response.status == 200){
          document.getElementById("addMessageNew").innerHTML = "Added succesfully!"
          document.getElementsByClassName('updateRow')[0].innerHTML = "";
        }
        console.log(response)
      })
      .catch(err => {
        console.log(err)
      })

      setTimeout(function(){
        document.getElementById('divTableContent').innerHTML = "";
        totalAmount=0; //reset counter before reloading
        loadInventory();
        document.getElementById("addMessageNew").innerHTML = ""
        document.getElementsByClassName('updateRow')[0].innerHTML = "";
   }, 1800); 
  
  }
}

  function cancel(){
    
        document.getElementsByClassName('updateRow')[0].innerHTML = "";
   
  }