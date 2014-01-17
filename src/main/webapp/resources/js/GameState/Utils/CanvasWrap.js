function CanvasWrap() {
	//this.__proto__ = new Observable();
	this.listeners = new Array();

	this.canvas;
	this.context;
	this.scale = 1;
	var scale1;

	CanvasWrap.prototype.Init = function (/*string*/name, /*int*/ newWidth) {
		this.canvas = document.getElementById(name);
		if (this.canvas.getContext) {
			this.context = this.canvas.getContext("2d");
		} else {
			alert("Sorry, but your browser isn`t support canvas:(");
		}
		this.scale = newWidth / this.canvas.width;
		scale1 = this.scale;
		this.context.scale(this.scale, this.scale);
		//this.canvas.onclick = this.onClickHelper(e, this);
		/*function (e) {
			this.onClick(new Point(e.x * this.scale, e.y * this.scale));
		}*/
	//	this.canvas.addEventListener("onClick", onClick);
	}

	/*canvas*/ CanvasWrap.prototype.createCanvas = function(/*Area*/ area, /*stting*/ name, /*int*/ zIndex) {
		var canvas = document.createElement('canvas');
		canvas.id = name;
		canvas.style.position = "absolute";
		canvas.style.top = area.beginPoint.y;
		canvas.style.left = area.beginPoint.x;
		canvas.width = area.width;
		canvas.height = area.height;
		canvas.style.zIndex = zIndex;
		canvas.style.border = "1px solid";

		var body = document.getElementsByTagName("body")[0];
		body.appendChild(canvas);
		return canvas;
	}

	CanvasWrap.prototype.getContext = function () {
		return this.context;
	}

	CanvasWrap.prototype.setNewDrawableAreaByWidth = function (/*int*/ newWidth) {
		var oldWidth = this.canvas.width;
		this.scale = oldWidth / newWidth;
		this.context.scale(this.scale, this.scale);
	}

	CanvasWrap.prototype.onClick = function (eventPoint) {
		var point = new Point(eventPoint.layerX / this.scale, eventPoint.layerY / this.scale);
		this.triggerEvent("onClick", point);
	}

	/*Area*/CanvasWrap.prototype.getCanvasArea = function () {
		return new Area(new Point( parseInt(this.canvas.style.left), parseInt(this.canvas.style.top)),
            this.canvas.width, this.canvas.height);
	}

	/*Area*/CanvasWrap.prototype.getContextArea = function () {
		return new Area(new Point(parseInt(this.canvas.style.left), parseInt(this.canvas.style.top)),
            this.canvas.width * this.scale, this.canvas.height * this.scale);
	}

	CanvasWrap.prototype.onClickHelper = function (/*Point*/ point, /*CanvasWrap*/ canvasWrap) {
		canvasWrap.onClick(point.layerX * canvasWrap.scale, point.layerY * canvasWrap.scale)
	}

	CanvasWrap.prototype.clear = function () {
		this.context.clearRect(0, 0, this.canvas.width, this.canvas.height);
	}
	
	//this.canvas.addEventListener("click", onClick);
};
CanvasWrap.prototype = new Observable();