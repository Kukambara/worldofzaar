(function () {
	var canvasStaticName = "canvas_static";
	function getCanvasStaticName() {
		return canvasStaticName;
	};
    function stateEnum(){
        this.loading = "loading";
        this.loaded = "loaded";
        this.gamePlaceCard= "gamePlaceCard";
        this.gameFight= "gameFight";
        this.gameNegative= "gameNegative";
        this.endGame = "endGame";
        this.closedGame = "closedGame";
    }
    var gameState = new stateEnum();

    function CardSize(){
        this.width = 400;
        this.height = 600;
        this.border = 5;
    }
    var cardSize = new CardSize();

    function CanvasesName(){
        this.static = "canvas_static";
        this.field = "canvas_field_";
        this.avatar = "avatar";
        this.control = "canvas_control"
        this.playerHand = "canvas_playerHand";
    }
    var canvasesName = new CanvasesName();

    function ZIndex(){
        this.hide = -1;
        this.static = 0;
        this.field = 1;
        this.control = 2;
        this.message = 3;
    }
    var zIndex = new ZIndex();

    window.consts = {
		getCanvasStaticName: getCanvasStaticName
        , gameState: gameState
        , cardSize : cardSize
        , canvasesName:canvasesName
        , zIndex:zIndex

	};
})();