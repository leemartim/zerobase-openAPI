<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> <!-- 제이쿼리 CDN-->
<script>

$(document).ready(function() {
	var param = {
			"a" : "에이"
		,	"b" : "비"
	}
	
	var callback = function(data) {
		console.log(data);
	}
	
	callAjax(param, "./InsertWifiServlet", callback, false);
})

//Ajax
function callAjax(param, url, callback, async) {
		$.ajax({
			type: "POST", 
			url: url,
			dataType: "json",
			data : param,
			async : async,
			success: function (result) {
				callback(result)
			}, error: function(xhr, status, err) {
				console.log('entered error...');
				console.log("xhr : " + JSON.stringify(xhr));
				console.log("status : " + JSON.stringify(status));
				console.log("err : " + err);   
			}
		})
}
</script>
</body>
</html>