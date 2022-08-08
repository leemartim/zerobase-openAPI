<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1 align="center"><span id="cnt"></span>개의 Wifi 정보를 정상적으로 저장하였습니다.</h1>
    <div style="text-align:center;">
        <a href="./">홈으로 가기</a>
    </div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> <!-- 제이쿼리 CDN-->
<script>
$(document).ready(function() {
	   deleteAjax(); //데이터 등록시 초기 값 삭제
	   cnt();  // 개수 호출
	   
       var cntLength = Number($("#cnt").text()); //개수 텍스트로 전송후 가져오기
       var forLength = Math.floor(cntLength / 10); 
       for(var i=0;i <= forLength; i++) { //데이터 총 값 17774개 인설트 반복문
            console.log("i = "+ i)
            var first = 0;
            var last = 0;
            if(i == 0) {
                last = 9; 
            } else {
                first = parseInt(i*10); 
                last = parseInt(((i+1)*10)-1); 
            }
            init(first, last);  //데이터 인설트
            
        }
       
        $("#cnt").text(cntLength)
        
})

function cnt() {
    var xhr = new XMLHttpRequest();
    var url = 'http://openapi.seoul.go.kr:8088/74526f785664627339327549795559/xml/TbPublicWifiInfo/0/10'; /* URL */

    xhr.open('GET', url, false);
    xhr.onreadystatechange = function () {
        if (this.readyState == xhr.DONE) {  // <== 정상적으로 준비되었을때
            if(xhr.status == 200||xhr.status == 201){ // <== 호출 상태가 정상적일때
                var oParser = new DOMParser();
                var oDom = oParser.parseFromString(this.responseText,"text/xml");
                var cnt = oDom.getElementsByTagName("list_total_count")[0].firstChild.data;
                $("#cnt").text(cnt);
                console.log(oDom)
            }
        }
        
    };
    xhr.send(null);
}

function init(f,l) { //17774개 데이터를 50개식 인설트
    var xhr = new XMLHttpRequest();
    var url = 'http://openapi.seoul.go.kr:8088/74526f785664627339327549795559/xml/TbPublicWifiInfo/'+f+'/'+l; /* URL */
    var cnt = 0;
	xhr.open('GET', url, false);
	xhr.onreadystatechange = function () {
		if (this.readyState == xhr.DONE) {  // <== 정상적으로 준비되었을때
            if(xhr.status == 200||xhr.status == 201){ // <== 호출 상태가 정상적일때
                var oParser = new DOMParser();
                var oDom = oParser.parseFromString(this.responseText,"text/xml");
                $("#cnt").text(cnt);
                var acnt = 0;
                for(j=0;j<10;j++) { 
                    oParser = new DOMParser();
                    oDom = oParser.parseFromString(this.responseText,"text/xml");
                    console.log("들어옴")
                    console.log(oDom)
                    console.log("oDom = " +JSON.stringify(oDom) );
                    var row = oDom.getElementsByTagName("row")[j];
                    var X_SWIFI_MGR_NO = row.getElementsByTagName("X_SWIFI_MGR_NO")[0].firstChild.data;
                    var X_SWIFI_WRDOFC = row.getElementsByTagName("X_SWIFI_WRDOFC")[0].firstChild.data;
                    var X_SWIFI_MAIN_NM = row.getElementsByTagName("X_SWIFI_MAIN_NM")[0].firstChild.data;
                    var X_SWIFI_ADRES1 = row.getElementsByTagName("X_SWIFI_ADRES1")[0].firstChild.data;
                    var X_SWIFI_ADRES2 = row.getElementsByTagName("X_SWIFI_ADRES2")[0].firstChild.data;
                    //var X_SWIFI_INSTL_FLOOR = row.getElementsByTagName("X_SWIFI_INSTL_FLOOR")[0].firstChild.data;
                    var X_SWIFI_INSTL_TY = row.getElementsByTagName("X_SWIFI_INSTL_TY")[0].firstChild.data;
                    var X_SWIFI_INSTL_MBY = row.getElementsByTagName("X_SWIFI_INSTL_MBY")[0].firstChild.data;
                    var X_SWIFI_SVC_SE = row.getElementsByTagName("X_SWIFI_SVC_SE")[0].firstChild.data;
                    var X_SWIFI_CMCWR = row.getElementsByTagName("X_SWIFI_CMCWR")[0].firstChild.data;
                    var X_SWIFI_CNSTC_YEAR = row.getElementsByTagName("X_SWIFI_CNSTC_YEAR")[0].firstChild.data;
                    var X_SWIFI_INOUT_DOOR = row.getElementsByTagName("X_SWIFI_INOUT_DOOR")[0].firstChild.data;
                    var LAT = row.getElementsByTagName("LAT")[0].firstChild.data;
                    var LNT = row.getElementsByTagName("LNT")[0].firstChild.data;
                    var WORK_DTTM = row.getElementsByTagName("WORK_DTTM")[0].firstChild.data;
                    
                    var param = {
                    		
                    		manageNum : X_SWIFI_MGR_NO
                   		,	borough : X_SWIFI_WRDOFC
                   		,	agency : X_SWIFI_MAIN_NM 
                   		,	streetAddress : X_SWIFI_ADRES1
                   		,	address : X_SWIFI_ADRES2
                   		//,	X_SWIFI_INSTL_FLOOR : X_SWIFI_INSTL_FLOOR //층
                   		,	type : X_SWIFI_INSTL_TY
                   		,	typeNet : X_SWIFI_INSTL_MBY //서울시
                   		,	service : X_SWIFI_SVC_SE
                   		,	wifiConnection : X_SWIFI_CMCWR
                   		,	year : X_SWIFI_CNSTC_YEAR
                   		,	installLocation : X_SWIFI_INOUT_DOOR
                   		,	yCoordinate : LAT  //서울시 API에서 위도 경도가 바껴서 나온거 같음 -> y축값이나옴
                   		,	xCoordinate : LNT  //x축 값이나옴 
                   		,	date : WORK_DTTM
                   		
                    }
                    
                    $.ajax({
            			type: "POST", 
            			url: "./InsertWifiServlet",
            			dataType: "text",
            			data : param,
            			async : false,
            			success: function (result) {
            			}, error: function(xhr, status, err) {
            				console.log('entered error...');
            				console.log("xhr : " + JSON.stringify(xhr));
            				console.log("status : " + JSON.stringify(status));
            				console.log("err : " + err);   
            			}
            		})
                    
                }
            }
		}
        
	};
	xhr.send();
}


//Ajax
function deleteAjax() {
	$.ajax({
		type: "POST", 
		url: "./deleteWifiServlet",
		dataType: "json",
		async : false,
		success: function () {
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