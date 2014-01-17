(function () {
	var lastTime;

	var background;
	var map = new Map();

	var canvasesName = [];
	var buttons = [];
	var players = [];
    var playerHand;
	var globalState;
    var actionState;
    var isPause = false;
	var activePlayerNumber = 0;
    var playerCount = 4;
    var firstNumberInRound = 0;

	var time;
	var timer;

	window.requestAnimFrame = (function (callback) {
		return window.requestAnimationFrame || window.webkitRequestAnimationFrame
		|| window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame ||
		function (callback) {
			window.setTimeout(callback, 1000 / 60);
		};
	})();

	function init(/*string*/ canvasLoadingName) {
        setState(consts.gameState.loading);
		canvasesName["loading"] = canvasLoadingName;
		canvasManager.addCanvas(canvasLoadingName);

		canvasesName["static"] = consts.canvasesName.static;
		canvasManager.copyCanvas(canvasLoadingName, canvasesName["static"], consts.zIndex.static);
		_initBackground();
		map.Init(canvasManager.getCanvasByName(canvasesName["static"]), 50, 50, 700, 700);

		canvasesName["control"] = consts.canvasesName.control;
		canvasManager.copyCanvas(canvasesName["static"], canvasesName["control"], consts.zIndex.control);

        var rotate = 0;
        var canvasStyle = canvasManager.getCanvasByName(canvasesName["static"]).canvas.style;
        var canvasStaticBeginPoint = new Point( parseInt(canvasStyle.top), parseInt(canvasStyle.left) );
        //canvasStaticBeginPoint.ConvertThisFromStyle();
        for(var i = 0; i < map.playerField.length; ++i){
            players[i] = new PlayerField();
            players[i].InitAreasForCards(map.playerField[i].fullArea);
            canvasesName["field_"+i]= consts.canvasesName.field+i;
            var area = map.playerField[i].fullArea.GetClone();
            area.beginPoint.AddToThis(canvasStaticBeginPoint);
            area.Rotate(rotate);
            canvasManager.addNewCanvas(area, canvasesName["field_"+i], consts.zIndex.field);
            rotate+=90;
            players[i].rotate = rotate;
            players[i].SetContext(canvasManager.getCanvasByName(canvasesName["field_"+i]).context);
            players[i].ApplyScale();
            players[i].ApplyRotation();
            players[i].fullArea.isBorder = true;
           // canvasManager.addEventFunction(canvasesName["field_"+i], this, "onClick", "OnCardClickHelper"+i);
        }
        _initButtons(canvasesName["control"]);

        playerHand = new PlayerHand();
        var canvasArea =  map.background.area;
        var handBeginPoint = canvasManager.getCanvasByName(canvasesName["field_0"]).getCanvasArea().GetVerticalPositionAfterThis();
        handBeginPoint.x = 0;
        var q =   canvasManager.getCanvasByName(canvasesName["loading"]).getCanvasArea().beginPoint;
        handBeginPoint.AddToThis(canvasManager.getCanvasByName(canvasesName["loading"]).getCanvasArea().beginPoint);

        var handEndPoint = canvasManager.getCanvasByName(canvasesName["static"]).getCanvasArea().GetVerticalPositionAfterThis();
        var h =  handEndPoint.y -   handBeginPoint.y;

        var handAreaWidth = map.background.area.width;
        var w =     canvasManager.getCanvasByName(canvasesName["loading"]).getCanvasArea().height;
        var handAreaHeight = canvasManager.getCanvasByName(canvasesName["static"]).getCanvasArea().GetVerticalPositionAfterThis().y - handBeginPoint.y;
        var handArea = new Area(handBeginPoint,
            map.background.area.width,
            canvasManager.getCanvasByName(canvasesName["loading"]).getCanvasArea().height - handBeginPoint.y);
        playerHand.Init(handArea);
        canvasManager.addEventFunction(
            consts.canvasesName.playerHand,
            playerHand,
            "onClick",
            "onClickListener");


        canvasManager.addEventFunction(canvasesName["static"], window.game, "onClick", "onClickListener");

		resources.onReady(postInit);
	}

	function _initBackground() {
		resources.loadByUrl("/resources/Images/GameState/table_2.png");
		background = new AreaImage(
					new Area(new Point(0, 0), 1000, 1000),
					new Image(1000, 1000));
		background.image.src = "/resources/Images/GameState/table_2.png";
		background.context = canvasManager.getCanvasByName(canvasesName["loading"]).getContext();
	}

    function _initButtons(canvasControlName){
        timer = new AreaText(new Area(new Point(900, 827), 50, 40), 0);
        timer.context = canvasManager.getCanvasByName(canvasControlName).getContext();
        time = 0;
        canvasManager.getCanvasByName(canvasControlName).getContext().font = "15pt Arial";
        eventManager.addListener(window.game, "onFieldClick", "onCardTestClick");

        lastTime = Date.now();

        resources.loadByUrl("/resources/Images/GameState/Symbols/borders/buttonUnpresed.png");
        var buttonUnpresed = new Image(100, 40);
        buttonUnpresed.src = "/resources/Images/GameState/Symbols/borders/buttonUnpresed.png";

        buttons["skip"] = new Button();
        buttons["skip"].Init(new Area(new Point(830, 870), 150, 37)
            , buttonUnpresed, ""
            , canvasManager.getCanvasByName(canvasControlName).getContext()
        );
        buttons["skip"]._OnClick = onButtonSkipClick;

        buttons["chat"] = new Button();
        buttons["chat"].Init(new Area(buttons["skip"].area.GetVerticalPositionAfterThis(), 75, 37)
            , buttonUnpresed, ""
            , canvasManager.getCanvasByName(canvasControlName).getContext()
        );
        buttons["chat"]._OnClick = onButtonChatClick;

        buttons["log"] = new Button();
        buttons["log"].Init(new Area(buttons["chat"].area.GetHorisontalPositionAfterThis(), 75, 37)
            , buttonUnpresed, ""
            , canvasManager.getCanvasByName(canvasControlName).getContext()
        );
        buttons["log"]._OnClick = onButtonLogClick;

        buttons["surrender"] = new Button();
        buttons["surrender"].Init(new Area(buttons["chat"].area.GetVerticalPositionAfterThis(), 150, 37)
            , buttonUnpresed, ""
            , canvasManager.getCanvasByName(canvasControlName).getContext()
        );
        buttons["surrender"]._OnClick = onButtonSurrenderClick;

        for(var name in buttons){
            buttons[name].area.isBorder = true;
        }

        canvasManager.addEventFunction(canvasControlName, window.game, "onClick", "onClickListener");
    }


	function main() {
		var now = Date.now();
		var deltaTime = (now - lastTime) / 1000.0;
		update(deltaTime);
		//draw();

		lastTime = now;
		requestAnimFrame(main);

	}

	function update(deltaTime) {
        if(!isPause)
        {
            time += deltaTime;
            timer.DrawNewText(Math.floor(time));
            if(time > 60){
                PassCourse();
            }
        }
		//alert(deltaTime);
    }

    function PassCourse(){
        time = 0;
        var mess =   "change player from "+ activePlayerNumber;
        ++activePlayerNumber;
        if(activePlayerNumber >= playerCount )  {
            activePlayerNumber = 0;
        }
        mess += " to "+ activePlayerNumber;
         alert( mess );
        if(activePlayerNumber == firstNumberInRound){
            switch (state){
                case consts.gameState.gamePlaceCard:
                    state = consts.gameState.gameFight;
                    break;
                case consts.gameState.gameFight:
                    state = consts.gameState.gameNegative;
                    break;
                case consts.gameState.gameNegative:
                    state = consts.gameState.gamePlaceCard;
                    ++firstNumberInRound;
                    if(firstNumberInRound >= playerCount )  {
                        firstNumberInRound = 0;
                    }
                    break;
                alert( "change state to "+state );
            }
        }
    }

	function render() {
		alert("render");
	}

    function setState(/*string*/ newState){
        state = newState;
    }

    /*string*/ function getState(){
        return state;
    }

	function onClickListener(eventPoint) {

		map.OnClick(eventPoint);
        for(var name in buttons){
            buttons[name].onClick(eventPoint);
        }
	}

	function postInit() {
		canvasManager.setCanvasZindex(canvasesName["loading"], consts.zIndex.hide);
		background.Draw();
		map.Draw();
        for(var name in buttons){
            buttons[name].Draw();
        }
		timer.Draw();
        main();
        setState(consts.gameState.loaded);
        setState(consts.gameState.gamePlaceCard);
	}

	function draw() {
		//map.Draw();
		//buttonTest.Draw();
	}

	/*Clicks*/
	function onButtonLogClick(eventPoint) {
		alert("onButtonLogClick");
	}

    function onButtonChatClick(eventPoint){
        alert("onButtonChatClick");
    }

    function onButtonSkipClick(eventPoint){
        alert("onButtonSkipClick");
    }

    function onButtonSurrenderClick(eventPoint){
        alert("onButtonSurrenderClick");
        OnPauseClick(eventPoint);
    }

    function onCardTestClick(data) {
        alert("fieldId:"+data[0]+"\ncardID:"+data[1]);
    }

	/*string*/function ConvertSecondIntoString(/*int*/ seconds) {
		var timeWithOutMIllicesonds = Math.floor(seconds);
		var minutes = Math.floor(timeWithOutMIllicesonds / 60);
		var onlyseconds = seconds % 60;
		return minutes + ":" + onlyseconds;
	}

    function OnCardClickHelper0(point){
       players[0].OnCardClick(point);
    }

    function OnCardClickHelper1(point){
        players[1].OnCardClick(point);
    }

    function OnCardClickHelper2(point){
        players[2].OnCardClick(point);
    }

    function OnCardClickHelper3(point){
        players[3].OnCardClick(point);
    }

    function OnPauseClick(point){
        isPause = !isPause;
    }


	window.game = {
        init: init
        , setState: setState
        , getState: getState
		, onClickListener: onClickListener
        , onCardTestClick: onCardTestClick
        , OnCardClickHelper0:OnCardClickHelper0
        , OnCardClickHelper1:OnCardClickHelper1
        , OnCardClickHelper2:OnCardClickHelper2
        , OnCardClickHelper3:OnCardClickHelper3
        , OnPauseClick: OnPauseClick
	}
})();