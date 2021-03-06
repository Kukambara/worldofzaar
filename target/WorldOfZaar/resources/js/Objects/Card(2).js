function Card() {
	var baseWidth = 400;
	var baseHeight = 640;
    this.area;

	this.borderSize = 0.1;
	this.background;
	this.image;
	this.info;

	this.name;
	this.energy;

	this.hp;
	this.atack;
	this.defence;

	this.imageSmall;

	this.nameSmall;
	this.energySmall;

	this.hpSmall;
	this.atackSmall;
	this.defenceSmall;

	this.scale = 1;

    this.isWarrior ;

    this.InitCard = function(area,isWarrior,cardObject,cardName,cardSlogan,cardProperty){

        this.area = new Area(area.beginPoint, baseWidth, baseHeight);

        this.isWarrior = isWarrior;

        this.background = new AreaImage(new Area(new Point(0, 0), this.area.width, this.area.height));
        this.background.SetSource(cardObject.subset.frontPath);



        this.name = new AreaText(new Area(new Point(this.background.area.beginPoint.x + this.borderSize, this.background.area.beginPoint.y + this.borderSize),
            this.background.area.width - this.borderSize * 3, this.borderSize), cardName);

        var widthWithOutBorder = this.background.area.width - this.borderSize * 2;

        this.energy = new AreaText(new Area(this.name.area.GetHorisontalPositionAfterThis(),
            this.borderSize, this.borderSize), cardObject.cardEnergy);

        if(isWarrior){
            this.hp = new AreaText(new Area(this.name.area.GetVerticalPositionAfterThis(),
                widthWithOutBorder / 3, this.borderSize),cardObject.cardHealth);
            this.atack = new AreaText(new Area(this.hp.area.GetHorisontalPositionAfterThis(),
                widthWithOutBorder / 3, this.borderSize), cardObject.cardDamage);
            this.defence = new AreaText(new Area(this.atack.area.GetHorisontalPositionAfterThis(),
                widthWithOutBorder / 3, this.borderSize), cardObject.cardArmor);
        }
        else   {
            this.hp = new AreaText(new Area(this.name.area.GetVerticalPositionAfterThis(),
                widthWithOutBorder / 3, this.borderSize),0);
            this.atack = new AreaText(new Area(this.hp.area.GetHorisontalPositionAfterThis(),
                widthWithOutBorder / 3, this.borderSize), 0);
            this.defence = new AreaText(new Area(this.atack.area.GetHorisontalPositionAfterThis(),
                widthWithOutBorder / 3, this.borderSize), 0);
        }

        var freeHeight = this.area.height - this.borderSize*4;

        this.image = new AreaImage(new Area(this.hp.area.GetVerticalPositionAfterThis(), widthWithOutBorder, freeHeight * 2 / 3));
        this.image.SetSource(cardObject.cardPicture);


        this.info = new AreaText(new Area(this.image.area.GetVerticalPositionAfterThis(), widthWithOutBorder, freeHeight * 1 / 3), cardProperty);

        this.SetDrawableArea(area);
    }

	this.InitSmall = function () {
        var height = (this.area.height - this.borderSize * 2) / 6;
        var width = (this.area.width - this.borderSize * 2);

		this.nameSmall = new AreaText(new Area(new Point(this.background.area.beginPoint.x + this.borderSize, this.background.area.beginPoint.y + this.borderSize),
		width, height), this.name.text);//new Area(new Point(0, 0), this.area.width, height ), "testName");
		width /= 2;
		this.energySmall = new AreaText(new Area(this.nameSmall.area.GetVerticalPositionAfterThis(),
		width, height), this.energy.text);
		this.hpSmall = new AreaText(new Area(this.energySmall.area.GetHorisontalPositionAfterThis(),
		width, height), this.hp.text);
		this.atackSmall = new AreaText(new Area(this.energySmall.area.GetVerticalPositionAfterThis(),
		width, height),this.atack.text );
		this.defenceSmall = new AreaText(new Area(this.atackSmall.area.GetHorisontalPositionAfterThis(),
		width, height), this.defence.text);
		this.imageSmall = new AreaImage(new Area(this.atackSmall.area.GetVerticalPositionAfterThis(), width * 2, height * 3));
		this.imageSmall.SetSource(this.image.image.src);
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
		this.background.context = context;
		this.image.context = context;
		this.info.context = context;

		this.name.context = context;
		this.energy.context = context;

		this.hp.context = context;
		this.atack.context = context;
		this.defence.context = context;
		//this.context = context;

		this.info.area.isBorder = true;

		this.name.area.isBorder = true;
		this.energy.area.isBorder = true;

		this.hp.area.isBorder = true;
		this.atack.area.isBorder = true;
		this.defence.area.isBorder = true;

		this.info.background = "rgb(254, 254, 254)";

		this.name.background = "rgb(254, 254, 254)";
		this.energy.background = "rgb(254, 254, 254)";

		this.hp.background = "rgb(254, 254, 254)";
		this.atack.background = "rgb(254, 254, 254)";
		this.defence.background = "rgb(254, 254, 254)";

		this.info.textColor = "rgb(0, 0, 0)";

		this.name.textColor = "rgb(0, 0, 0)";
		this.energy.textColor = "rgb(0, 0, 0)";

		this.hp.textColor = "rgb(0, 0, 0)";
		this.atack.textColor = "rgb(0, 0, 0)";
		this.defence.textColor = "rgb(0, 0, 0)";

		this.SetContextSmall(context);
	}

	this.SetDrawableArea = function (/*Area*/ drawableArea){//(/*Point*/ beginPoint, /*int*/ width, /*int*/ height) {
		var widthScale = drawableArea.width / this.area.width;
		var heightScale = drawableArea.height / this.area.height;
		this.scale = (widthScale < heightScale ? widthScale : heightScale)/this.scale;
		this.area.beginPoint = drawableArea.beginPoint;
	}

	this.Draw = function () {
		this.background.context.save();

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

		this.background.context.restore();

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

//Card.prototype = new Clickable();