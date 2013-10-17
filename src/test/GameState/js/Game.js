var lastTime;

function main() {
	var now = Date.now();
	var deltaTime = (now - loastTime) / 1000.0;

	update(deltaTime);
	render();

	lastTime = now;
	requestAnimationFrame(main);

}

function update(deltaTime){
	
}

function render() {
	
}