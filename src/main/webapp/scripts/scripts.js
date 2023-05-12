/**
 * 
 */
 
 function PreviewImage() {
	var oFReader = new FileReader();
	oFReader.readAsDataURL(document.getElementById("pictureFile").files[0]);
	
	oFReader.onload = function(oFREvent) {
		document.getElementById("uploadPreview").src = oFREvent.target.result;
	}
}


/**************************************/
//Script to modify radios in filters
const purchasesRadio = document.getElementById('purchases-radio');
const salesRadio = document.getElementById('sales-radio');
const purchasesDiv = document.getElementById('purchasesDiv');
const salesDiv = document.getElementById('salesDiv');
const purchaseCheckboxes = purchasesDiv.querySelectorAll('input[type="checkbox"]');
const salesCheckboxes = salesDiv.querySelectorAll('input[type="checkbox"]');
const openAuctionCheckbox = document.getElementById("open_auction");
const myAuctionsWonCheckbox = document.getElementById("my_auctions_won");


purchasesRadio.addEventListener('change', () => {
  if (purchasesRadio.checked) {
	salesDiv.classList.add('disabled');
	purchasesDiv.classList.remove('disabled');
	salesCheckboxes.forEach((checkbox) => {
		if (checkbox.checked) {
			checkbox.checked = false
		}
	})
  }
});

salesRadio.addEventListener('change', () => {
  if (salesRadio.checked) {
	purchasesDiv.classList.add('disabled');
	salesDiv.classList.remove('disabled');
	purchaseCheckboxes.forEach((checkbox) => {
		if (checkbox.checked) {
			checkbox.checked = false
		}
	})
  }
});

//Script to modify checkboxes in filters
openAuctionCheckbox.addEventListener('change', () => {
  if (openAuctionCheckbox.checked) {
    myAuctionsWonCheckbox.checked = false;
  }
});

myAuctionsWonCheckbox.addEventListener('change', () => {
  if (myAuctionsWonCheckbox.checked) {
    openAuctionCheckbox.checked = false;
  }
});

myAuctionsWonCheckbox.addEventListener('change', () => {
	if (myAuctionsWonCheckbox.checked) {
		openAuctionCheckbox.checked = false
	}
})




