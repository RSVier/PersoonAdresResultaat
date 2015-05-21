<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script> 
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagina resultaten</title>
</head>
<body>
<h1>Pagina resultaten</h1>
<h2>${persoon.voornaam} ${persoon.achternaam}</h2>
<div class="resultaatButtons">
	<form method="POST" action="UserController?action=deleteresultaat" name="frmDeleteResult">
		<table class="resultatenlijst">
			<thead>
				<tr>
					<th colspan=1></th>
					<th>Module</th>
					<th>Score</th>
					<th>Voldoende</th>
				</tr>
			</thead>
    		<c:forEach var="resultaat" items="${persoon.resultaten}">
        		<tr>
        			<td class="center">
        				<input type="checkbox" name="check" value="${resultaat.id}"/>
        			</td>
            		<td>${resultaat.modulenaam}</td>
            		<td>${resultaat.resultaat}</td>
            		<td>
            			<c:choose>
            				<c:when test="${resultaat.voldoende}">Ja</c:when>
            				<c:when test="${not resultaat.voldoende}">Nee</c:when>
            			</c:choose>
            		</td>
        		</tr> 
    		</c:forEach>
		</table>
	
		<input type="hidden" name="persoonId" value="${persoon.id}"/>
		<button type="submit" id="verwijderSelectie">Verwijder selectie</button>
	</form>
		<button type="submit" id="addRow">Resultaat toevoegen</button>
	</div>
	
	<div class="addForm"></div>
	
</body>
<script type="text/javascript">   

$(document).ready(function() {
    var max_fields      = 1; //maximum input boxes allowed
    var wrapper         = $(".addForm"); //Fields wrapper
    var add_button      = $("#addRow"); //Add button ID
    
    var x = 0; //initlal text box count
    $(add_button).click(function(){ //on add input button click
        if(x < max_fields){ //max input box allowed
            x++; //text box increment
            $(wrapper).append('<div>' +
            	'<form method="POST" action="UserController?action=addresultaat" name="frmAddResult" class="formResultaat">' +
            	'<label for="modulenaam">Modulenaam:</label>' +
                '<select name="modulenaam">' +
        	  		'<option value="Basis01">Basis01</option>' +
        	  		'<option value="Basis02">Basis02</option>' +
        	  		'<option value="Basis03">Basis03</option>' +
        	  		'<option value="Basis04">Basis04</option>' +
        	  		'<option value="OCA">OCA</option>' +
        		'</select>' +
        		'<label for="resultaat">Resultaat:</label>' +
            	'<input type="number" name="resultaat" min="1" max="10" step="0.1" required/>' +
            	'<label for="voldoende">Voldoende:</label>' +
            	'<select name="voldoende">' +
      	  			'<option value="Ja">Ja</option>' +
      	  			'<option value="Nee">Nee</option>' +
      			'</select>' +
            	'<input type="hidden" name="persoonId" value="${persoon.id}"/>' +
            	'<button type="submit" class="toevoegen">Voeg toe</button>' +
    		'</form>' +
    		'<button type="submit" class="verwijder">Verwijder</button>' +
    		'</div>');
        }
    });
    
    $(wrapper).on("click",".verwijder", function(){ //user click on remove text
    	x--;
    	$(this).parent('div').remove();
    })
});
</script>
</html>