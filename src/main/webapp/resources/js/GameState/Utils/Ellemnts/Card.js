function Card() {
    this.baseWidth = window.consts.cardSize.width;
    this.baseHeight = window.consts.cardSize.height;

    this.borderSize = 0.1;
    this.texts = [];

    this.scale = 1;

    Card.prototype.Init = function ( /*Area*/ area) {
        //var border = this.borderSize * area.width;

        //this.area = area;
        this.area = new Area(area.beginPoint, baseWidth, baseHeight)

        this.borderSize = this.borderSize * this.area.width;
        //	this.background = new AreaImage(this.area);
        this.images["background"] = new AreaImage(new Area(new Point(0, 0), this.area.width, this.area.height));
        this.images["background"].SetSource("Picture/Symbols/Card/VE.png");
        this.texts["name"] = new AreaText(new Area(new Point(this.images["background"].area.beginPoint.x + this.borderSize, this.images["background"].area.beginPoint.y + this.borderSize),
            this.images["background"].area.width - this.borderSize * 3, this.borderSize), "testName");
        var widthWithOutBorder = this.images["background"].area.width - this.borderSize * 2;
        this.texts["nergy"] = new AreaText(new Area(this.texts["name"].area.GetHorisontalPositionAfterThis(),
            this.borderSize, this.borderSize), "0");
        var freeHeight = this.area.height - this.borderSize*3;
        this.images["image"]= new AreaImage(new Area(this.text["hp"].area.GetVerticalPositionAfterThis(), widthWithOutBorder, freeHeight * 2 / 3));
        this.images["image"].SetSource("Picture/Symbols/Card/Rokirovaka_support_VE.jpg");
        this.texts["info"] = new AreaText(new Area(this.images["image"].area.GetVerticalPositionAfterThis(), widthWithOutBorder, freeHeight * 1 / 3), "testInfo");

        this.SetDrawableArea(area);
    }

   /* Card.InitSmall2 = function () {
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

    Card.prototype.InitSmall = function () {

        this.background = new AreaImage(new Area(new Point(0, 0), this.area.width, this.area.height));
        this.background.SetSource("Picture/Symbols/Card/VE.png");

        var height = (this.area.height - this.borderSize * 2) / 6;
        var width = (this.area.width - this.borderSize * 2);
        this.nameSmall = new AreaText(new Area(new Point(this.background.area.beginPoint.x + this.borderSize, this.background.area.beginPoint.y + this.borderSize),
            width, height), "testName");//new Area(new Point(0, 0), this.area.width, height ), "testName");
        width /= 2;
        this.energySmall = new AreaText(new Area(this.nameSmall.area.GetVerticalPositionAfterThis(),
            width, height), "0");
        this.hpSmall = new AreaText(new Area(this.energySmall.area.GetHorisontalPositionAfterThis(),
            width, height), "1");
        this.atackSmall = new AreaText(new Area(this.energySmall.area.GetVerticalPositionAfterThis(),
            width, height), "2");
        this.defenceSmall = new AreaText(new Area(this.atackSmall.area.GetHorisontalPositionAfterThis(),
            width, height), "3");
        this.imageSmall = new AreaImage(new Area(this.atackSmall.area.GetVerticalPositionAfterThis(), width * 2, height * 3));
        this.imageSmall.SetSource("Picture/Symbols/Card/Rokirovaka_support_VE.jpg");
    }

    Card.prototype.SetContextSmall = function (context) {
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

    Card.prototype.InitColorsForText = function () {
        this.nameSmall.background = "rgb(254, 254, 254)";
        this.energySmall.background = "rgb(254, 254, 254)";

        this.hpSmall.background = "rgb(254, 254, 254)";
        this.atackSmall.background = "rgb(254, 254, 254)";
        this.defenceSmall.background = "rgb(254, 254, 254)";

        this.nameSmall.textColor = "rgb(0, 0, 0)";
        this.energySmall.textColor = "rgb(0, 0, 0)";
    }

    Card.prototype.InitColorsForText2 = function () {
        this.nameSmall.textColor = "rgb(254, 254, 254)";
        this.energySmall.textColor = "rgb(254, 254, 254)";

        this.hpSmall.textColor = "rgb(254, 254, 254)";
        this.atackSmall.textColor = "rgb(254, 254, 254)";
        this.defenceSmall.textColor = "rgb(254, 254, 254)";
    }    */

    Card.prototype.SetContext = function (context) {
        for(name in this.images){
            this.images[name].context = context;
        }

        for(name in this.texts){
            this.texts[name].context = context;
            this.texts[name].isBorder = true;
            this.texts[name].textColor = "rgb(0, 0, 0)";
            this.texts[name].background = "rgb(254, 254, 254)";
        }
    }

    Card.prototype.SetDrawableArea = function (/*Area*/ drawableArea) {
        this.scale = CalculateScale(drawableArea);
        this.area.beginPoint = drawableArea.beginPoint;
    }

    /*int*/ Card.prototype.CalculateScale = function(/*Area*/ drawableArea){
        var widthScale = drawableArea.width / this.area.width;
        var heightScale = drawableArea.height / this.area.height;
        return (widthScale < heightScale ? widthScale : heightScale)/this.scale;

    }

    Card.prototype.PreDraw = function () {
        this.images["background"].context.save();

        this.images["background"].context.translate(this.area.beginPoint.x, this.area.beginPoint.y);
        this.images["background"].context.scale(this.scale, this.scale);
        this.images["background"].context.font = "25pt Arial";
    }

    Card.prototype.Draw = function(){
        for(var name in this.images){
            this.images[name].Draw();
        }

        for(var name in this.texts){
            this.texts[name].Draw();
        }
    }

    Card.prototype.PostDraw = function () {
        this.images["background"].context.restore();
    }

    /*Card.prototype.DrawSmall = function () {
        this.imageSmall.context.save();

        this.background.context.translate(this.area.beginPoint.x, this.area.beginPoint.y);
        this.background.context.scale(this.scale, this.scale);
        this.background.context.font = "35pt Arial";

        this.background.Draw();
        this.imageSmall.Draw();

        this.nameSmall.Draw();

        this.energySmall.Draw();

        this.imageSmall.context.restore();
    } */

}

Card.prototype = new Clickable();