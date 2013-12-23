(function(){
	var fieldHeightSize = 1 / 3;
	var fieldWidthSize = 2 / 3;
	var fieldStepX = (1 - fieldWidthSize)/2;
	var fieldStepY = 1 - fieldHeightSize;
	var borderScaleSize = 63/750;

	var canvases = []
	
	function init(/*string*/canvasStaticName, /*int*/ width){
		canvases[canvasStaticNameConst] = new CanvasWrap();
		canvases[canvasStaticNameConst].Init(canvasStaticName, width);
	}

	function addCanvas(/*string*/canvasName) {
		canvases[canvasName] = new CanvasWrap();
		var width = document.getElementById(canvasName).width;
		canvases[canvasName].Init(canvasName, width);		
	}

	function addCanvasWithWidth(/*string*/canvasName, /*int*/ width) {
		canvases[canvasName] = new CanvasWrap();
		canvases[canvasName].Init(canvasName, width);
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

	function addNewCanvas(/*Area*/ area, /*stting*/ canvasName, /*int*/ zIndex) {
		createCanvas(/*Area*/ area, /*stting*/ canvasName, /*int*/ zIndex);
		addCanvas(canvasName);
	}

	function copyCanvas(/*string*/ canvasNameCopied, /*string*/ canvasNameNew, /*int*/ zIndexNew) {
		var canvasCopied = document.getElementById(canvasNameCopied);
		var newBeginPoint = new Point(canvasCopied.style.left, canvasCopied.style.top);
		var newWidth = canvasCopied.width;
		var newHeight = canvasCopied.height;
		var newArea = new Area(newBeginPoint, newWidth, newHeight);
		addNewCanvas(newArea, canvasNameNew, zIndexNew);
	}

	/*canvas*/function getCanvasByName(/*string*/ name) {
		return this.canvases[name];
	}

	function setCanvasZindex(canvasName, zIndex) {
		var canvas = document.getElementById(canvasName);
		canvas.style.zIndex = zIndex;
		//	document.getElementById(canvasName).style.zIndex = zIndex;
		testZindex = document.getElementById(canvasName).style.zIndex;
		
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
		addCanvas: addCanvas,
		addCanvasWithWidth: addCanvasWithWidth,
		addNewCanvas: addNewCanvas,
		copyCanvas:copyCanvas,
		getCanvasByName:getCanvasByName,
		setCanvasZindex:setCanvasZindex,
		addEventFunction:addEventFunction,
		canvases:canvases
	};

})();