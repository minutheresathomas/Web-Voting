$(":button").click(function () {
alert("clicked");
var question = $("#question").val();
alert(question);


var choicetxt="[";
var valueText=document.getElementsByName('DynamicTextBox');
alert(valueText);
for(var i=0;i<valueText.length;i++){
//alert("length:"+valueText.length);
//alert(valueText[i].value);
var vartext=valueText[i].value;

choicetxt=choicetxt+"{\"option\":\""+vartext+"\"}";
if(i<valueText.length-1){
choicetxt=choicetxt+","; 
}
else
choicetxt=choicetxt+"]";

//alert("option :"+choicetxt);

}
var obj=JSON.parse(choicetxt);
//alert("obj"+obj);



var createdate=$("#datepicker").val();
alert(createdate);
var expirydate=$("#datepicker").val();
jQuery.ajax({
type: "POST",
url: "http://localhost:8080/sms-voting/v1/admin/polls",
contentType: "application/json",
dataType: "json",
data: JSON.stringify({
"question": $("#question").val(),
"choices": obj,
"creation date":$("#datepicker").val(),
"expiry date":	$("#datepicker").val()
}),
success: function (data) {
alert("success");
},
error: function () {
alert("failed");
}
});
});