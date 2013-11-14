function PlayerField() {
	this.listener;
	var sizeOfHeightStep = 1/2;
	var sizeOfHpAreaWidth = 1 / 8;
	var sizeOfOneCardEllement = 2 * sizeOfHpAreaWidth;
	var sizeOfHpAreaHeight = 1 / 2;
	var sizeOfTsAreaHeight = 1 / 3;

	var fighterCount = 3;
	var card = new Card();
	var cardSmall = new Card();
	var cardSmall2 = new Card();
	/*this.mainArea;
	this.fighterArea;*/

	this.fullArea;
	this.deckArea = new AreaImage();
	this.talonArea;
	this.supportCardArea;

	this.HPArea;
	this.TSArea;
	this.OBArea;
	this.NVArea;
	this.EArea;

	this.fighterCardAreas = new Array();

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

		this.deckArea.Init(new Area(new Point(0, 0 + tempHeight), width * sizeOfOneCardEllement, tempHeight));

		this.HPArea = new AreaText(new Area(this.deckArea.area.GetHorisontalPositionAfterThis(), width * sizeOfHpAreaWidth, this.deckArea.area.height * sizeOfHpAreaHeight));
		this.EArea = new AreaText(new Area(this.HPArea.area.GetVerticalPositionAfterThis(), width * sizeOfHpAreaWidth, this.deckArea.area.height * sizeOfHpAreaHeight));

		this.supportCardArea = new AreaImage(new Area(this.HPArea.area.GetHorisontalPositionAfterThis(), width * sizeOfOneCardEllement, this.deckArea.area.height));


		this.TSArea = new AreaText(new Area(this.supportCardArea.area.GetHorisontalPositionAfterThis(), width * sizeOfHpAreaWidth, this.deckArea.area.height * sizeOfTsAreaHeight));
		this.OBArea = new AreaText(new Area(this.TSArea.area.GetVerticalPositionAfterThis(), width * sizeOfHpAreaWidth, this.deckArea.area.height * sizeOfTsAreaHeight));
		this.NVArea = new AreaText(new Area(this.OBArea.area.GetVerticalPositionAfterThis(), width * sizeOfHpAreaWidth, this.deckArea.area.height * sizeOfTsAreaHeight));

		this.talonArea = new AreaImage(new Area(this.TSArea.area.GetHorisontalPositionAfterThis(), width * sizeOfOneCardEllement, this.deckArea.area.height));

		card.Init(this.fighterCardAreas[0].area);
		card.InitColorsForText();
		cardSmall.Init(this.fighterCardAreas[1].area);
		cardSmall.InitColorsForText();
		cardSmall2.Init(this.fighterCardAreas[2].area);
		cardSmall2.InitSmall2();
		//this.InitClick();
	}

	this.InitClick = function () {
		for (var i = 0; i < this.fighterCardAreas.length; ++i) {
			this.fighterCardAreas[i].addListener(this.fighterCardAreas[i], "onClick", "DrawBigImage");
		}

		this.deckArea.addListener(this.deckArea, "onClick", "DrawBigImage");
		this.talonArea.addListener(this.talonArea, "onClick", "DrawBigImage");
		this.supportCardArea.addListener(this.supportCardArea, "onClick", "DrawBigImage");
	}

	this.SetContext = function (context) {
		this.context = context;
		this.deckArea.context = context;

		this.HPArea.context = context;
		this.EArea.context = context;

		this.supportCardArea.context = context;

		this.TSArea.context = context;
		this.OBArea.context = context;
		this.NVArea.context = context;

		this.talonArea.context = context;

		for (var i = 0; i < this.fighterCardAreas.length; ++i) {			
			this.fighterCardAreas[i].context = context;
		}

		card.SetContext(context);
		cardSmall.SetContext(context);
		cardSmall2.SetContext(context);
	}

	this.InitTexts = function () {
		this.HPArea.text = "HP";
		this.EArea.text = "E";
		this.TSArea.text = "TS";
		this.OBArea.text = "OB";
		this.NVArea.text = "NV";

	}

	this.SetSourceFighterCardAreas = function () {
		//resources.loadByUrl("Picture/Symbols/fighterCard.png");
		for (var i = 0; i < this.fighterCardAreas.length; ++i) {
			//this.fighterCardAreas[i].SetImage(resources.get("Picture/Symbols/fighterCard.png"));
			this.fighterCardAreas[i].SetSource("Picture/Symbols/fighterCard.png");
		}
	}

	this.SetSourceHightElf = function () {
		this.SetSourceFighterCardAreas();
		this.deckArea.SetSource("Picture/Symbols/HightElf/deck.png");
		this.talonArea.SetSource("Picture/Symbols/HightElf/talon.png");
		this.supportCardArea.SetSource("Picture/Symbols/HightElf/supportCard.png");
	}

	this.SetSourceDarkElf = function () {
		this.SetSourceFighterCardAreas();
		this.deckArea.SetSource("Picture/Symbols/DarkElf/deck.png");
		this.talonArea.SetSource("Picture/Symbols/DarkElf/talon.png");
		this.supportCardArea.SetSource("Picture/Symbols/DarkElf/supportCard.png");
		
	}

	this.SetSourceBalikuru = function () {
		this.SetSourceFighterCardAreas();
		this.deckArea.SetSource("Picture/Symbols/Balikuru/deck.png");
		this.talonArea.SetSource("Picture/Symbols/Balikuru/talon.png");
		this.supportCardArea.SetSource("Picture/Symbols/Balikuru/supportCard.png");
		
	}

	this.SetSourceVeld = function () {
		this.SetSourceFighterCardAreas();
		this.deckArea.SetSource("Picture/Symbols/Veld/deck.png");
		this.talonArea.SetSource("Picture/Symbols/Veld/talon.png");
		this.supportCardArea.SetSource("Picture/Symbols/Veld/supportCard.png");
		
	}

	this.FighterCardAreasDraw = function() {
		for (var i = 0; i < this.fighterCardAreas.length; ++i) {
			this.fighterCardAreas[i].Draw();
		}
	}

	this.Draw = function () {
		this.fullArea.DrawRotateBegin(this.context, this.rotate);

	//	this.deckArea.area.DrawImage(this.deckArea.context, this.deckArea.image);

		//this.fullArea.Draw();

		this.FighterCardAreasDraw();
		this.deckArea.Draw();
		this.talonArea.Draw();
		this.supportCardArea.Draw();

		this.HPArea.Draw();
		this.TSArea.Draw();
		this.OBArea.Draw();
		this.NVArea.Draw();
		this.EArea.Draw();
				
		card.Draw();
		cardSmall.DrawSmall();
		cardSmall2.DrawSmall();

		this.fullArea.DrawRotateEnd(this.context);
	}
	
	this.OnClick = function (eventPoint) {
		/*var eventPointTemp = new Point(eventPoint.x, eventPoint.y);
		eventPointTemp.RotationAcrosPoint(this.fullArea.beginPoint, -this.rotate);//для востановления нужно передавать обратный угол.
		if (this.fullArea.IsPointInArea(eventPointTemp)) {*/

		/*	eventPointTemp.MinusFromThis(this.fullArea.beginPoint)
			for (var i = 0; i < this.fighterCardAreas.length; ++i) {
				this.fighterCardAreas[i].OnClick(eventPointTemp);
			}

			this.deckArea.OnClick(eventPointTemp);
			this.talonArea.OnClick(eventPointTemp);
			this.supportCardArea.OnClick(eventPointTemp);*/

			/*eventPointTemp.MinusFromThis(this.fullArea.beginPoint)
			for (var i = 0; i < this.fighterCardAreas.length; ++i) {
				this.fighterCardAreas[i].onClick(eventPointTemp);
			}

			this.deckArea.onClick(eventPointTemp);
			this.talonArea.onClick(eventPointTemp);
			this.supportCardArea.onClick(eventPointTemp);

		}*/
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
}
