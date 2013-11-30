function Point(x, y) {
	var TO_RADIANS = Math.PI / 180;

	this.x = x ? x : 0;
	this.y = y ? y : 0;

	this.AddToThis = function(/*Point*/ secondPoint){
		this.x += secondPoint.x;
		this.y += secondPoint.y;
	}

	this.MinusFromThis = function(/*Point*/ subtrahend/*����������*/){
		this.x -= subtrahend.x;
		this.y -= subtrahend.y;
	}

	this.MinusThis = function(/*Point*/ minuend/*�����������*/){
		this.x = minuend.x - this.x;
		this.y = minuend.y - this.y;
	}

	this.Rotation = function (rotate) {
		var x = this.x;
		var y = this.y;
		this.x = x * Math.cos(rotate) - y * Math.sin(rotate);
		this.y = x * Math.sin(rotate) + y * Math.cos(rotate);
	}

	this.RotationAcrosPoint = function (/*Point*/ point, /*int*/ rotate) {
		//�������� �� ����� ����� �������, ������������ ������� ����� �������� �������, ����� ������� ��� ������������ ��� �������.
		this.MinusFromThis(point);	
		this.Rotation(rotate * TO_RADIANS);	//��� ������������� ����� ���������� �������� ����.
		this.AddToThis(point); //����� �������� �������� ������������ �������.
	}
}