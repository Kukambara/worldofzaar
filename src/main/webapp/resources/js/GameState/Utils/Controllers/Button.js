function Button() {
	this.background = [];
	this.backgroundNow;
	this.state;
	this.text;
	this.context;
//	this.prototype = new Observarable();

	Button.prototype.Init = function (/*Area*/ area, /*Image[]*/ background, /*string*/ text, /*context*/context) {
		this.area = area;
		this.text = text;
		this.background = background;
		this.context = context;
		this.backgroundNow = this.background["normal"];
	}

	Button.prototype.Draw = function () {
		this.area.DrawImage(this.context, this.background);
        //this.area.DrawText(this.context, this.text, 5, 5);
	}

	Button.prototype.DrawRotation = function (rotate) {
		this.area.DrawRotateImage(this.context, this.background, rotate);
	}

	Button.prototype.DrawRotateBegin = function (rotate) {
		this.area.DrawRotateImageBegin(this.context, rotate, this.background);
	}

	Button.prototype.DrawRotateEnd = function () {
		this.area.DrawRotateImageEnd(this.context);
	}

	Button.prototype.SetSource = function (imageSource) {
		resources.loadByUrl(imageSource);
		//this.image = resources.get(imageSource);
		this.background = new Image(this.area.height, this.area.width);
		this.background.src = imageSource;
	}

	Button.prototype.SetImage = function (/*Image*/ image) {
		this.background = image;
		this.background.width = this.area.width;
		this.background.height = this.area.height;
	}

	Button.prototype._OnClick = function (eventPoint) {
	}

	Button.prototype.OnMouseLeave = function (eventPoint) {
		if (!this.area.IsPointInArea(eventPoint)) {

			//this.area.OnClick(eventPoint);
			//	this.DrawImage(this.context, this.image);
			this.context.clearRect(800 / 3, 800 / 6, 800 / 3, 400);
		}
	}
}

Button.prototype = new Clickable();