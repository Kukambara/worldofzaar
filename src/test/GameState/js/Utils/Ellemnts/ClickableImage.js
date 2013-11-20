//must include Area.js
function ClickableImage(area, image, context) {
	this.area = area;
	this.image = image;
	this.context = context;

	ClickableImage.prototype.Init = function (area, image, context) {
		this.area = area;
		this.image = image;
		this.context = context;
	}

	ClickableImage.prototype.Draw = function () {
		this.area.DrawImage(this.context, this.image);
	}

	ClickableImage.prototype.DrawRotation = function (rotate) {
		this.area.DrawRotateImage(this.context, this.image, rotate);
	}

	ClickableImage.prototype.DrawRotateBegin = function (rotate) {
		this.area.DrawRotateImageBegin(this.context, rotate, this.image);
	}

	ClickableImage.prototype.DrawRotateEnd = function () {
		this.area.DrawRotateImageEnd(this.context);
	}

	ClickableImage.prototype.SetSource = function (imageSource) {
		resources.loadByUrl(imageSource);
		//this.image = resources.get(imageSource);
		this.image = new Image(this.area.height, this.area.width);
		this.image.src = imageSource;
	}

	ClickableImage.prototype.SetImage = function (/*Image*/ image) {
		this.image = image;
		this.image.width = this.area.width;
		this.image.height = this.area.height;
	}

	ClickableImage.prototype.DrawBigImage = function () {
		this.context.drawImage(this.image, 800 / 3, 800 / 6, 800 / 3, 400);
	}

	ClickableImage.prototype.OnMouseLeave = function (eventPoint) {
		if (!this.area.IsPointInArea(eventPoint)) {

			//this.area.OnClick(eventPoint);
			//	this.DrawImage(this.context, this.image);
			this.context.clearRect(800 / 3, 800 / 6, 800 / 3, 400);
		}
	}

	ClickableImage.prototype._OnClick = function (Point) {
		this.DrawBigImage();
	}
}

ClickableImage.prototype = new Clickable();

