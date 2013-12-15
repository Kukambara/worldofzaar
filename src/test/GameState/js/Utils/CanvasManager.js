(function(){
	var fieldHeightSize = 1 / 3;
	var fieldWidthSize = 2 / 3;
	var fieldStepX = (1 - fieldWidthSize)/2;
	var fieldStepY = 1 - fieldHeightSize;
	var borderScaleSize = 63/750;
	
	var canvasStaticNameConst = "static"

	var canvases = []
	
	function init(/*string*/canvasStaticName, /*int*/ width){
		canvases[canvasStaticNameConst] = new CanvasWrap();
		canvases[canvasStaticNameConst].Init(canvasStaticName, width);
		//var canvasArea = new Area(new Point(canvasStatic.style.left,canvasStatic.style.top),canvasStatic.width*3/4, canvasStatic.height*3/4 );
		var canvasArea = new Area(new Point(50, 150), canvases[canvasStaticNameConst].width * 3 / 4, canvases[canvasStaticNameConst].height * 3 / 4);
		createCanvas(canvasArea, "canvasMenu", 1);
		canvases["testCanvas"] = new CanvasWrap();
		canvases["testCanvas"].Init("canvasMenu", canvasArea.width);
			
	/*	var context = canvasMenu.getContext("2d");		
		context.scale(2, 2);*/
	}

	/*canvas*/ function createCanvas(/*Area*/ area, /*stting*/ name, /*int*/ zIndex) {
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

	/*canvas*/function getCanvasByName(/*string*/ name) {
		return this.canvases[name];
	}

	function addCanvas(/*String*/ name, /*int*/ newWidth) {
		this.canvases[name] = new CanvasWrap();
		this.canvases[name].Init(name, newWidth);
	}

	function getCanvasStatic(){
		return canvases[canvasStaticNameConst];
	}

	function getCanvasMenu(){
		return canvases["menu"];
	}

	function getCanvasPlayerField(/*int*/index){
		//return canvasPlayerField[index];
	}

	function addEventFunction(/*string*/ nameOfCanvas, /*obj*/ object,  /*string*/ eventName, /*function*/ onClickFunction) {
		canvases[nameOfCanvas].addListener(object, eventName, onClickFunction);
		canvases[nameOfCanvas].canvas.onclick = function (e) {
			canvasManager.canvases[nameOfCanvas].onClick(e);
		}
	}

	function onCanvasMenuClick(e){
		alert("hi");
	}

	window.canvasManager = {
		init: init,
		getCanvasStatic: getCanvasStatic,
		getCanvasMenu: getCanvasMenu,
		getCanvasPlayerField: getCanvasPlayerField,
		addEventFunction:addEventFunction,
		canvases:canvases
	};

})();