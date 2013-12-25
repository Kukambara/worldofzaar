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
	
	this.rotate = 0;	//�������
	this.context;
    this.fieldId = 0;
    this.scale = 0;

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

	this.InitAreas = function (/*Area*/ area) {
		this.fullArea = area.GetClone();
	
		var tempHeight = this.fullArea.height * sizeOfHeightStep;
		this.FighterAreasInit(0 + this.fullArea.width * sizeOfOneCardEllement
            , 0
            , this.fullArea.width * 2 * sizeOfOneCardEllement
            , tempHeight
            , fighterCount
        );

		this.areas["deck"] = new AreaImage(new Area(new Point(0, 0 + tempHeight)
            , this.fullArea.width * sizeOfOneCardEllement
            , tempHeight)
        );

		this.areas["hp"] = new AreaText(new Area(this.areas["deck"].area.GetHorisontalPositionAfterThis()
            , this.fullArea.width * sizeOfHpAreaWidth
            , this.areas["deck"].area.height * sizeOfHpAreaHeight)
        );

		this.areas["ea"] = new AreaText(new Area(this.areas["hp"].area.GetVerticalPositionAfterThis()
            , this.fullArea.width * sizeOfHpAreaWidth
            , this.areas["deck"].area.height * sizeOfHpAreaHeight)
        );

		this.areas["supportCard"] = new AreaImage(new Area(this.areas["hp"].area.GetHorisontalPositionAfterThis()
            , this.fullArea.width * sizeOfOneCardEllement
            , this.areas["deck"].area.height)
        );


		this.areas["mp"] = new AreaText(new Area(this.areas["supportCard"].area.GetHorisontalPositionAfterThis()
            , this.fullArea.width * sizeOfHpAreaWidth
            , this.areas["deck"].area.height * sizeOfTsAreaHeight)
        );
		this.areas["ap"] = new AreaText(new Area(this.areas["mp"].area.GetVerticalPositionAfterThis()
            , this.fullArea.width * sizeOfHpAreaWidth
            , this.areas["deck"].area.height * sizeOfTsAreaHeight)
        );
		this.areas["nv"] = new AreaText(new Area(this.areas["ap"].area.GetVerticalPositionAfterThis()
            , this.fullArea.width * sizeOfHpAreaWidth
            , this.areas["deck"].area.height * sizeOfTsAreaHeight)
        );

		this.areas["talon"] = new AreaImage(new Area(this.areas["mp"].area.GetHorisontalPositionAfterThis()
            , this.fullArea.width * sizeOfOneCardEllement, this.areas["deck"].area.height)
        );

		this.areas["talon"]._OnClick = this.OnTalonClick;
	}

    this.InitAreasForCards = function(/*Area*/ areaReal, context){
        this.context = context;
        this.scale =  Card().CalculateScale(areaReal);
        var scaledArea = areaReal.GetClone();
        scaledArea.Scale(this.scale);
        this.InitAreas(scaledArea);
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
		this.areas["mp"].text = "MP";
		this.areas["ap"].text = "AP";
		this.areas["nv"].text = "NV";

	}

    this.SetStats = function(hp, ea, mp, ap, nv){
        SetStatByName("hp", hp);
        SetStatByName("ea", ea);
        SetStatByName("mp", mp);
        SetStatByName("ap", ap);
        SetStatByName("nv", nv);
    }

    this.SetStatByName = function(/*string*/ name, value){
        this.areas[name].ResetText(value);
    }


	this.SetSourceFighterCardAreas = function () {
		for (var i = 0; i < this.fighterCardAreas.length; ++i) {
			this.fighterCardAreas[i].SetSource("/resources/Images/GameState/Symbols/fighterCard.png");
		}
	}

	this.SetSourceHightElf = function () {
		this.SetSourceFighterCardAreas();
		this.areas["deck"].SetSource("/resources/Images/GameState/Symbols/HightElf/deck.png");
		this.areas["talon"].SetSource("/resources/Images/GameState/Symbols/HightElf/talon.png");
		this.areas["supportCard"].SetSource("/resources/Images/GameState/Symbols/HightElf/supportCard.png");
	}

	this.SetSourceDarkElf = function () {
		this.SetSourceFighterCardAreas();
		this.areas["deck"].SetSource("/resources/Images/GameState/Symbols/DarkElf/deck.png");
		this.areas["talon"].SetSource("/resources/Images/GameState/Symbols/DarkElf/talon.png");
		this.areas["supportCard"].SetSource("/resources/Images/GameState/Symbols/DarkElf/supportCard.png");
		
	}

	this.SetSourceBalikuru = function () {
		this.SetSourceFighterCardAreas();
		this.areas["deck"].SetSource("/resources/Images/GameState/Symbols/Balikuru/deck.png");
		this.areas["talon"].SetSource("/resources/Images/GameState/Symbols/Balikuru/talon.png");
		this.areas["supportCard"].SetSource("/resources/Images/GameState/Symbols/Balikuru/supportCard.png");
		
	}

	this.SetSourceVeld = function () {
		this.SetSourceFighterCardAreas();
		this.areas["deck"].SetSource("/resources/Images/GameState/Symbols/Veld/deck.png");
		this.areas["talon"].SetSource("/resources/Images/GameState/Symbols/Veld/talon.png");
		this.areas["supportCard"].SetSource("/resources/Images/GameState/Symbols/Veld/supportCard.png");
		
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
		eventPointTemp.RotationAcrosPoint(this.fullArea.beginPoint, -this.rotate);//��� ������������� ����� ���������� �������� ����.
		if (this.fullArea.IsPointInArea(eventPointTemp)) {
			eventPointTemp.MinusFromThis(this.fullArea.beginPoint);

			for (var i in this.fighterCardAreas) {
				this.fighterCardAreas[i].onClick(eventPointTemp);
                if(this.fighterCardAreas[i].CheckPoint(eventPointTemp))   {
                    eventManager.triggerEvent("onFieldClick", [this.fieldId, i]);
                }
			}

			for (var areaName in this.areas) {
				if (this.areas[areaName].onClick) {
					this.areas[areaName].onClick(eventPointTemp);
				}
                if(this.areas[areaName].area.IsPointInArea(eventPointTemp))   {
                    eventManager.triggerEvent("onFieldClick", [this.fieldId, areaName]);
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
		eventPointTemp.RotationAcrosPoint(this.fullArea.beginPoint, -this.rotate);//��� ������������� ����� ���������� �������� ����.
		return this.fullArea.IsPointInArea(eventPointTemp);
	}*/


	this.OnMouseLeave = function (eventPoint) {
		/*var eventPointTemp = new Point(eventPoint.x, eventPoint.y);
		eventPointTemp.RotationAcrosPoint(this.fullArea.beginPoint, -this.rotate);//��� ������������� ����� ���������� �������� ����.
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
	//	alert("talon");
	}

    this.OnPlayerField = function(){
        eventManager.triggerEvent("onFieldClick", this.fieldId, "talon");
    }

	this.OnSuppClick = function () {
		alert("supp");
	}
}
