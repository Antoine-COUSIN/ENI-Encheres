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