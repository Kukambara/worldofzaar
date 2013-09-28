function Area(x, y, width, height) {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;

	var TO_RADIANS = Math.PI / 180;

	this.Init = function (x, y, width, height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	this.DrawImage = function (context, image) {
		/*context.save();
		context.translate(this.x, this.y);
		context.drawImage(image, 0, 0, this.width, this.height);
		context.restore();*/

		context.drawImage(image, this.x, this.y, this.width, this.height);
	}

	this.DrawRotateBegin = function(context, rotate, image){
		context.save();
		//Чтобы поворот был относительно нашего предмета.
		var rotationX = this.x + this.width / 2;
		var rotationY = this.y + this.height / 2;
		var rotationRadian = TO_RADIANS * rotate;
		/*context.translate(rotationX, rotationY);
		this.DrawImage(context, image);
		context.rotate(TO_RADIANS * rotate);
		this.DrawImage(context, image);

		context.translate(-(this.x + this.width / 2), -(this.y + this.height / 2));
		*/

		context.translate(rotationX, rotationY);
		this.DrawFromBegin(context, image);
		context.rotate(rotationRadian);
		this.DrawFromBegin(context, image);

		/*var rotationX2 = rotationX * Math.cos(rotationRadian) - rotationY * Math.sin(rotationRadian);
		var rotationY2 = +rotationX * Math.sin(rotationRadian) + rotationY * Math.cos(rotationRadian);
*/
		var rotationX2 = rotationX * Math.cos(rotationRadian) - rotationY * Math.sin(rotationRadian);
		var rotationY2 = rotationX * Math.sin(rotationRadian) + rotationY * Math.cos(rotationRadian);
		context.translate(-rotationX2, -rotationY2);
		this.DrawFromBegin(context, image);
	}

	this.DrawRotateEnd = function (context) {
		context.restore();
	}

	this.DrawRotateImage = function (context, image, rotate) {
		this.DrawRotateBegin(context, rotate);

		context.drawImage(
		image,
		-this.width / 2,
		-this.height / 2,
		this.width,
		this.height);

		context.restore();
	}

	this.DrawText = function (context, string, borderX, borderY){
		context.fillText(string, this.x + borderX, y + this.height - borderY, this.width - borderX * 2);
		//context.fillText(string, 50, 150, this.width - borderX * 2);
	}

	this.DrawFromBegin = function (context, image) {
		context.drawImage(image, 0, 0, this.width, this.height);		
	}
}