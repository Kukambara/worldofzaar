function Map() {
	var fieldHeightSize = 1 / 3;
	var fieldWidthSize = 2 / 3;
	var fieldStepX = (1 - fieldWidthSize)/2;
	var fieldStepY = 1 - fieldHeightSize;
	var borderScaleSize = 63/750;

	//this.backgroundFull;
	this.background;
	this.playerField = [];
	this.canvas;
	//this.scale = 0.5;

	this.Init = function (x, y, width, height) {
		this.canvas = canvasManager.getCanvasStatic();//document.getElementById("canvas");
		resources.loadByArr([
				"Picture/map.png",
				"Picture/table.png"]);
	/*	this.backgroundFull = new AreaImage(
			new Area(new Point(0, 0), 1100, 1100),
			new Image(1000, 1000));*/
		var borderSize = borderScaleSize * width;
//		this.backgroundFull.image.src = /*resources[*/"Picture/table.png"/*]*/;
		this.background = new AreaImage(
				new Area(new Point(x - borderSize, y - borderSize), width + borderSize * 2, height + borderSize * 2),
				new Image(width, height));
		this.background.image.src = "Picture/map.png";

		this.playerField = [];
		this.playerField[0] = new PlayerField();
		this.playerField[0].InitAreas(x + width * fieldStepX, y + height * fieldStepY, width * fieldWidthSize, height * fieldHeightSize);
		this.playerField[0].InitTexts();

		this.playerField[1] = new PlayerField();
		this.playerField[1].rotate = -90;
		this.playerField[1].InitAreas(x + width * fieldStepY, y + height * (1 - fieldStepX), width * fieldWidthSize, height * fieldHeightSize);
		this.playerField[1].InitTexts();

		this.playerField[2] = new PlayerField();
		this.playerField[2].rotate = 90;
		//this.playerField2.InitAreas(x + width * fieldStepX, y + height * fieldStepY, width * fieldWidthSize, height * fieldHeightSize);
		this.playerField[2].InitAreas(x + width * (1 - fieldStepY), y + height * fieldStepX, width * fieldWidthSize, height * fieldHeightSize);
		this.playerField[2].InitTexts();

		this.playerField[3] = new PlayerField();
		this.playerField[3].rotate = -180;
		this.playerField[3].InitAreas(x + width * (1 - fieldStepX), y + height * (1 - fieldStepY), width * fieldWidthSize, height * fieldHeightSize);
		this.playerField[3].InitTexts();

		//this.canvas.setNewDrawableAreaByWidth(width / 750 * 1000);

		//this.canvas = document.getElementById("canvas");

	//	this.canvas.addEventListener("click", this.OnClick);
	//	this.canvas.addEventListener("click", OnClick);
		//this.canvas.addEventListener("mousemove", this.OnClick);

		//	this.canvas.addEventListener("mousemove", OnMouseLeave);
	/*	if (this.canvas.getContext) {
			this.backgroundFull.context = this.canvas.getContext("2d");
			this.backgroundFull.context.scale(this.scale, this.scale);
			this.background.context = this.canvas.getContext("2d");
			for (var i = 0; i < this.playerField.length; ++i) {
				this.playerField[i].SetContext(this.background.context);
			}
			this.background.context.font = "15pt Arial";
		} else {
			alert("Sorry, but your browser isn`t support canvas:(");
		}
		*/
	//	this.backgroundFull.context = this.canvas.getContext();
		this.background.context = this.canvas.getContext();
		for (var i = 0; i < this.playerField.length; ++i) {
			this.playerField[i].SetContext(this.background.context);
		}
		this.background.context.font = "15pt Arial";
		this.playerField[0].SetSourceHightElf();
		this.playerField[1].SetSourceDarkElf();
		this.playerField[2].SetSourceVeld();
		this.playerField[3].SetSourceBalikuru();
	}

	this.OnClick = function (/*Point*/event) {
		if (event.x && event.y) {
			var eventPoint = new Point(event.x, event.y);
			if (this.background.area.IsPointInArea(eventPoint)) {
				eventPoint.MinusFromThis(this.background.area.beginPoint);
				for (var i = 0; i < this.playerField.length; ++i) {
					this.playerField[i].OnClick(eventPoint);
				//	this.playerField[i].onClick(eventPoint);
				}
			} else {
				for (var i = 0; i < this.playerField.length; ++i) {
					this.playerField[i].OnMouseLeave(eventPoint);
				}
			}
		}
	}

	function OnClick(/*Point*/event) {
		if (event.x && event.y) {
			var eventPoint = new Point(event.x, event.y);
			if (background.area.IsPointInArea(eventPoint)) {
				eventPoint.MinusFromThis(background.area.beginPoint);
				for (var i = 0; i < playerField.length; ++i) {
					//playerField[i].OnClick(eventPoint);
					playerField[i].onClick(eventPoint);
				}
			} else {
				for (var i = 0; i < playerField.length; ++i) {
					playerField[i].OnMouseLeave(eventPoint);
				}
			}
		}
	}

	//this.canvas.onclick = OnClick;
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

	this.Draw = function () {
		//this.backgroundFull.Draw();
		this.background.Draw();
		for (var i = 0; i < this.playerField.length; ++i) {
			this.playerField[i].Draw();
		}
	}

	//this.Draw();
}
/*
var map;
var button = new Button();
function init() {
	map = new Map();
	map.Init(50, 50, 750, 750);

	button.Init(new Area(new Point(10, 100), 100, 50), map.background.image, "test", canvasInstance.getContext());
	canvasInstance.addListener(this, "onClick", "onClickListener");
	button._OnClick = onButtonClick;

	/*card = new Card();
	card.Init(new Area(new Point(300, 50), 200, 320));
	card.SetContext(canvasInstance.getContext());*/
/*	map.canvas.onclick = function (e) {
		map.OnClick(new Point(e.x, e.y));
	}*/
	//map.Draw();
/*	resources.onReady(draw);
}*/
/*
function onButtonClick(eventPoint){
	alert("test");
}

function onClickListener(eventPoint) {
	map.OnClick(eventPoint);
	button.onClick(eventPoint);
}

function draw() {
	map.Draw();
	button.Draw();
}*/
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