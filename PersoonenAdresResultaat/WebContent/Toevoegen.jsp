<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagina wijzigen/toevoegen</title>
</head>
<body>
<h1>Pagina wijzigen/toevoegen</h1>

<form method="POST" action='UserController?action=addpersoon' name="frmAddUser" class="formToevoegen">
		<label for="voornaam">Voornaam:</label>
        <input type="text" name="voornaam" 
            value="<c:out value="${persoon.voornaam}"/>" required/> <br /> 
        <label for="achternaam">Achternaam:</label>
        <input type="text" name="achternaam" 
            value="<c:out value="${persoon.achternaam}"/>" required/> <br /> 
        <label for="tussenvoegsel">Tussenvoegsel:</label>
        <input type="text" name="tussenvoegsel"
            value="<c:out value="${persoon.tussenvoegsel}" />" /> <br /> 
        <label for="geboortedatum">Geboortedatum:</label>
        <input type="text" name="geboortedatum" 
            value="<c:out value="${persoon.geboortedatum}"/>" required/> <br />
        <label for="straatnaam">Straatnaam:</label>
        <input type="text" name="straatnaam"
            value="<c:out value="${persoon.adres.straatnaam}"/>" required/> <br />
        <label for="huisnummer">Huisnummer:</label>
        <input type="text" name="huisnummer"
            value="<c:out value="${persoon.adres.huisnummer}"/>" required/> <br />
        <label for="toevoeging">Toevoeging:</label>
       	<input type="text" name="toevoeging"
            value="<c:out value="${persoon.adres.toevoeging}" />" /> <br />
        <label for="postcode">Postcode:</label>
       	<input type="text" name="postcode"
            value="<c:out value="${persoon.adres.postcode}"/>" required/> <br />
        <label for="woonplaats">Woonplaats:</label>
        <input type="text" name="woonplaats"
            value="<c:out value="${persoon.adres.woonplaats}"/>" required/> <br /> 
        <input type="hidden" readonly="readonly" name="persoonId"
            value="<c:out value="${persoon.id}" />" />
        <input type="hidden" readonly="readonly" name="adresId"
            value="<c:out value="${persoon.adres.id}" />" />
        <button type="submit">Wijzig/Voeg Toe</button>
            
    </form>

</body>
</html>