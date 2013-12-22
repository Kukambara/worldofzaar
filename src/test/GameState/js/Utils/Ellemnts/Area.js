function Area(beginPoint, width, height) {
	this.beginPoint = beginPoint;
	this.width = width;
	this.height = height;
	this.isBorder = true;

	var TO_RADIANS = Math.PI / 180;

	Area.prototype.Init = function (beginPoint, width, height) {
		this.beginPoint = beginPoint;
		this.width = width;
		this.height = height;
	}

	Area.prototype.DrawImage = function (context, image) {
		/*context.save();
		context.translate(this.x, this.y);
		context.drawImage(image, 0, 0, this.width, this.height);
		context.restore();*/
		context.drawImage(image, this.beginPoint.x, this.beginPoint.y, this.width, this.height);
		if (this.isBorder) {
			context.rect(this.beginPoint.x, this.beginPoint.y, this.width, this.height);
			context.stroke();
		}
	}

	Area.prototype.DrawRotateBegin = function (context, rotate) {
		context.save();
		//Чтобы поворот был относительно нашего предмета.

		var rotationX = this.beginPoint.x + this.width / 2;
		var rotationY = this.beginPoint.y + this.height / 2;
		var rotationRadian = TO_RADIANS * rotate;
		/*context.translate(rotationX, rotationY);
		this.DrawImage(context, image);
		context.rotate(TO_RADIANS * rotate);
		this.DrawImage(context, image);

		context.translate(-(this.x + this.width / 2), -(this.y + this.height / 2));
		*/

		//	context.translate(rotationX, rotationY);
		context.translate(this.beginPoint.x, this.beginPoint.y);
		//this.DrawFromBegin(context, image);
		context.rotate(rotationRadian);
		//this.DrawFromBegin(context, image);

		/*var rotationX2 = rotationX * Math.cos(rotationRadian) - rotationY * Math.sin(rotationRadian);
		var rotationY2 = +rotationX * Math.sin(rotationRadian) + rotationY * Math.cos(rotationRadian);
*/
		var rotationX2 = rotationX * Math.cos(rotationRadian) - rotationY * Math.sin(rotationRadian);
		var rotationY2 = rotationX * Math.sin(rotationRadian) + rotationY * Math.cos(rotationRadian);
		//context.translate(-this.width / 2, -this.height / 2 );
	}

	Area.prototype.DrawRotateEnd = function (context) {
		context.restore();
	}

	Area.prototype.DrawRotateImage = function (context, image, rotate) {
		this.DrawRotateBegin(context, rotate);

		context.drawImage(
		image,
		-this.width / 2,
		-this.height / 2,
		this.width,
		this.height);

		context.restore();
	}

	Area.prototype.Clear = function (context) {
		context.clearRect(this.beginPoint.x, this.beginPoint.y, this.width, this.height);
	}

	Area.prototype.DrawText = function (context, string, borderX, borderY) {
		context.fillText(string, this.beginPoint.x + borderX, this.beginPoint.y + this.height - borderY, this.width - borderX * 2);
		if (this.isBorder) {
			context.rect(this.beginPoint.x, this.beginPoint.y, this.width, this.height);
			context.stroke();
		}
		//context.fillText(string, 50, 150, this.width - borderX * 2);
	}

	Area.prototype.DrawFromBegin = function (context, image) {
		context.drawImage(image, 0, 0, this.width, this.height);		
	}

	Area.prototype.GetDiagonalPositionAfterThis = function () {
		return new Point(this.beginPoint.x + this.width, this.beginPoint.y + this.height);
	}
	Area.prototype.GetHorisontalPositionAfterThis = function () {
		return new Point(this.beginPoint.x + this.width, this.beginPoint.y);
	}
	Area.prototype.GetVerticalPositionAfterThis= function () {
		return new Point(this.beginPoint.x, this.beginPoint.y + this.height);
	}

	Area.prototype.IsPointInArea = function (/*Point*/point) {
		var res1 = point.x > this.beginPoint.x;
		var res2 = point.x < this.beginPoint.x + this.width;
		var res3 = point.y > this.beginPoint.y;
		var res4 = point.y < this.beginPoint.y + this.height;
		return res1 && res2 && res3 && res4;
	}

	Area.prototype.OnClick = function (/*POint*/ eventPoint) {
		if (this.IsPointInArea(eventPoint)) {
			this.isBorder = !this.isBorder;
			//alert("Do something");
		}
	}

	Area.prototype.GetCenterWidthPoint = function () {
		return new Point(this.width / 2 + this.beginPoint.x, this.height / 2 + this.beginPoint.y);
	}

	Area.prototype.GetCenterPoint = function () {
		return new Point(this.width / 2 + this.beginPoint.x, this.height / 2 + this.beginPoint.y);
	}
}