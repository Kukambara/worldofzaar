(function () {
	var lastTime;

	var background;
	var map = new Map();

	var canvasesName = [];
	var buttons = [];
	var players = [];
	var state;
	var activePlayerNUmber;

	var time;
	var timer;

	window.requestAnimFrame = (function (callback) {
		return window.requestAnimationFrame || window.webkitRequestAnimationFrame
		|| window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame ||
		function (callback) {
			window.setTimeout(callback, 1000 / 60);
		};
	})();

	function init(/*string*/ canvasLoadingName) {
		canvasesName["loading"] = canvasLoadingName;
		canvasManager.addCanvas(canvasLoadingName);
		canvasManager.addEventFunction(canvasLoadingName, this, "onClick", "onButtonTestClick");

		var ctx = canvasManager.getCanvasByName(canvasLoadingName).context
		ctx.fillStyle = "#00ff00";
		ctx.fillRect(0, 0, 200, 200);

		var canvasStaticName = "canvas_static";
		canvasesName["static"] = canvasStaticName;
		canvasManager.copyCanvas(canvasLoadingName, canvasStaticName, 0);
		_initBackground();
		map.Init(canvasManager.getCanvasByName(canvasStaticName), 50, 50, 700, 700);

		var canvasControlName = "canvas_control";
		canvasesName["control"] = canvasControlName;
		canvasManager.copyCanvas(canvasStaticName, canvasControlName, 1);

		buttons["test"] = new Button();
		buttons["test"].Init(new Area(new Point(700, 800), 100, 100), map.background.image, "test", canvasManager.getCanvasByName(canvasControlName).getContext());
		buttons["test"]._OnClick = onButtonTestClick;
		canvasManager.addEventFunction(canvasControlName, this, "onClick", "onClickListener");

		timer = new AreaText(new Area(new Point(850, 800), 50, 25), 0);
		timer.context = canvasManager.getCanvasByName(canvasControlName).getContext();
		time = 0;
		canvasManager.getCanvasByName(canvasControlName).getContext().font = "15pt Arial";

		lastTime = Date.now();
		resources.onReady(postInit);
	}

	function _initBackground() {
		resources.loadByUrl("/resources/Images/GameState/table.png");
		background = new AreaImage(
					new Area(new Point(0, 0), 1000, 1000),
					new Image(1000, 1000));
		background.image.src = "/resources/Images/GameState/table.png";
		background.context = canvasManager.getCanvasByName(canvasesName["static"]).getContext();
	}

	function main() {
		var now = Date.now();
		var deltaTime = (now - lastTime) / 1000.0;
		update(deltaTime);
		//draw();

		lastTime = now;
		requestAnimFrame(main);

	}

	function update(deltaTime) {
		time += deltaTime;
		timer.DrawNewText(Math.floor(time));
		//alert(deltaTime);
	}

	function render() {
		alert("render");
	}

	function onClickListener(eventPoint) {

		map.OnClick(eventPoint);
		buttons["test"].onClick(eventPoint);
	}

	function postInit() {
		canvasManager.setCanvasZindex(canvasesName["loading"], -1);
		background.Draw();
		map.Draw();
		main();
		buttons["test"].Draw();
		timer.Draw();
	}

	function draw() {
		//map.Draw();
		//buttonTest.Draw();
	}

	/*Clicks*/
	function onButtonTestClick(eventPoint) {
		alert("onButtonTestClick");
	}

	/*string*/function ConvertSecondIntoString(/*int*/ seconds) {
		var timeWithOutMIllicesonds = Math.floor(seconds);
		var minutes = Math.floor(timeWithOutMIllicesonds / 60);
		var onlyseconds = seconds % 60;
		return minutes + ":" + onlyseconds;
	}

	window.game = {
		init: init
		, onClickListener: onClickListener
		, onButtonTestClick: onButtonTestClick
	}
})();