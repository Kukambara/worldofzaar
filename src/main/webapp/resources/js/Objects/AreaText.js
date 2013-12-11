//must include Area.js
function AreaText(area, text){
	this.area = area;
	this.text = text;
	this.textProperty;
	this.context = 0;
	this.borderX = 0;
	this.borderY = 0;

	this.background;
	this.textColor;

	this.Draw = function () {
		this.context.save();
		if (typeof this.background !== "undefined") {
			this.context.fillStyle = this.background;
			this.context.fillRect(this.area.beginPoint.x, this.area.beginPoint.y, this.area.width, this.area.height);
		}
		this.context.fillStyle = this.textColor;
		this.context.textAlign = "center";
		this.context.textBaseline = "middle";
		if (this.area.isBorder) {
			this.context.rect(this.area.beginPoint.x, this.area.beginPoint.y, this.area.width, this.area.height);
			this.context.stroke();
		}
		var center = this.area.GetCenterPoint();
		this.context.fillText(this.text, center.x, center.y);

		this.context.restore();
		//this.area.DrawText(this.context, this.text, this.borderX, this.borderY);
	}
	
	this.SetBorders = function (borderX, borderY) {
		this.borderX = borderX;
		this.borderY = borderY;
	}

	this.SetTextProperty = function () {
		
	}
}