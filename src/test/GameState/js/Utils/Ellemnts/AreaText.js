//must include Area.js
function AreaText(area, text){
	this.area = area;
	this.text = text;
	this.textProperty;
	this.context = 0;
	this.borderX = 0;
	this.borderY = 0;

	this.Draw = function () {
		this.area.DrawText(this.context, this.text, this.borderX, this.borderY);
	}



	this.SetBorders = function (borderX, borderY) {
		this.borderX = borderX;
		this.borderY = borderY;
	}

	this.SetTextProperty = function () {
		
	}
}