$(":button").click(function() {
var pollid=$(this).closest("tr").find("td").eq(0).text();
alert(pollid);
jQuery.ajax({
type: "DELETE",
url: "http://localhost:8080/sms-voting/v1/admin/polls/"+pollid,
contentType: "application/json",
success : function ()
{

alert('Success.');
location.reload();
},
failure : function ()
{
alert('failure...');
}
});
});
