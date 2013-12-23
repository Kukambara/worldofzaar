function PlayerField() {
	this.listener;
	var sizeOfHeightStep = 1/2;
	var sizeOfHpAreaWidth = 1 / 8;
	var sizeOfOneCardEllement = 2 * sizeOfHpAreaWidth;
	var sizeOfHpAreaHeight = 1 / 2;
	var sizeOfTsAreaHeight = 1 / 3;

	var fighterCount = 3;
	this.fighterCardAreas = new Array();

	this.fullArea;

	this.areas = []
	this.cards = [];
	
	this.rotate = 0;	//градусы
	this.context;

	this.FighterAreasInit = function(x, y, width, height, count) {
		this.fighterCardAreas = new Array(count);
		var positionCardX = x;
		var positionCardY = y;
		var widthStep = width / count;
		for (var i = 0; i < count; ++i) {
			this.fighterCardAreas[i] = new AreaImage(new Area(new Point(positionCardX, positionCardY), widthStep, height));
			positionCardX += widthStep;
		}
	}

	this.InitAreas = function (x, y, width, height) {
		this.fullArea = new Area(new Point(x, y), width, height);
	
		var tempHeight = height * sizeOfHeightStep;
		this.FighterAreasInit(0 + width * sizeOfOneCardEllement, 0, width * 2 * sizeOfOneCardEllement, tempHeight, fighterCount);

		this.areas["deck"] = new AreaImage(new Area(new Point(0, 0 + tempHeight), width * sizeOfOneCardEllement, tempHeight));

		this.areas["hp"] = new AreaText(new Area(this.areas["deck"].area.GetHorisontalPositionAfterThis(), width * sizeOfHpAreaWidth, this.areas["deck"].area.height * sizeOfHpAreaHeight));
		this.areas["ea"] = new AreaText(new Area(this.areas["hp"].area.GetVerticalPositionAfterThis(), width * sizeOfHpAreaWidth, this.areas["deck"].area.height * sizeOfHpAreaHeight));

		this.areas["supportCard"] = new AreaImage(new Area(this.areas["hp"].area.GetHorisontalPositionAfterThis(), width * sizeOfOneCardEllement, this.areas["deck"].area.height));


		this.areas["ts"] = new AreaText(new Area(this.areas["supportCard"].area.GetHorisontalPositionAfterThis(), width * sizeOfHpAreaWidth, this.areas["deck"].area.height * sizeOfTsAreaHeight));
		this.areas["ob"] = new AreaText(new Area(this.areas["ts"].area.GetVerticalPositionAfterThis(), width * sizeOfHpAreaWidth, this.areas["deck"].area.height * sizeOfTsAreaHeight));
		this.areas["nv"] = new AreaText(new Area(this.areas["ob"].area.GetVerticalPositionAfterThis(), width * sizeOfHpAreaWidth, this.areas["deck"].area.height * sizeOfTsAreaHeight));

		this.areas["talon"] = new AreaImage(new Area(this.areas["ts"].area.GetHorisontalPositionAfterThis(), width * sizeOfOneCardEllement, this.areas["deck"].area.height));

		this.areas["talon"]._OnClick = this.OnTalonClick;
	}

	this.InitClick = function () {
		/*for (var i = 0; i < this.fighterCardAreas.length; ++i) {
			this.fighterCardAreas[i].addListener(this.fighterCardAreas[i], "onClick", "DrawBigImage");
		}

		this.deckArea.addListener(this.deckArea, "onClick", "DrawBigImage");
		this.talonArea.addListener(this.talonArea, "onClick", "DrawBigImage");
		this.supportCardArea.addListener(this.supportCardArea, "onClick", "DrawBigImage");*/

		this.areas["talon"]._OnClick = this.OnTalonClick();

	}

	this.SetContext = function (context) {
		this.context = context;

		for (var i = 0; i < this.fighterCardAreas.length; ++i) {
			this.fighterCardAreas[i].context = context;
		}

		for (var areaName in this.areas) {
			this.areas[areaName].context = context;
		}
	}

	this.InitTexts = function () {
		this.areas["hp"].text = "HP";
		this.areas["ea"].text = "E";
		this.areas["ts"].text = "TS";
		this.areas["ob"].text = "OB";
		this.areas["nv"].text = "NV";

	}

	this.SetSourceFighterCardAreas = function () {
		for (var i = 0; i < this.fighterCardAreas.length; ++i) {
			this.fighterCardAreas[i].SetSource("Picture/Symbols/fighterCard.png");
		}
	}

	this.SetSourceHightElf = function () {
		this.SetSourceFighterCardAreas();
		this.areas["deck"].SetSource("Picture/Symbols/HightElf/deck.png");
		this.areas["talon"].SetSource("Picture/Symbols/HightElf/talon.png");
		this.areas["supportCard"].SetSource("Picture/Symbols/HightElf/supportCard.png");
	}

	this.SetSourceDarkElf = function () {
		this.SetSourceFighterCardAreas();
		this.areas["deck"].SetSource("Picture/Symbols/DarkElf/deck.png");
		this.areas["talon"].SetSource("Picture/Symbols/DarkElf/talon.png");
		this.areas["supportCard"].SetSource("Picture/Symbols/DarkElf/supportCard.png");
		
	}

	this.SetSourceBalikuru = function () {
		this.SetSourceFighterCardAreas();
		this.areas["deck"].SetSource("Picture/Symbols/Balikuru/deck.png");
		this.areas["talon"].SetSource("Picture/Symbols/Balikuru/talon.png");
		this.areas["supportCard"].SetSource("Picture/Symbols/Balikuru/supportCard.png");
		
	}

	this.SetSourceVeld = function () {
		this.SetSourceFighterCardAreas();
		this.areas["deck"].SetSource("Picture/Symbols/Veld/deck.png");
		this.areas["talon"].SetSource("Picture/Symbols/Veld/talon.png");
		this.areas["supportCard"].SetSource("Picture/Symbols/Veld/supportCard.png");
		
	}

	this.FighterCardAreasDraw = function() {
		for (var i = 0; i < this.fighterCardAreas.length; ++i) {
			this.fighterCardAreas[i].Draw();
		}
	}

	this.Draw = function () {
		this.fullArea.DrawRotateBegin(this.context, this.rotate);
		
		//this.fullArea.Draw();
		this.FighterCardAreasDraw();
		for (var areaName in this.areas) {
			this.areas[areaName].Draw();
		}

		this.fullArea.DrawRotateEnd(this.context);
	}
	
	this.OnClick = function (eventPoint) {
		var eventPointTemp = new Point(eventPoint.x, eventPoint.y);
		eventPointTemp.RotationAcrosPoint(this.fullArea.beginPoint, -this.rotate);//для востановления нужно передавать обратный угол.
		if (this.fullArea.IsPointInArea(eventPointTemp)) {
			eventPointTemp.MinusFromThis(this.fullArea.beginPoint);

			for (var i = 0; i < this.fighterCardAreas.length; ++i) {
				this.fighterCardAreas[i].onClick(eventPointTemp);
			}

			for (var areaName in this.areas) {
				if (this.areas[areaName].onClick) {
					this.areas[areaName].onClick(eventPointTemp);
				}
			}

		}
	}

	this.Clear = function () {
		this.fullArea.DrawRotateBegin(this.context, this.rotate);

		for (var i = 0; i < this.fighterCardAreas.length; ++i) {
			this.fighterCardAreas[i].Clear();
		}

		for (var areaName in this.areas) {
			this.areas[areaName].Clear();
		}

		this.fullArea.DrawRotateEnd(this.context);

	}

	/*this.CheckPoint = function (eventPoint) {
		var eventPointTemp = new Point(eventPoint.x, eventPoint.y);
		eventPointTemp.RotationAcrosPoint(this.fullArea.beginPoint, -this.rotate);//для востановления нужно передавать обратный угол.
		return this.fullArea.IsPointInArea(eventPointTemp);
	}*/


	this.OnMouseLeave = function (eventPoint) {
		/*var eventPointTemp = new Point(eventPoint.x, eventPoint.y);
		eventPointTemp.RotationAcrosPoint(this.fullArea.beginPoint, -this.rotate);//для востановления нужно передавать обратный угол.
		if (!this.fullArea.IsPointInArea(eventPointTemp)) {
			eventPointTemp.MinusFromThis(this.fullArea.beginPoint)
			for (var i = 0; i < this.fighterCardAreas.length; ++i) {
				this.fighterCardAreas[i].OnMouseLeave(eventPointTemp);
			}

			this.deckArea.OnMouseLeave(eventPointTemp);
			this.talonArea.OnMouseLeave(eventPointTemp);
			this.supportCardArea.OnMouseLeave(eventPointTemp);
		}*/
	}

	this.OnDeckClick = function () {
		alert("deck");
	}

	this.OnTalonClick = function () {
		alert("talon");
		alert(this);
	}

	this.OnSuppClick = function () {
		alert("supp");
	}
}
