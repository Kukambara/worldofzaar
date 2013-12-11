function Card() {
    this.baseWidth = 400;
    this.baseHeight = 600;
    this.area;

	this.borderSize = 0.1;
	this.background;
	this.image;
	this.info;

	this.name;
	this.energy;

	this.imageSmall;

	this.nameSmall;
	this.energySmall;

	this.scale = 1;


	Card.prototype.Init = function (area,cardObject,cardName,cardSlogan,cardProperty) {
		//var border = this.borderSize * area.width;
		
		//this.area = area;
		this.area = new Area(area.beginPoint, this.baseWidth, this.baseHeight)
		
		this.borderSize = this.borderSize * this.area.width;
		//	this.background = new AreaImage(this.area);
		this.background = new AreaImage(new Area(new Point(0, 0), this.area.width, this.area.height));
		this.background.SetSource(cardObject.subset.frontPath);
		this.name = new AreaText(new Area(new Point(this.background.area.beginPoint.x + this.borderSize, this.background.area.beginPoint.y + this.borderSize),
		this.background.area.width - this.borderSize * 3, this.borderSize),cardName);
		var widthWithOutBorder = this.background.area.width - this.borderSize * 2;
		this.energy = new AreaText(new Area(this.name.area.GetHorisontalPositionAfterThis(),
		this.borderSize, this.borderSize), cardObject.cardEnergy);
		var freeHeight = this.area.height - this.borderSize*3;
		this.image = new AreaImage(new Area(this.name.area.GetVerticalPositionAfterThis(), widthWithOutBorder, freeHeight * 2 / 3));
		this.image.SetSource(cardObject.cardPicture);
		this.info = new AreaText(new Area(this.image.area.GetVerticalPositionAfterThis(), widthWithOutBorder, freeHeight * 1 / 3), cardProperty);

		this.SetDrawableArea(area);
		this.InitSmall(cardObject,cardName,cardSlogan,cardProperty);
	}

	Card.InitSmall2 = function () {

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

	Card.prototype.InitSmall = function (cardObject,cardName) {

		//	this.borderSize = this.borderSize * this.area.width;
		//	this.background = new AreaImage(this.area);
		/*	this.background = new AreaImage(new Area(new Point(0, 0), this.area.width, this.area.height));
			this.background.SetSource("Picture/Symbols/Card/VE.png");*/
		//	this.background = new AreaImage(this.area);
		this.background = new AreaImage(new Area(new Point(0, 0), this.area.width, this.area.height));
		this.background.SetSource(cardObject.subset.frontPath);

		var height = (this.area.height - this.borderSize * 2) / 6;
		var width = (this.area.width - this.borderSize * 2);
		this.nameSmall = new AreaText(new Area(new Point(this.background.area.beginPoint.x + this.borderSize, this.background.area.beginPoint.y + this.borderSize),
		width, height), cardName);//new Area(new Point(0, 0), this.area.width, height ),);
		width /= 2;
		this.energySmall = new AreaText(new Area(this.nameSmall.area.GetVerticalPositionAfterThis(),
		width, height), cardObject.cardEnergy);
		this.imageSmall = new AreaImage(new Area(this.nameSmall.area.GetVerticalPositionAfterThis(), width * 2, height * 3));
		this.imageSmall.SetSource(cardObject.cardPicture);
	}

	Card.prototype.SetContextSmall = function (context) {
		this.imageSmall.context = context;

		this.nameSmall.context = context;
		this.energySmall.context = context;


		this.nameSmall.area.isBorder = true;
		this.energySmall.area.isBorder = true;

	}

	Card.prototype.InitColorsForText = function () {
		this.nameSmall.background = "rgb(254, 254, 254)";
		this.energySmall.background = "rgb(254, 254, 254)";



		this.nameSmall.textColor = "rgb(0, 0, 0)";
		this.energySmall.textColor = "rgb(0, 0, 0)";
	}

	Card.prototype.InitColorsForText2 = function () {
		this.nameSmall.textColor = "rgb(254, 254, 254)";
		this.energySmall.textColor = "rgb(254, 254, 254)";

	}

	Card.prototype.SetContext = function (context) {
		this.background.context = context;
		this.image.context = context;
		this.info.context = context;

		this.name.context = context;
		this.energy.context = context;


		this.info.area.isBorder = true;

		this.name.area.isBorder = true;
		this.energy.area.isBorder = true;



		this.info.background = "rgb(254, 254, 254)";

		this.name.background = "rgb(254, 254, 254)";
		this.energy.background = "rgb(254, 254, 254)";


		this.info.textColor = "rgb(0, 0, 0)";

		this.name.textColor = "rgb(0, 0, 0)";
		this.energy.textColor = "rgb(0, 0, 0)";

		this.SetContextSmall(context);
	}

	Card.prototype.SetDrawableArea = function (/*Area*/ drawableArea) {//(/*Point*/ beginPoint, /*int*/ width, /*int*/ height) {
		var widthScale = drawableArea.width / this.area.width;
		var heightScale = drawableArea.height / this.area.height;
		this.scale = (widthScale < heightScale ? widthScale : heightScale)/this.scale;
		this.area.beginPoint = drawableArea.beginPoint;
	}

    this.SetScale = function (input){
        this.scale = input;
    }

	Card.prototype.Draw = function () {
		this.background.context.save();

		this.background.context.translate(this.area.beginPoint.x, this.area.beginPoint.y);
		this.background.context.scale(this.scale, this.scale);
		this.background.context.font = "25pt Arial";

		this.background.Draw();
		this.image.Draw();
		this.info.Draw();

		this.name.Draw();
		this.energy.Draw();
	}

	Card.prototype.DrawEnd = function () {
		this.background.context.restore();
	}

	Card.prototype.DrawSmall = function () {
		this.imageSmall.context.save();

		this.background.context.translate(this.area.beginPoint.x, this.area.beginPoint.y);
		this.background.context.scale(this.scale, this.scale);
		this.background.context.font = "35pt Arial";

		this.background.Draw();
		this.imageSmall.Draw();

		this.nameSmall.Draw();

		this.energySmall.Draw();
		/*this.hpSmall.Draw();

		this.atackSmall.Draw();
		this.defenceSmall.Draw();*/

		this.imageSmall.context.restore();
	}

}

//Card.prototype = new Clickable();