function CardFighter() {
	this.__proto__ = new Card();
	
	this.hp;
	this.atack;
	this.defence;

	this.Init = function ( /*Area*/ area) {
		//var border = this.borderSize * area.width;
		
		//this.area = area;
		this.__proto__.area = new Area(area.beginPoint, this.baseWidth, this.baseHeight)
		
		this.__proto__.borderSize = this.borderSize * this.__proto__.area.width;
		//	this.background = new AreaImage(this.area);
		this.__proto__.background = new AreaImage(new Area(new Point(0, 0), this.__proto__.area.width, this.__proto__.area.height));
		this.__proto__.background.SetSource("Picture/Symbols/Card/VE.png");
		this.__proto__.name = new AreaText(new Area(new Point(this.__proto__.background.area.beginPoint.x + this.borderSize, this.__proto__.background.area.beginPoint.y + this.borderSize),
		this.__proto__.background.area.width - this.borderSize * 3, this.borderSize), "testName");
		var widthWithOutBorder = this.__proto__.background.area.width - this.borderSize * 2;
		this.__proto__.energy = new AreaText(new Area(this.__proto__.name.area.GetHorisontalPositionAfterThis(),
		this.borderSize, this.borderSize), "0");
		this.hp = new AreaText(new Area(this.__proto__.name.area.GetVerticalPositionAfterThis(),
		widthWithOutBorder / 3, this.borderSize), "1");
		this.atack = new AreaText(new Area(this.hp.area.GetHorisontalPositionAfterThis(),
		widthWithOutBorder / 3, this.borderSize), "2");
		this.defence = new AreaText(new Area(this.atack.area.GetHorisontalPositionAfterThis(),
		widthWithOutBorder / 3, this.borderSize), "3");
		var freeHeight = this.area.height - this.borderSize*4;
		this.__proto__.image = new AreaImage(new Area(this.hp.area.GetVerticalPositionAfterThis(), widthWithOutBorder, freeHeight * 2 / 3));
		this.__proto__.image.SetSource("Picture/Symbols/Card/Rokirovaka_support_VE.jpg");
		this.__proto__.info = new AreaText(new Area(this.__proto__.image.area.GetVerticalPositionAfterThis(), widthWithOutBorder, freeHeight * 1 / 3), "testInfo");

		this.__proto__.SetDrawableArea(area);
	}

	this.InitSmall2 = function () {

	//	this.borderSize = this.borderSize * this.area.width;
		//	this.background = new AreaImage(this.area);
	/*	this.background = new AreaImage(new Area(new Point(0, 0), this.area.width, this.area.height));
		this.background.SetSource("Picture/Symbols/Card/VE.png");*/
		//	this.background = new AreaImage(this.area);
		this.background = new AreaImage(new Area(new Point(0, 0), this.area.width, this.area.height));
		this.background.SetSource("Picture/Symbols/Card/VE.png");

		var height = (this.area.height - this.borderSize*2) / 6;
		var width = (this.area.width - this.borderSize*2);
		this.nameSmall = new AreaText(new Area(new Point(this.background.area.beginPoint.x + this.borderSize, this.background.area.beginPoint.y + this.borderSize),
		width, height), "testName");//new Area(new Point(0, 0), this.area.width, height ), "testName");
		width /= 2;
		this.energySmall = new AreaText(new Area(this.nameSmall.area.GetVerticalPositionAfterThis(),
		width, height), "0");
		this.hpSmall = new AreaText(new Area(this.energySmall.area.GetHorisontalPositionAfterThis(),
		width, height), "1");
		var point = this.energySmall.area.GetVerticalPositionAfterThis();
		point.AddToThis(new Point(0, height * 3));
		this.atackSmall = new AreaText(new Area(point,
		width, height), "2");
		this.defenceSmall = new AreaText(new Area(this.atackSmall.area.GetHorisontalPositionAfterThis(),
		width, height), "3");
		this.imageSmall = new AreaImage(new Area(this.nameSmall.area.GetVerticalPositionAfterThis(), width * 2, height * 5));
		this.imageSmall.SetSource("Picture/Symbols/Card/Rokirovaka_support_VE.jpg");
		this.InitColorsForText2();
	}

	this.InitSmall = function () {

		//	this.borderSize = this.borderSize * this.area.width;
		//	this.background = new AreaImage(this.area);
		/*	this.background = new AreaImage(new Area(new Point(0, 0), this.area.width, this.area.height));
			this.background.SetSource("Picture/Symbols/Card/VE.png");*/
		//	this.background = new AreaImage(this.area);
		this.__proto__.background = new AreaImage(new Area(new Point(0, 0), this.area.width, this.area.height));
		this.__proto__.background.SetSource("Picture/Symbols/Card/VE.png");

		var height = (this.area.height - this.borderSize * 2) / 6;
		var width = (this.area.width - this.borderSize * 2);
		this.__proto__.nameSmall = new AreaText(new Area(new Point(this.__proto__.background.area.beginPoint.x + this.borderSize, this.__proto__.background.area.beginPoint.y + this.borderSize),
		width, height), "testName");//new Area(new Point(0, 0), this.area.width, height ), "testName");
		width /= 2;
		this.__proto__.energySmall = new AreaText(new Area(this.__proto__.nameSmall.area.GetVerticalPositionAfterThis(),
		width, height), "0");
		this.hpSmall = new AreaText(new Area(this.energySmall.area.GetHorisontalPositionAfterThis(),
		width, height), "1");
		this.atackSmall = new AreaText(new Area(this.energySmall.area.GetVerticalPositionAfterThis(),
		width, height), "2");
		this.defenceSmall = new AreaText(new Area(this.atackSmall.area.GetHorisontalPositionAfterThis(),
		width, height), "3");
		this.__proto__.imageSmall = new AreaImage(new Area(this.atackSmall.area.GetVerticalPositionAfterThis(), width * 2, height * 3));
		this.__proto__.imageSmall.SetSource("Picture/Symbols/Card/Rokirovaka_support_VE.jpg");
	}

	this.SetContextSmall = function(context){
		this.imageSmall.context = context;

		this.nameSmall.context = context;
		this.energySmall.context = context;

		this.hpSmall.context = context;
		this.atackSmall.context = context;
		this.defenceSmall.context = context;
		//this.context = context;

		this.nameSmall.area.isBorder = true;
		this.energySmall.area.isBorder = true;

		this.hpSmall.area.isBorder = true;
		this.atackSmall.area.isBorder = true;
		this.defenceSmall.area.isBorder = true;
	}

	this.InitColorsForText = function () {
		this.nameSmall.background = "rgb(254, 254, 254)";
		this.energySmall.background = "rgb(254, 254, 254)";

		this.hpSmall.background = "rgb(254, 254, 254)";
		this.atackSmall.background = "rgb(254, 254, 254)";
		this.defenceSmall.background = "rgb(254, 254, 254)";

		this.nameSmall.textColor = "rgb(0, 0, 0)";
		this.energySmall.textColor = "rgb(0, 0, 0)";

		this.hpSmall.textColor = "rgb(0, 0, 0)";
		this.atackSmall.textColor = "rgb(0, 0, 0)";
		this.defenceSmall.textColor = "rgb(0, 0, 0)";
	}

	this.InitColorsForText2 = function () {
		this.nameSmall.textColor = "rgb(254, 254, 254)";
		this.energySmall.textColor = "rgb(254, 254, 254)";

		this.hpSmall.textColor = "rgb(254, 254, 254)";
		this.atackSmall.textColor = "rgb(254, 254, 254)";
		this.defenceSmall.textColor = "rgb(254, 254, 254)";
	}

	this.SetContext = function (context) {
		this.__proto__.background.context = context;
		this.__proto__.image.context = context;
		this.__proto__.info.context = context;

		this.__proto__.name.context = context;
		this.__proto__.energy.context = context;

		this.hp.context = context;
		this.atack.context = context;
		this.defence.context = context;
		//this.context = context;

		this.__proto__.info.area.isBorder = true;

		this.__proto__.name.area.isBorder = true;
		this.__proto__.energy.area.isBorder = true;

		this.hp.area.isBorder = true;
		this.atack.area.isBorder = true;
		this.defence.area.isBorder = true;

		this.__proto__.info.background = "rgb(254, 254, 254)";

		this.__proto__.name.background = "rgb(254, 254, 254)";
		this.__proto__.energy.background = "rgb(254, 254, 254)";

		this.hp.background = "rgb(254, 254, 254)";
		this.atack.background = "rgb(254, 254, 254)";
		this.defence.background = "rgb(254, 254, 254)";

		this.__proto__.info.textColor = "rgb(0, 0, 0)";

		this.__proto__.name.textColor = "rgb(0, 0, 0)";
		this.__proto__.energy.textColor = "rgb(0, 0, 0)";

		this.hp.textColor = "rgb(0, 0, 0)";
		this.atack.textColor = "rgb(0, 0, 0)";
		this.defence.textColor = "rgb(0, 0, 0)";

		//this.SetContextSmall(context);
	}

	this.Draw = function () {
		/*this.background.context.save();

		this.background.context.translate(this.area.beginPoint.x, this.area.beginPoint.y);
		this.background.context.scale(this.scale, this.scale);
		this.background.context.font = "25pt Arial";

		this.background.Draw();
		this.image.Draw();
		this.info.Draw();

		this.name.Draw();
		this.energy.Draw();

		this.hp.Draw();
		this.atack.Draw();
		this.defence.Draw();

		this.background.context.restore();*/
		this.__proto__.Draw();
		this.hp.Draw();
		this.atack.Draw();
		this.defence.Draw();

	}

	this.DrawSmall = function () {
		this.imageSmall.context.save();

		this.background.context.translate(this.area.beginPoint.x, this.area.beginPoint.y);
		this.background.context.scale(this.scale, this.scale);
		this.background.context.font = "35pt Arial";

		this.background.Draw();
		this.imageSmall.Draw();

		this.nameSmall.Draw();

		this.energySmall.Draw();
		this.hpSmall.Draw();

		this.atackSmall.Draw();
		this.defenceSmall.Draw();

		this.imageSmall.context.restore();
	}

}