function PlayerField() {
	var sizeOfHeightStep = 1/2;
	var sizeOfHpAreaWidth = 1 / 8;
	var sizeOfOneCardEllement = 2 * sizeOfHpAreaWidth;
	var sizeOfHpAreaHeight = 1 / 2;
	var sizeOfTsAreaHeight = 1 / 3;

	var fighterCount = 3;

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

	this.fighterCardAreas = new Array();

	this.rotate = 0;
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
		/*this.mainArea = new Area();
		this.mainArea.Init(x, y, width, height);
		this.mainArea.Init(x, y, width, height);*/
		this.fullArea = new Area(new Point(x, y), width, height);
		/*var tempHeight = height * sizeOfHeightStep;
		this.FighterAreasInit(x + width * sizeOfOneCardEllement, y, width * 2 *  sizeOfOneCardEllement, tempHeight, fighterCount);

		this.deckArea = new AreaImage(new Area(new Point(x, y + tempHeight), width * sizeOfOneCardEllement, tempHeight));

		this.HPArea = new AreaText(new Area(this.deckArea.area.GetVerticalPositionAfterThis(), width * sizeOfHpAreaWidth, this.deckArea.area.height * sizeOfHpAreaHeight));
		this.EArea = new AreaText(new Area(this.HPArea.area.GetHorisontalPositionAfterThis(), width * sizeOfHpAreaWidth, this.deckArea.area.height * sizeOfHpAreaHeight));

		this.supportCardArea = new AreaImage(new Area(this.HPArea.area.GetVerticalPositionAfterThis(), width * sizeOfOneCardEllement, this.deckArea.area.height));


		this.TSArea = new AreaText(new Area(this.supportCardArea.area.GetVerticalPositionAfterThis(), width * sizeOfHpAreaWidth, this.deckArea.area.height * sizeOfTsAreaHeight));
		this.OBArea = new AreaText(new Area(this.TSArea.area.GetHorisontalPositionAfterThis(), width * sizeOfHpAreaWidth, this.deckArea.area.height * sizeOfTsAreaHeight));
		this.NVArea = new AreaText(new Area(this.OBArea.area.GetHorisontalPositionAfterThis(), width * sizeOfHpAreaWidth, this.deckArea.area.height * sizeOfTsAreaHeight));

		this.talonArea = new AreaImage(new Area(this.TSArea.area.GetVerticalPositionAfterThis(), width * sizeOfOneCardEllement, this.deckArea.area.height));
		*/
		var tempHeight = height * sizeOfHeightStep;
		this.FighterAreasInit(0 + width * sizeOfOneCardEllement, 0, width * 2 * sizeOfOneCardEllement, tempHeight, fighterCount);

		this.deckArea = new AreaImage(new Area(new Point(0, 0 + tempHeight), width * sizeOfOneCardEllement, tempHeight));

		this.HPArea = new AreaText(new Area(this.deckArea.area.GetVerticalPositionAfterThis(), width * sizeOfHpAreaWidth, this.deckArea.area.height * sizeOfHpAreaHeight));
		this.EArea = new AreaText(new Area(this.HPArea.area.GetHorisontalPositionAfterThis(), width * sizeOfHpAreaWidth, this.deckArea.area.height * sizeOfHpAreaHeight));

		this.supportCardArea = new AreaImage(new Area(this.HPArea.area.GetVerticalPositionAfterThis(), width * sizeOfOneCardEllement, this.deckArea.area.height));


		this.TSArea = new AreaText(new Area(this.supportCardArea.area.GetVerticalPositionAfterThis(), width * sizeOfHpAreaWidth, this.deckArea.area.height * sizeOfTsAreaHeight));
		this.OBArea = new AreaText(new Area(this.TSArea.area.GetHorisontalPositionAfterThis(), width * sizeOfHpAreaWidth, this.deckArea.area.height * sizeOfTsAreaHeight));
		this.NVArea = new AreaText(new Area(this.OBArea.area.GetHorisontalPositionAfterThis(), width * sizeOfHpAreaWidth, this.deckArea.area.height * sizeOfTsAreaHeight));

		this.talonArea = new AreaImage(new Area(this.TSArea.area.GetVerticalPositionAfterThis(), width * sizeOfOneCardEllement, this.deckArea.area.height));

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
	}

	this.InitTexts = function () {
		this.HPArea.text = "HP";
		this.EArea.text = "E";
		this.TSArea.text = "TS";
		this.OBArea.text = "OB";
		this.NVArea.text = "NV";

	}

		this.SetSourceFighterCardAreas = function() {
		for (var i = 0; i < this.fighterCardAreas.length; ++i) {
			this.fighterCardAreas[i].SetSource("Picture/Symbols/HightElf/deck.jpg");
		}
	}

	this.SetSourceHightElf = function () {
		this.SetSourceFighterCardAreas();
		this.deckArea.SetSource("Picture/Symbols/HightElf/deck.jpg");
		this.talonArea.SetSource("Picture/Symbols/HightElf/talon.jpg");
		this.supportCardArea.SetSource("Picture/Symbols/HightElf/supportCard.jpg");
	}

	this.SetSourceDarklElf = function () {
		SetSourceFighterCardAreas();
		
	}

	this.SetSourceBalikuru = function () {
		SetSourceFighterCardAreas();
		
	}

	this.SetSourceVeld = function () {
		SetSourceFighterCardAreas();
		
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
				
		this.fullArea.DrawRotateEnd(this.context);
	}
}