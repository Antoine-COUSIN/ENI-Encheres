//TIMER FOR END AUCTIONS
var end_auction = document.querySelector('[data-end]').getAttribute("data-end");
function updateTimer() {
	// Calcul de la différence entre la date de fin de l'enchère et la date actuelle
	var diff = Math.floor((new Date(end_auction) - new Date()) / 1000);
	
	// Conversion de la différence en heures, minutes et secondes
	var hours = Math.floor(diff / (60 * 60));
	var minutes = Math.floor(diff / 60) % 60;
	var seconds = diff % 60;
	
	// Mise à jour de l'affichage du minuteur
	var timer = document.getElementById("timer");
	if (diff <= 0) {
		timer.innerHTML = "Enchère terminée";
	} else {
		timer.innerHTML = hours + ":" + (minutes < 10 ? "0" : "") + minutes + ":" + (seconds < 10 ? "0" : "") + seconds;
	}
}

// Mise à jour du minuteur toutes les secondes
setInterval(updateTimer, 1000);