$("#search").click(function() {
	var que = $("#queSubString").val();
	alert("sub string is : "+que);
	var url = "http://localhost:8080/sms-voting/user/polls/searchPolls?question="+que;
	jQuery.ajax({
//		$.get(url, function(data) {
//			alert("Data is : "+data);
//			document.location.href = url;
//		});
		type: "GET",
	    url: url,
	    contentType: "application/json",
	    success : function ()
	    {
	    	alert('success');
	    	document.location.href = url;
	    },
		failure : function ()
		{
			alert('failure...');
		}
	});
});
//$(document).ready(function() {
//$.ajaxSetup({ cache: true });
//$.getScript('//connect.facebook.net/en_UK/all.js', function(){
//  FB.init({
//    appId: '627796963977885',
//  });     
//  $('#loginbutton,#feedbutton').removeAttr('disabled');
//  FB.getLoginStatus(updateStatusCallback);
//});
//});