<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Coin Page</title>
</head>
<style>
	td{
		font-size:18px;
	}
	input{
		width:100%;
		font-size:18px;
	}
</style>
<body>

<form action="coinresult" method="get" >
	<table>
		<tr>
			<td>
				지폐금액
			</td>
			<td>
				<input type="number" name="T" max="10000" min="0" value="1000">
			</td>
		</tr>
		<tr>
			<td>
				동전의 가지 수
			</td>
			<td>
				<table>
					<tr>
						<td><input type="button" value="＋" onclick="addRow()"></td>
						<td><input type="button" value="－" onclick="deleteRow()"></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
			</td>
			<td>
				<table id="coin_table" width="250px">
					<tr>
						<td>동전금액</td>
						<td>개수</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="계산" >
			</td>
		</tr>
	</table>
</form>

<script>
function addRow(){
	var questTable = document.getElementById('coin_table'); 
	var newRow = questTable.insertRow();
	var newCell1 = newRow.insertCell();
	var newCell2 = newRow.insertCell();

	newCell1.className="";
	newCell2.className="";

	newCell1.innerHTML = "<input name='Pi"+(questTable.rows.length-1)+"' type='number' value='0'>";
	newCell2.innerHTML = "<input name='Ni"+(questTable.rows.length-1)+"' type='number' value='0'>"; 
}
function deleteRow(){
	var questTable = document.getElementById('coin_table'); 
	if (questTable.rows.length > 2){
		questTable.deleteRow(questTable.rows.length-1);
	}
}
addRow()
</script>


</body>
</html>