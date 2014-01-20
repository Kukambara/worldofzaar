function PlayerHand(){
    var baseCard = new Area(new Point(0, 0), consts.cardSize.width, consts.cardSize.height)
    var baseBorder = 15;
    var cardSize = 5;

    this.fullArea;
    this.scale = 1;

    this.cards = [];
    this.areas = [];

    this.context;

    this.Init = function(/*Area*/ baseArea){
        this.scale = baseArea.height / (baseCard.height + baseBorder*2);
        var width = baseCard.width * 5 + baseBorder * 6;
        var realWidth = width * this.scale;
        if(realWidth > baseArea.width){
            this.scale = baseArea.width / width;
            realWidth = width * this.scale;
        }
        var realHeight = baseCard.height * this.scale;
        var beginPoint = new Point (baseArea.width / 2, baseArea.height / 2);
        var centerPoint = new Point(realWidth/2, realHeight/2);
        beginPoint.MinusFromThis(centerPoint);
        beginPoint.AddToThis(baseArea.beginPoint);

        this.fullArea = new Area(beginPoint, realWidth, realHeight);
        canvasManager.addNewCanvas(this.fullArea,consts.canvasesName.playerHand, consts.zIndex.control);
        canvasManager.getCanvasByName(consts.canvasesName.playerHand).setScale(this.scale);

        var beginX = baseBorder;
        this.context = canvasManager.getCanvasByName(consts.canvasesName.playerHand).getContext();
        for(var i = 0; i < cardSize; ++i){
           this.areas[i] = new Area(new Point(beginX, baseBorder), baseCard.width, baseCard.height);
            this.cards[i] = new Card( this.areas[i]);
            this.cards[i].SetContext(this.context);
            this.cards[i].setCardByID(i);
            beginX +=  baseCard.width + baseBorder;
        }
        var data = GameAPI.GetUserCardsInHand();
    }

    this.onClickListener = function(e){
        alert("On playerHand click; "+e);
    }
    this.draw = function(){
        for(var card in this.cards){
            this.cards[card].Draw();
        }

    }

}
