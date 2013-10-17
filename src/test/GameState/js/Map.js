function Map(x, y, width, height) {

	var fieldHeightSize = 1 / 3;
	var fieldWidthSize = 2 / 3;
	var fieldStepX = (1 - fieldWidthSize)/2;
	var fieldStepY = 1 - fieldHeightSize;


	/*this.area = new Area();
	this.area.Init(x, y, width, height);*/
	this.background = new AreaImage(
			new Area(new Point(x, y), width, height),
			new Image(width, height));
	this.background.image.src = "Picture/table.jpg";

	this.playerField = [];
	this.playerField[0] = new PlayerField();
	this.playerField[0].InitAreas(x + width * fieldStepX, y + height * fieldStepY, width * fieldWidthSize, height * fieldHeightSize);
	this.playerField[0].InitTexts();
	this.playerField[0].SetSourceHightElf();

	this.playerField[1] = new PlayerField();
	this.playerField[1].rotate = -90;
	this.playerField[1].InitAreas(x + width * fieldStepY, y + height * (1 - fieldStepX), width * fieldWidthSize, height * fieldHeightSize);
	this.playerField[1].InitTexts();
	this.playerField[1].SetSourceHightElf();

	this.playerField[2] = new PlayerField();
	this.playerField[2].rotate = 90;
	//this.playerField2.InitAreas(x + width * fieldStepX, y + height * fieldStepY, width * fieldWidthSize, height * fieldHeightSize);
	this.playerField[2].InitAreas(x + width * (1 - fieldStepY), y + height * fieldStepX, width * fieldWidthSize, height * fieldHeightSize);
	this.playerField[2].InitTexts();
	this.playerField[2].SetSourceHightElf();

	this.playerField[3] = new PlayerField();
	this.playerField[3].rotate = -180;
	this.playerField[3].InitAreas(x + width * (1 - fieldStepX), y + height * (1 - fieldStepY), width * fieldWidthSize, height * fieldHeightSize);
	this.playerField[3].InitTexts();
	this.playerField[3].SetSourceHightElf();

	this.OnClick = function (event) {
		if (event.x && event.y) {
			event.x -= this.background.area.beginPoint.x;
			event.y -= this.background.area.beginPoint.y;
			for (var i = 0; i < this.playerField.length; ++i) {
				this.playerField[i].OnClick(event);
			}
		}
	}

	function OnClick(event) {
		if (event.x && event.y) {
			var eventPoint = new Point(event.x, event.y);
			if (background.area.IsPointInArea(eventPoint)) {
				eventPoint.MinusFromThis(background.area.beginPoint);
				for (var i = 0; i < playerField.length; ++i) {
					playerField[i].OnClick(eventPoint);
				}
			} else {
				for (var i = 0; i < playerField.length; ++i) {
					playerField[i].OnMouseLeave(eventPoint);
				}
			}
		}
	}
	function OnMouseLeave(event) {
		if (event.x && event.y) {
			var eventPoint = new Point(event.x, event.y);
			if (!background.area.IsPointInArea(eventPoint)) {
				eventPoint.MinusFromThis(background.area.beginPoint);
				for (var i = 0; i < playerField.length; ++i) {
					playerField[i].OnMouseLeave(eventPoint);
				}
			}
		}
	}
	this.canvas = document.getElementById("canvas");

//	this.canvas.addEventListener("click", OnClick);
	this.canvas.addEventListener("mousemove", OnClick);

	this.canvas.addEventListener("mousemove", OnMouseLeave);

	if (this.canvas.getContext) {
		this.background.context = this.canvas.getContext("2d");
		for (var i = 0; i < this.playerField.length; ++i ){
			this.playerField[i].SetContext(this.background.context);
		}
		this.background.context.font = "15pt Arial";
	} else {
		alert("Sorry, but your browser isn`t support canvas:(");
	}

	this.Draw = function () {
		//this.background.Draw();
		for (var i = 0; i < this.playerField.length; ++i) {
			this.playerField[i].Draw();
		}
	}

	this.Draw();
}
/*
var one = {
	callBackOne: function (e) {
		alert("Вызван подписчик ONE: " + e.message);
	}
};

var two = {
	callBackTwo: function (e) {
		alert("Вызван подписчик TWO: " + e.message);
	}
};

// Регистрируем наблюдателей для событий someEventForOne и someEventForTwo:
observerable.addListener(one, "someEventForOne", "callBackOne");
observerable.addListener(two, "someEventForTwo", "callBackTwo");
// Теперь допустим, что то произошло и мы в коде вызвали события
// someEventForOne и someEventForTwo, а заодно передаём 
// какую нибудь полезную информацию :
observerable.triggerEvent("someEventForOne", { message: "i am one event" });
observerable.triggerEvent("someEventForTwo", { message: "i am two event" });

*/