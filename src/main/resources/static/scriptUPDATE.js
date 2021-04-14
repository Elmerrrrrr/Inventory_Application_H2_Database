var baseurl = "http://localhost:8080/inventory";

function updateRow(indexNr){
  document.getElementsByClassName('updateRow')[0].innerHTML ="";
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.open("GET",baseurl + "",true);

  xmlhttp.onreadystatechange = function() {

    if(xmlhttp.readyState ===4 && xmlhttp.status ===200){
      var inventory = JSON.parse(xmlhttp.responseText);
      console.log(inventory);

      let id = inventory[indexNr].id;
      let brand = inventory[indexNr].brand;
      let model = inventory[indexNr].model;
      let price = inventory[indexNr].price;
      let latestIndexNumber = inventory.length;
      

      var tbltop = "";

      //main table content we fill from data from the rest call
      var main ="<h1>Update Laptop data:</h1>";
      // for (i = 0; i < inventory.length; i++){
        main +=

             "<form id='updateForm'>" +
            "<div>"+
               "<span style='display:inline-block'>"+
                    "<label for=brand style='display:block'>Brand:</label>"+
                   " <input type=text name=brand id=brand placeholder='Brand' required value="+ brand +" onChange={dataUpdate()}/>"+
               "</span>"+
                
                "<span style='display:inline-block'>"+
                   "<label for=model style='display:block'>Model:</label>"+
                    "<input type=text name=model id=model placeholder='Model' required  value="+ model +" onChange={dataUpdate()}/>"+
                "</span>"+
                "<span style='display:inline-block'>"+
                    "<label for=price style='display:block'>Price:</label>"+
                    "<input type=text name=price id=price placeholder='Price' required value="+ price +" onChange={dataUpdate()}/>"+
                "</span>"+
          "</div>"+
          "</form>";

       var tblbottom = "<div><button id='saveData' onclick='updateData("+ id +")'>Save</button><button id='cancel' onclick='cancel()'>Cancel</button></div><div id='messageAdd'></div>";
      var tbl = tbltop + main + tblbottom;
      document.getElementsByClassName('updateRow')[0].innerHTML += tbl;
      return latestIndexNumber;
    }
  };
  xmlhttp.send();

}


function updateData(id){


//Get updated values from form
let brandUpdated = document.getElementById("brand").value;
let modelUpdated = document.getElementById("model").value;
let priceUpdated = document.getElementById("price").value;

    
    if(!brandUpdated || !modelUpdated || !priceUpdated){
      document.getElementById("brand").style.backgroundColor = 'LightCoral'
      document.getElementById("model").style.backgroundColor = 'LightCoral'
      document.getElementById("price").style.backgroundColor = 'LightCoral'
      document.getElementById("messageAdd").innerHTML = "Please fill in all fields!"
      // alert('Please fill in all fields:')
      
    } else{





  fetch('http://localhost:8080/inventory/'+id, {
    method: 'PUT',
    headers: {
      'content-type': 'application/json'
    },
    body: JSON.stringify( {
      id: id,
      brand: brandUpdated,
      model: modelUpdated,
      price: priceUpdated
    })
  })
    .then(response => {
      if(response.status == 200){
        document.getElementById("addMessageUpdate").innerHTML = "Updated succesfully!"
        document.getElementsByClassName('updateRow')[0].innerHTML = "";
        

      }
      console.log(response)
      
    })
    .catch(err => {
      console.log(err)
    })
    setTimeout(function(){
      document.getElementById('divTableContent').innerHTML = "";
      loadInventory();
      document.getElementById("addMessageUpdate").innerHTML = ""
      document.getElementsByClassName('updateRow')[0].innerHTML = "";
 }, 1800);
}

}
