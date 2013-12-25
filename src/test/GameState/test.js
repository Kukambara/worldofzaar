var canvas;
var context;


function clear() {
	context.clearRect(0, 0, 1000, 1000);
}

function test(canvasName) {
	canvas = document.getElementById(canvasName);
	context;
	if (canvas.getContext) {
		context = canvas.getContext("2d");
	} else {
		alert("Sorry, but your browser isn`t support canvas:(");
	}
	context.beginPath();
	context.rect(0, 0, 1000, 1000);
	context.fillStyle = 'yellow';
	context.fill();

	canvas.onclick = function (e) {
		clear();
	}
}