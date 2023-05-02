<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="<%=request.getContextPath() %>/styles/styles.css" rel="stylesheet" />
</head>
<body>

<%@include file="../fragments/header.jspf" %>


<div class="login-container">
	<form action="login" method="POST">
		<div>
			<label for="login">Identifiant :</label>
			<input type="text" id="login" name="login"/>
		</div>
		<div>
			<label for="password">Mot de Passe</label>
			<input type="password" id="password" name="password" />
		</div>
		
		<div>
			<input type="submit" value="Connexion"/>
			
			<div>
				<label for="rememberLog">Se souvenir de moi</label>
				<input type="checkbox" id="rememberLog" name="rememberLog" />
				
				<a href="#">Mot de passe oublié</a>
			</div>
		</div>
  	</form>
</div>



</body>
</html>