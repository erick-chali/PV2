<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet"/>

</head>
<body>
	<label id="fPago">1</label>
	<label id="celda"></label>
	<label id="row"></label>
	<input type="text" id="caja">
	<button id="fila">AGREGAR</button>
	<table class="table table-condensed table-bordered">
		<thead>
			<tr>
				<th>Col 1</th>
				<th>Col 2</th>
				<th>Col 3</th>
				<th>Col 4</th>
				<th>Col 5</th>
				<th>Col 6</th>
				<th>Col 7</th>
				<th>Col 8</th>
				<th>Col 9</th>
				<th>Col 10</th>
				<th>Col 11</th>
				<th>Col 12</th>
				<th>Col 13</th>
				
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="editProducto" id="1">Codigo</td>
				<td class="edit" id="2">col 2</td>
				<td class="editar" id="3">col 3</td>
				<td class="edit" id="4">col 4</td>	
				<td class="edit" id="5">col 5</td>	
				<td class="edit" id="6">col 6</td>
				<td class="edit">col 7</td>
				<td class="edit">col 8</td>
				<td class="edit">col 9</td>
				<td class="edit">col 10</td>	
				<td class="edit">col 11</td>	
				<td class="edit">col 12</td>	
				<td class="edit">col 13</td>		
			</tr>
		</tbody>
	</table>
</body>
<script src="js/jquery-1.11.3.min.js"></script> 
<script src="js/bootstrap.min.js"></script>
<script src="js/jeditable.min.js"></script>
<script src="js/demoEdit.js"></script>
</html>