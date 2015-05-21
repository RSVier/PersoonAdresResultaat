<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script> 
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagina personenlijst</title>
</head>
<body>
<h1>Pagina personenlijst</h1>
<form action="Toevoegen.jsp" method="get">
    <input type="submit" value="Persoon toevoegen" name="Submit" id="frm1_submit" />
</form>

	<table class="persoonlijst">
		<thead>
			<tr>
				<th>Persoon</th>
				<th>Adres</th>
				<th colspan=3 align="left"><input type="text" id="search" placeholder="Type to search"/></th>
			</tr>
		</thead>
    	<c:forEach var="persoon" items="${list}">
        	<tr class="personen">
            	<td>${persoon.voornaam} ${persoon.achternaam}</td>
            	<td>${persoon.adres.straatnaam} ${persoon.adres.huisnummer}</td>
            	<td colspan=3 id="CRUDOpties">
            		<a href="UserController?action=edit&id=<c:out value="${persoon.id}"/>">Wijzig gegevens</a>
            		<a href="UserController?action=delete&id=<c:out value="${persoon.id}"/>">Verwijder</a>
            		<a href="UserController?action=result&id=<c:out value="${persoon.id}"/>">Bekijk resultaat</a>
            	</td>
        	</tr> 
    	</c:forEach>
	</table>
</body>

<script type="text/javascript">
var $rows = $('.personen');
$('#search').keyup(function() {
    var val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase();
    
    $rows.show().filter(function() {
        var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
        return !~text.indexOf(val);
    }).hide();
});
</script>
</html>