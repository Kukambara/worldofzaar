//must include Area.js
function AreaText(area, text){
	this.area = area;
	this.text = text;
	this.textProperty;
	this.context ;
	this.borderX = 0;
	this.borderY = 0;

	this.background;
	this.textColor;
    this.fontStyle;

	this.Draw = function () {
		this.context.save();
		if (typeof this.background != "undefined") {
			this.context.fillStyle = this.background;
			this.context.fillRect(this.area.beginPoint.x, this.area.beginPoint.y, this.area.width, this.area.height);
		}
        if (typeof this.fontStyle != "undefined") {
            this.context.font = this.fontStyle;
        }
		this.context.fillStyle = this.textColor;
		this.context.textAlign = "center";
		this.context.textBaseline = "middle";
        if (this.area.isBorder) {
            this.DrawBorder();
        }
		var center = this.area.GetCenterPoint();
		this.context.fillText(this.text, center.x, center.y);

		this.context.restore();
		//this.area.DrawText(this.context, this.text, this.borderX, this.borderY);
	}

    this.DrawBorder = function (){
        this.context.beginPath();
        this.context.lineWidth = 4;
        this.context.moveTo(this.area.beginPoint.x, this.area.beginPoint.y);
        this.context.lineTo(this.area.beginPoint.x+this.area.width,this.area.beginPoint.y);
        this.context.lineTo(this.area.beginPoint.x+this.area.width,this.area.beginPoint.y+this.area.height);
        this.context.lineTo(this.area.beginPoint.x,this.area.beginPoint.y+this.area.height);
        this.context.closePath();
        this.context.stroke();
    }

	this.SetBorders = function (borderX, borderY) {
		this.borderX = borderX;
		this.borderY = borderY;
	}

	this.SetTextProperty = function () {
		
	}

    this.SetContext = function (context) {
        this.context = context;
    }
}