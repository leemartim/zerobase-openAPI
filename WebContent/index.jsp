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

<h1>와이파이 정보 구하기</h1>
<a href="./">홈</a> | <a href="./ListHistoryServlet">히스토리 목록</a> | <a href="./load-wifi.jsp">Open Api 와이파이 정보 가져오기</a>
<br><br>

LAT: <input type="text" id="LAT" value="${x}"> , LNT: <input type="text" id="LNT" value="${y}">
<button type="button" id="myFocus" >내 위치 가져오기</button>
<button type="button" onclick="searchList()">근처 WIPI 정보 보기</button>
<br><br>
<table id="customers"> 
	<thead>	
		<tr>
			<th>관리번호</th>
			<th>자치구</th>
			<th>와이파이명</th>
			<th>도로명주소</th>
			<th>상세주소</th>
			<th>설치위치(층)</th>
			<th>설치유형</th>
			<th>설치기관</th>
			<th>서비스구분</th>
			<th>망종류</th>
			<th>설치년도</th>
			<th>실내외구분</th>
			<th>X좌표</th>
			<th>Y좌표</th>
			<th>작성일자</th>
		</tr>
	</thead>
  	<tbody id="tbody">
	   <c:forEach items="${list}" var="lists" varStatus="status">
	  		<tr>
	  			<td>${lists.manageNum}</td>
	  			<td>${lists.borough}</td>
	  			<td>${lists.agency}</td>
	  			<td>${lists.streetAddress}</td>
	  			<td>${lists.address}</td>
	  			<td>${lists.agency}</td>
	  			<td>${lists.agency}</td>
	  			<td>${lists.agency}</td>
	  			<td>${lists.service}</td>
	  			<td>${lists.wifiConnection}</td>
	  			<td>${lists.year}</td>
	  			<td>${lists.installLocation}</td>
	  			<td>${lists.XCoordinate}</td> 
	  			<td>${lists.YCoordinate}</td> 
	  			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${lists.dateT}" /></td> 
	  		</tr>
	   </c:forEach>
  </tbody>
</table>
<form method="post" action="./ListWifiServlet" id="frmSubmit">
<input type="hidden" name="x" />
<input type="hidden" name="y" />
</form>
<script>

    $(document).ready(function() {
    	
    })

    $("#myFocus").click(function() {
        getLocation();
    })

function getLocation() { //내 위치 잡기
    if (navigator.geolocation) { // GPS를 지원하면
        navigator.geolocation.getCurrentPosition(function(position) {
            $("#LAT").val(position.coords.latitude)  // x축
            $("#LNT").val(position.coords.longitude)  // y축
        }, function(error) {
        console.error(error);
        }, {
        enableHighAccuracy: false,
        maximumAge: 0,
        timeout: Infinity
        });
    } else {
        alert('GPS를 지원하지 않습니다');
    }
}

function searchList() {
	if($("#LAT").val() == '') {
		alert("LAT축의 데이터를 입력해주세요");
		return; 
	}
	
	if($("#LNT").val() == '') {
		alert("LNT축의 데이터를 입력해주세요");
		return; 
	}
	
	$("[name='x']").val($("#LAT").val());
	$("[name='y']").val($("#LNT").val());
	
	$("#frmSubmit").submit();
}

</script>
</body>
</html>


