<%@include file="../fragments/header.jspf" %>



<h1>TODO : Update profile here (like create-user form)</h1>

<div>
	<p>Pseudo : ${ loggedUser.pseudo }</p>
	<p>Nom : ${ loggedUser.lastName }</p>
	<p>Prénom : ${ loggedUser.firstName }</p>
	<p>Email : ${ loggedUser.email }</p>
	<p>Téléphone : ${ loggedUser.phoneNumber }</p>
	<p>Rue : ${ loggedUser.streetAddress }</p>
	<p>Code postal : ${ loggedUser.postalCodeAddress }</p>
	<p>Ville : ${ loggedUser.cityAddress }</p>
	
	<a href="updateProfile" >Modifier</a>
	
</div>






<%@include file="../fragments/footer.jspf" %>