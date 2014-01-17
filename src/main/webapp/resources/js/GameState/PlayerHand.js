function PlayerHand(){
    var baseCard = new Area(new Point(0, 0), consts.cardSize.width, consts.cardSize.height)
    var baseBorder = 15;

    this.fullArea;

    this.cards = [];
    this.areas = [];

    this.context;

    this.Init = function(/*Area*/ baseArea){
        var scale = baseArea.height / (baseCard.height + baseBorder*2);
        var width = baseCard.width * 5 + baseBorder * 6;
        var realWidth = width * scale;
        if(realWidth > baseArea.width){
            scale = baseArea.width / width;
            realWidth = width * scale;
        }
        var realHeight = baseCard.height * scale;
        var beginPoint = new Point (baseArea.width / 2, baseArea.height / 2);
        var centerPoint = new Point(realWidth/2, realHeight/2);
        beginPoint.MinusFromThis(centerPoint);
        beginPoint.AddToThis(baseArea.beginPoint);

        this.fullArea = new Area(beginPoint, realWidth, realHeight);
        canvasManager.addNewCanvas(this.fullArea,consts.canvasesName.playerHand, consts.zIndex.control);
        }

    this.onClickListener = function(e){
        alert("On playerHand click; "+e);
    }
}
