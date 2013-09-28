//must include Area.js
function AreaImage(area, image, context) {
	this.area = area;
	this.image = image;
	this.context = context;

	this.Init = function (area, image, context) {
		this.area = area;
		this.image = image;
		this.context = context;
	}

	this.Draw = function () {
		this.area.DrawImage(this.context, this.image);
	}

	this.DrawRotation = function (rotate){
		this.area.DrawRotateImage(this.context, this.image, rotate);
	}

	this.DrawRotateBegin = function (rotate) {
		this.area.DrawRotateImageBegin(this.context, rotate, this.image);
	}

	this.DrawRotateEnd = function () {
		this.area.DrawRotateImageEnd(this.context);
	}

	this.SetSource = function (imageSource) {
		this.image = new Image(this.area.width, this.area.height);
		this.image.src = imageSource;
	}



}