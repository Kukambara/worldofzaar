var lastTime;

var map = new Map();
var buttonTest = new Button();

function initGame() {	
	map.Init(50, 50, 750, 750);

	buttonTest.Init(new Area(new Point(10, 100), 100, 50), map.background.image, "test", canvasInstance.getContext());
	buttonTest._OnClick = onButtonTestClick;
	canvasInstance.addListener(this, "onClick", "onClickListener");
	resources.onReady(draw);
}

function main() {
	var now = Date.now();
	var deltaTime = (now - loastTime) / 1000.0;

	update(deltaTime);
	render();

	lastTime = now;

}

function update(deltaTime){
	alert(deltaTime);
}

function render() {
	alert("render");
}

function onClickListener(eventPoint) {
	map.OnClick(eventPoint);
	buttonTest.onClick(eventPoint);
}

function draw() {
	map.Draw();
	buttonTest.Draw();
}

/*Clicks*/
function onButtonTestClick(eventPoint) {
	alert("test");
}
