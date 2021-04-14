
function deleteItem(id){

        document.getElementsByClassName('updateRow')[0].innerHTML ="";    
      fetch('http://localhost:8080/inventory/'+id, {
        method: 'DELETE',
        headers: {
          'content-type': 'application/json'
        }
        })
        .then(response => {
          if(response.status == 200){
            document.getElementById("addMessageNew").innerHTML = "Deleted succesfully!";
          }
          console.log(response)
          
        })
        .catch(err => {
          console.log(err)
        })
        setTimeout(function(){
            document.getElementById('divTableContent').innerHTML = "";
            loadInventory();
            document.getElementById("addMessageNew").innerHTML = ""
            document.getElementsByClassName('updateRow')[0].innerHTML = "";
       },100);
        
    }