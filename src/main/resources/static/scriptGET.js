
var baseurl = "http://localhost:8080/inventory";
let totalAmount=0;

function loadInventory(){
  document.getElementById('divTableContent').innerHTML = "";
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.open("GET",baseurl + "",true);

  xmlhttp.onreadystatechange = function() {

    if(xmlhttp.readyState ===4 && xmlhttp.status ===200){
      let inventory = JSON.parse(xmlhttp.responseText);
      console.log(inventory);

      var tbltop = "";

      var main ="";
      for (let i = 0; i < inventory.length; i++){
        main +=

        "<div class='divTableRow'><div class='divTableCell'>"+ inventory[i].brand +"</div><div class='divTableCell'>"+ inventory[i].model +"</div><div class='divTableCell'>"+ inventory[i].price +"</div><div class='divTableCell'><button id='updateData' onclick='updateRow("+ i +")'>Update Data</button><button id='deleteData' onclick='deleteItem("+ inventory[i].id +")'>Delete Data</button><button id='viewData' onclick='viewRow("+ i +")'>View</button></div></div>";
        totalAmount++;
      }
      var tblbottom = "";
      var tbl = tbltop + main + tblbottom;
      document.getElementById('divTableContent').innerHTML += tbl;
      document.getElementById('addData').onclick.value="addData("+(totalAmount+1)+")";//add a up-following id number

    }
  };
  xmlhttp.send();
}
window.onload = function(){
  loadInventory();
}
