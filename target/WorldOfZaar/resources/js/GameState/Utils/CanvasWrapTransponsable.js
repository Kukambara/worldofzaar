function CanvasWrapTransponsable(){
	this.__proto__ = new CanvasWrap();
	
	this.angle;

	this.init = function (/*int*/angle) {
		this.__proto__.init();

		this.angle = angle;
	}


}