function PlayerField() {

	var sizeOfOneEllement = 1 / 6;
	var sizeOfHpAreaWidth = 1 / 12;
	var sizeOfHpAreaHeight = 1 / 2;
	var sizeOfTsAreaHeight = 1 / 3;

	/*this.mainArea;
	this.fighterArea;*/
	this.fullArea;
	this.deckArea;
	this.talonArea;
	this.supportCardArea;

	this.HPArea;
	this.TSArea;
	this.OBArea;
	this.NVArea;
	this.EArea;

	this.fighterCardArea1;
	this.fighterCardArea2;
	this.fighterCardArea3;

	this.rotate = 0;
	this.context;

	this.InitAreas = function (x, y, width, height, context) {
		/*this.mainArea = new Area();
		this.mainArea.Init(x, y, width, height);
		this.mainArea.Init(x, y, width, height);*/
		this.fullArea = new Area(x, y, width, height);
		var tempX = x;
		var tempY = y;
		this.deckArea = new AreaImage(new Area(tempX, tempY, width * sizeOfOneEllement, height));

		this.HPArea = new AreaText(new Area(tempX += this.deckArea.area.width, tempY, width * sizeOfHpAreaWidth, height * sizeOfHpAreaHeight));
		this.EArea = new AreaText(new Area(tempX, tempY + this.HPArea.area.height, width * sizeOfHpAreaWidth, height * sizeOfHpAreaHeight));

		this.supportCardArea = new AreaImage(new Area(tempX += this.HPArea.area.width, y, width * sizeOfOneEllement, height));


		this.TSArea = new AreaText(new Area(tempX += this.supportCardArea.area.width, tempY, width * sizeOfHpAreaWidth, height * sizeOfTsAreaHeight));
		this.OBArea = new AreaText(new Area(tempX, tempY += this.TSArea.area.height, width * sizeOfHpAreaWidth, height * sizeOfTsAreaHeight));
		this.NVArea = new AreaText(new Area(tempX, tempY + this.OBArea.area.height, width * sizeOfHpAreaWidth, height * sizeOfTsAreaHeight));

		this.talonArea = new AreaImage(new Area(tempX += this.TSArea.area.width, y, width * sizeOfOneEllement, height));

		this.fighterCardArea1 = new AreaImage();
		this.fighterCardArea2 = new AreaImage();
		this.fighterCardArea3 = new AreaImage();
		//	this.fighterArea.Init(x+
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

		this.fighterCardArea1.context = context;
		this.fighterCardArea2.context = context;
		this.fighterCardArea3.context = context;
	}

	this.InitTexts = function () {
		this.HPArea.text = "HP";
		this.EArea.text = "E";
		this.TSArea.text = "TS";
		this.OBArea.text = "OB";
		this.NVArea.text = "NV";

	}

	this.SetSorceFighterCardAreas = function () {
		this.fighterCardArea1.SetSource("Picture/Symbols/HightElf/deck.jpg");
		this.fighterCardArea2.SetSource("Picture/Symbols/HightElf/deck.jpg");
		this.fighterCardArea3.SetSource("Picture/Symbols/HightElf/deck.jpg");
	}

	this.SetSourceHightElf = function () {
		//this.SetSorceFighterCardAreas();
		this.deckArea.SetSource("Picture/Symbols/HightElf/deck.jpg");
		this.talonArea.SetSource("Picture/Symbols/HightElf/talon.jpg");
		this.supportCardArea.SetSource("Picture/Symbols/HightElf/supportCard.jpg");
	}

	this.SetSourceDarklElf = function () {
		this.SetSorceFighterCardAreas();
		SetSorceFighterCardAreas();
		
	}

	this.SetSourceBalikuru = function () {
		this.SetSorceFighterCardAreas();
		SetSorceFighterCardAreas();
		
	}

	this.SetSourceVeld = function () {
		this.SetSorceFighterCardAreas();
		SetSorceFighterCardAreas();
		
	}

	this.Draw = function () {
		this.fullArea.DrawRotateBegin(this.context, this.rotate, this.deckArea.image);

		this.deckArea.Draw();
		this.talonArea.Draw();
		this.supportCardArea.Draw();

		this.HPArea.Draw();
		this.TSArea.Draw();
		this.OBArea.Draw();
		this.NVArea.Draw();
		this.EArea.Draw();
		/*
		this.fighterCardArea1.DrawRotation(rotate);
		this.fighterCardArea2.DrawRotation(rotate);
		this.fighterCardArea3.DrawRotation(rotate);
		*/
		this.fullArea.DrawRotateEnd(this.context);
	}
}