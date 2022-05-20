
$(".txtOrderId").text(generateOrderNumber());
$("#cmbItemId").append("<option> Select </option>");
$("#cmbcustormerId").append("<option> Select </option>");


$("#btnAddtoCart").click(function () {
    let itemQty =parseInt($("#itemqty").val());
    let orderQty =parseInt($("#OrderQty").val());
    if($("#OrderQty").val() !=''){
        if(itemQty < orderQty ){
            alert("echara nane");
        }else{
            addToCart();
            ReduceItemQty($("#OrderQty").val());
            loadCartTable();
            clearAddtoCart();
        }
    }else{
        alert("type karala nane");
    }
});

function generateOrderNumber() {
    try {
        let lastOId =OrderDB[OrderDB.length - 1].getOid();
        let newOId = parseInt(lastOId.substring(1, 4)) + 1;
        if (newOId < 10) {
            $(".txtOrderId").text("O00" + newOId);
        } else if (newOId < 100) {
            $(".txtOrderId").text("O0" + newOId);
        } else {
            $(".txtOrderId").text("O" + newOId);
        }
    } catch (e) {
        $(".txtOrderId").text("O001");
    }
};



$(".comfirmOrders").click(function(){
   //generateOrderNumber();
   saveAllOrders();
   loadAllConfirmOrder();
   Carts=[];
   $("#cartTabale").empty();
   clearAll();
});



$("#cmbItemId").click(function () {
    console.log("Click kara")
    var itemId = $("#cmbItemId").val();
    $("#cmbItemId").empty();
    $.ajax({
        url: "http://localhost:8080/PosSystem/items",
        method:"GET",
        success : function (response) {
            for (const i of response.data) {
                addValuesToItems("<option>"+i.itemCode+"</option>");
                if (i.itemCode == itemId) {
                    itemName = i.itemName;
                    price = i.price;
                    itemqty = i.qty;
                    $("#itNames").val(itemName);
                    $("#itemprice").val(price);
                    $("#itemqty").val(itemqty);
                }
            }
        }
    })
});


$("#cmbcustormerId").click(function () {
    var  cusId = $("#cmbcustormerId").val();
    $("#cmbcustormerId").empty();
    $.ajax({
        url: "http://localhost:8080/PosSystem/customer",
        method: "GET",
        success : function (response) {
            for (const i of response.data) {
                addValuesToCmbCustomers("<option>"+i.id+"</option>");

                if(i.id == cusId){
                    cusName = i.name;
                    salary = i.salary;
                    address = i.address;


                    $("#cusName").val(cusName);
                    $("#CusSalary").val(salary);
                    $("#CusAddress").val(address);
                }
            }
        }
    })

});



function addValuesToCmbCustomers(value) {
     $("#cmbcustormerId").append(value);
}

function addValuesToItems(value) {
    $("#cmbItemId").append(value);
}






 function saveAllOrders(){
    let oId =$(".txtOrderId").text();
    let cusid =$("#cmbcustormerId").val();
    let date =$("#datetime").val();
    let discount =$("#txtDiscount").val();
    let totals =$("#txtTotal").text();

        OrderDB.push(new Orders(oId,cusid,date,discount,totals));
 }



 function ReduceItemQty(orderQty){
     var qtyOn = parseInt(orderQty);
     var avilableQty = parseInt($("#itemqty").val());

     avilableQty = avilableQty - qtyOn;
     let  itemCode = $("#cmbItemId").val();

     var ob ={
          "qty" :$("#itemqty").val(),
          "itemCode" :$("#cmbItemId").val(),
     }

     $.ajax({
         url:"http://localhost:8080/PosSystem/items",
         method:"PUT",
         contentType:"application/json",
         data:JSON.stringify(ob),
         success(resp){

         }
     })

 };

 let sub_total = 0;
 let discount = 0;
 let total = 0;

 function Calculatetotal(orderQty,itemPrice,disc){
    sub_total+= orderQty * itemPrice;
    discount+= (orderQty * itemPrice *disc)  / 100;
    total = sub_total - discount;
    $("#txtTotal").text(total);
    $("#txtsubTotal").text(sub_total);
 }


function addToCart() {

  let  itemCode = $("#cmbItemId").val();
  let  itemName = $("#itNames").val();
  let  itemPrice = $("#itemprice").val();
  let  itemQty = $("#itemqty").val();
  let  orderQty = $("#OrderQty").val();
  disc = $("#txtDiscount").val();
  let total = itemPrice * orderQty;
  let fulltoal = $("#txtTotal").text();


   Calculatetotal($("#OrderQty").val(),$("#itemprice").val(),$("#txtDiscount").val());
  
    for(var i in Carts){
        if(Carts[i].getcartICode()===itemCode){
          var newqty  =+Carts[i].getcartOQty() +  +orderQty;
          var newTotal =itemPrice * newqty; 
          Carts[i].setcartOQty(newqty);
          Carts[i].setTotal(newTotal);
          return;
        }
    }
   
    Carts.push(new cart(itemCode,itemName,itemPrice,orderQty,total,disc,fulltoal));


}

 function loadCartTable(){
    $("#cartTabale").empty();
    for(var i of Carts){
        let row = `<tr><td>${i.getcartICode()}</td><td>${i.getcartIName()}</td><td>${i.getcartOQty()}</td><td>${i.getcartIPrice()}</td><td>${i.getTotal()}</td><td><button type="button" class="btn-sm  btnDeleteItem btn-danger">Delete</button>
        <button type="button"  data-bs-toggle="modal" data-bs-target="#exampleModal" class="btn-sm border btn-success updaterow" style="width: 17%;  "><svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px"
        width="35" height="20"
        viewBox="0 0 172 172"
        style=" right:5px; position: relative; fill:#000000;"><g fill="none" fill-rule="nonzero" stroke="none" stroke-width="1" stroke-linecap="butt" stroke-linejoin="miter" stroke-miterlimit="10" stroke-dasharray="" stroke-dashoffset="0" font-family="none" font-weight="none" font-size="none" text-anchor="none" style="mix-blend-mode: normal"><path d="M0,172v-172h172v172z" fill="none"></path><g fill="#ffffff"><path d="M101.05,42.28333l-79.55,79.55v28.66667h28.66667l79.55,-79.55zM111.8,31.53333l17.2,-17.2l28.66667,28.66667l-17.2,17.2z"></path></g></g></svg></button></td>
        </tr>`
        $("#cartTabale").append(row);
    }
 }

   function clearAll(){
    $("#datetime").val('');
    $("#cmbcustormerId").val('');
    $("#cusName").val('');
    $("#CusSalary").val('');
    $("#CusAddress").val('');
    $("#txtTotal").text('');
    $("#txtsubTotal").text('');
   }

   function clearAddtoCart(){
      $("#cmbItemId").val('');
      $("#itNames").val('');
      $("#itemprice").val('');
      $("#itemqty").val('');
      $("#OrderQty").val('');
      $("#txtDiscount").val('');
   }


