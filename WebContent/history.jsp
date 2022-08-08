<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<!-- 스타일 정의 -->
<style> 

/* 테이블 스타일 */
#customers {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 1800px;
}

#customers td, #customers th {
  font-size: 14px;
  border: 1px solid #ddd;
  padding: 2px;
}

#customers tr:nth-child(even){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #04AA6D;
  color: white;
}

/*  테이블 스타일 끝 */
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> <!-- 제이쿼리 CDN-->

</head>
<body>

<h1>히스토리내역</h1>
<a href="./">홈</a> | <a href="./ListHistoryServlet">히스토리 목록</a> | <a href="./load-wifi.jsp">Open Api 와이파이 정보 가져오기</a>
<br><br>

<table id="customers">
	<thead>	
		<tr>
			<th>ID</th>
			<th>x좌표</th>
			<th>y좌표</th>
			<th>조회</th>
			<th>비고</th>
		</tr>
	</thead>
  	<tbody id="tbody">
	   <c:forEach items="${list}" var="lists" varStatus="status">
	  		<tr>
	  			<td>${lists.id}</td>
	  			<td>${lists.XCoordinate}</td> 
	  			<td>${lists.YCoordinate}</td> 
	  			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${lists.lookupDate}" /></td>
	  			<td>
	  				<button type="button" onclick="deleteHistory('${lists.id}')">삭제</button>
	  			</td>
	  		</tr>
	   </c:forEach>
  </tbody>
</table>
<script>


function deleteHistory(id) {
	
	if(id == '') {
		alert("who are you?");
		return;
	}
	
	if(confirm("정말로 삭제하시겠습니까?")) {
				
	
		var param = {
				id : id
		}
		
		$.ajax({
			type: "POST", 
			url: "./deleteHistory",
			dataType: "text",
			data : param,
			async : false,
			success: function (result) {
				alert("삭제가 완료되었습니다.");
				location.reload();
			}, error: function(xhr, status, err) {
				console.log('entered error...');
				console.log("xhr : " + JSON.stringify(xhr));
				console.log("status : " + JSON.stringify(status));
				console.log("err : " + err);   
			}
		})
	}
}
</script>
</body>
</html>


