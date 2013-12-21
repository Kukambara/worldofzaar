(function(){
	var lastTime;

	var background;
	var map = new Map();
	var buttons = [];
	var bank;
	var chat;
	var log;
	var state;
	var activePlayerNUmber;

	window.requestAnimFrame = (function (callback) {
		return window.requestAnimationFrame || window.webkitRequestAnimationFrame
		|| window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame ||
		function (callback) {
			window.setTimeout(callback, 1000 / 60);
		};
	})();

	function init() {
		canvasManager.init('canvas_static', 1000)
		_initBackground();
		map.Init(50, 50, 760, 760);

		buttons["test"] = new Button();
		buttons["test"].Init(new Area(new Point(10, 100), 100, 50), map.background.image, "test", canvasManager.getCanvasStatic().getContext());
		buttons["test"]._OnClick = onButtonTestClick;
		canvasManager.addEventFunction("static", this, "onClick", "onClickListener");
		lastTime = Date.now();
		resources.onReady(drawStatic);
	}

	function _initBackground() {
		resources.loadByUrl("Picture/table.png");
		background = new AreaImage(
					new Area(new Point(0, 0), 1000, 1000),
					new Image(1000, 1000));
		background.image.src = "Picture/table.png";
		background.context = canvasManager.getCanvasStatic().getContext();
	}

	function main() {
		var now = Date.now();
		var deltaTime = (now - lastTime) / 1000.0;

		update(deltaTime);
		draw();

		lastTime = now;
		requestAnimFrame(main);

	}

	function update(deltaTime) {
		//alert(deltaTime);
	}

	function render() {
		alert("render");
	}

	function onClickListener(eventPoint) {
	
		map.OnClick(eventPoint);
		//buttonTest.onClick(eventPoint);
	}

	function drawStatic() {

		background.Draw();
		map.Draw();
		main();
	}

	function draw() {
		map.Draw();
		//buttonTest.Draw();
	}

	/*Clicks*/
	function onButtonTestClick(eventPoint) {
		alert("onButtonTestClick");
	}

	window.game = {
		init: init
		, onClickListener: onClickListener
	}
})();