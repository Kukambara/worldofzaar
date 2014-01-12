(function () {
	var canvasStaticName = "canvas_static";
	function getCanvasStaticName() {
		return canvasStaticName;
	};

    var gameState = function (){
        this.loading = "loaading";
        this.loaded = "loaded";
        this.gamePlaceCard= "gamePlaceCard";
        this.gameFight= "gameFight";
        this.gameNegative= "gameNegative";
        this.gameWait= "gameWait";
        this.pause = "pause";

    }
    window.consts = {
		getCanvasStaticName: getCanvasStaticName
        , gameState: gameState()

	};
})();