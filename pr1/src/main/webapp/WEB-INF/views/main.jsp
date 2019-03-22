<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Main Page</title>
<style>
	textt{
		font-size:30px;
		color:blue;
	}
	textt2{
		font-size:30px;
		color:violet;
		font-weight:bold;
	}
	textt3{
		font-size:30px;
		color:purple;
	}
</style>
</head>

<body align="center">
<textt>안녕, 세상이여!</textt><br>
<textt3>H</textt3>
<textt2>e</textt2>
<textt3>l</textt3>
<textt2>l</textt2>
<textt3>o</textt3>

<textt2>W</textt2>
<textt3>o</textt3>
<textt2>r</textt2>
<textt3>l</textt3>
<textt2>d</textt2>
<textt3>!</textt3>

<br><br>SPRING VER : 
<%=org.springframework.core.SpringVersion.getVersion() %>

</body>
</html>