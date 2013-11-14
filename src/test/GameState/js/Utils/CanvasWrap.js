function CanvasWrap() {
	if (typeof canvasInstance !== "undefined") {
		return canvasInstance;
	}

	this.listeners = [];
	var baseWidth = 1000;

	this.canvas;
	this.context;
	this.scale;

	this.Init = function (newWidth) {
		this.canvas = document.getElementById("canvas");
		if (this.canvas.getContext) {
			this.context = this.canvas.getContext("2d");
		} else {
			alert("Sorry, but your browser isn`t support canvas:(");
		}
		this.scale = newWidth / baseWidth;
		this.context.scale(this.scale, this.scale);
		canvasInstance.canvas.onclick = function (e) {
			canvasInstance.onClick(new Point(e.x, e.y));
		}
	//	this.canvas.addEventListener("onClick", onClick);
	}

	this.getContext = function () {
		return this.context;
	}

	this.onClick = function (eventPoint) {
		var point = new Point(eventPoint.x / this.scale, eventPoint.y / this.scale);
		this.triggerEvent("onClick", point);
	}

	//this.canvas.addEventListener("click", onClick);
};
CanvasWrap.prototype = new Observable();

canvasInstance = new CanvasWrap();