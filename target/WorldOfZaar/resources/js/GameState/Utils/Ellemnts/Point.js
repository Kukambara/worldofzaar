function Point(x, y) {
	var TO_RADIANS = Math.PI / 180;

	this.x = x ? x : 0;
	this.y = y ? y : 0;

	Point.prototype.AddToThis = function(/*Point*/ secondPoint){
		this.x += secondPoint.x;
		this.y += secondPoint.y;
	}

	Point.prototype.MinusFromThis = function (/*Point*/ subtrahend/*����������*/) {
		this.x -= subtrahend.x;
		this.y -= subtrahend.y;
	}

	Point.prototype.MinusThis = function (/*Point*/ minuend/*�����������*/) {
		this.x = minuend.x - this.x;
		this.y = minuend.y - this.y;
	}

	Point.prototype.Rotation = function (rotate) {
		var x = this.x;
		var y = this.y;
		this.x = x * Math.cos(rotate) - y * Math.sin(rotate);
		this.y = x * Math.sin(rotate) + y * Math.cos(rotate);
	}

	Point.prototype.RotationAcrosPoint = function (/*Point*/ point, /*int*/ rotate) {
		//�������� �� ����� ����� �������, ������������ ������� ����� �������� �������, ����� ������� ��� ������������ ��� �������.
		this.MinusFromThis(point);	
		this.Rotation(rotate * TO_RADIANS);	//��� ������������� ����� ���������� �������� ����.
		this.AddToThis(point); //����� �������� �������� ������������ �������.
	}

    Point.prototype.ConvertStyleValue = function(styleValue){
        return Number(styleValue.replace("px",""));
    }

    Point.prototype.ConvertThisFromStyle = function(){
        this.x = this.ConvertStyleValue(this.x);
        this.y = this.ConvertStyleValue(this.y);
    }

    Point.prototype.Multiply = function(scale){
        this.x *= scale;
        this.y *= scale;
    }
}