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
		resources.loadByUrl(imageSource);
		this.image = new Image(this.area.height, this.area.width);
		this.image.src = imageSource;
	}

	this.SetImage = function (/*Image*/ image) {
		this.image = image;
		this.image.width = this.area.width;
		this.image.height = this.area.height;
	}

	this.DrawBigImage = function () {
		this.context.drawImage(this.image, 800 / 3, 800 / 6, 800 / 3, 400);
	}
}

