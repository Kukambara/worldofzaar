function MasterOfDeck() {

    //-----------------------------Канвас-----------------------
    var canvas;
    //-----------------------------------------------------------------------


    //--------------------------Переменные характеристики--------------------


    var currCardsRowIndex = 0;                //Индекс строки отрисовки
    var isBuyingCards = true;               //флаг: true- покупаем; false - продаем

    var cardsWidth = 85;
    var cardsHeigth = 120;
    //-----------------------------------------------------------------------


    //------------------------Массивы данных--------------------------------

    var allMasterOfDeckCards = [];              //Все пользовательские карты
    var allPlayerCards = [];
    var transitionArrows = [];           //Стрелки навигации
    var loadedCards = [];
    var loadedBorders = [];
    var loadedLabels = [];
    var loadedBackground = [];
    var loadedStatic = [];
    var loadedActionImg =[];
    var masterOfDeckMap = [];
    var navigationBar;
    var player;
    var loadedCardIndex=0;
    var loadedCardId = 0;
    //-----------------------------------------------------------------------

    //--------------------------Входная точка---------------------------------
    function OnLoadCanvas() {
        canvas = new Canvas();
        canvas.Init();
        canvas.ReInitCanvasEventLevel();

        getUserCards();
        getMasterOfDeckCards();
        getUser();
        setTransitionElements();
        setCardList();
        setBackground();
        setBorders();
        setLables();
        setMasterOfDeckMap();
        setNavigationBar();
        setStaticElements();

        setTimeout(function () {
            Draw(loadedBackground);
            navigationBar.Init();
            setTimeout(function () {
                Draw(loadedCards);
                Draw(loadedBorders);
                Draw(loadedLabels);
                Draw(loadedStatic);
                Draw(transitionArrows);
                setEvents(0);
            }, 200);

        }, 50);
    }

    OnLoadCanvas();
    //-----------------------------------------------------------------------

    //------------------------------События-----------------------------------

    function OnClickWrapper(event) {
        var point = new Point((event.pageX - canvas.CanvasPos_X) * canvas.CanvasScale, (event.pageY - canvas.CanvasPos_Y) * canvas.CanvasScale);
        for (var i = 0; i < masterOfDeckMap.length; i++) {
            if (masterOfDeckMap[i][0].IsPointInArea(point)) {
                masterOfDeckMap[i][1](point);
                break;
            }
        }
    }

    function OnMoveWrapper(event) {
        var point = new Point((event.pageX - canvas.CanvasPos_X) * canvas.CanvasScale, (event.pageY - canvas.CanvasPos_Y) * canvas.CanvasScale);
        for (var i = 0; i < masterOfDeckMap.length; i++) {
            masterOfDeckMap[i][2](point);
        }
    }

    function OnMoveTransitionElement(point) {
        for (var i = 0; i < transitionArrows.length; ++i) {
            transitionArrows[i].OnMove(point);
        }
    }

    function OnMoveNavigationBar(point) {
        navigationBar.OnMove(point);
    }

    function OnClickTransitionElement(point) {
        for (var i = 0; i < transitionArrows.length; ++i) {
            var state = transitionArrows[i].OnClick(point);
            if (state) {
                if(transitionArrows[i].GetDirection() == 0){
                    isBuyingCards=!isBuyingCards;
                    currCardsRowIndex = 0;
                    canvas.GetCanvasContextLevel(1).clearRect(0,100,900,800);
                    ReDrawCanvas();
                    break;
                }

                if (currCardsRowIndex + transitionArrows[i].GetDirection() >= 0) {
                    currCardsRowIndex += transitionArrows[i].GetDirection();
                    setCardList();
                    Draw(loadedCards);
                    break;
                }
            }
        }
    }

    function OnClickNavigationBar(point) {
        navigationBar.OnClick(point)
    }

    function OnClickCardBuy(event) {
        var point = new Point((event.pageX - canvas.CanvasPos_X) * canvas.CanvasScale, (event.pageY - canvas.CanvasPos_Y) * canvas.CanvasScale);

        for (var i = 0; i < loadedActionImg.length; ++i) {
            if (loadedActionImg[i].OnClick(point)) {
                if(loadedActionImg[i].GetDirection()){
                    BuyCard();
                }
                setEvents(0);
                canvas.GetCanvasContextLevel(2).clearRect(0,0,900,900);
                break;
            }
        }
    }

    function OnClickCardSell(event) {
        var point = new Point((event.pageX - canvas.CanvasPos_X) * canvas.CanvasScale, (event.pageY - canvas.CanvasPos_Y) * canvas.CanvasScale);

        for (var i = 0; i < loadedActionImg.length; ++i) {
            if (loadedActionImg[i].OnClick(point)) {
                if(loadedActionImg[i].GetDirection()){
                    SellCard();
                }
                setEvents(0);
                canvas.GetCanvasContextLevel(2).clearRect(0,0,900,900);
                break;
            }
        }

    }

    function OnClickCardAction(point){
        for (var i = 0; i < loadedCards.length; ++i) {
            if (loadedCards[i].OnClick(point)) {
                loadedActionImg = [];

                var area = new Area (new Point(10,120),400,600);
                var card;
                var info = [];
                if(!isBuyingCards){
                    card= initPlayerCard(allPlayerCards[(currCardsRowIndex*6)+i],area,2);}
                else{
                    card= initMasterOfDeckCard(allMasterOfDeckCards[(currCardsRowIndex*6)+i],area,2);

                    info[0] = new AreaText(new Area(new Point(600, 200), 50, 50),allMasterOfDeckCards[(currCardsRowIndex*3)+i].price);
                    info[0].SetContext(canvas.GetCanvasContextLevel(2));
                    info[0].textColor = "rgb(255,185,15)";
                    info[0].fontStyle ='italic 20pt Calibri';

                    info[1] = new AreaText(new Area(new Point(600, 250), 50, 50),allMasterOfDeckCards[(currCardsRowIndex*3)+i].donatePrice);
                    info[1].SetContext(canvas.GetCanvasContextLevel(2));
                    info[1].textColor = "rgb(192,192,192)";
                    info[1].fontStyle ='italic 20pt Calibri';

                    info[2] = new AreaText(new Area(new Point(600, 300), 50, 50),"Card Level: "+allMasterOfDeckCards[(currCardsRowIndex*3)+i].cardLevel);
                    info[2].SetContext(canvas.GetCanvasContextLevel(2));
                    info[2].textColor = "rgb(192,192,192)";
                    info[2].fontStyle ='italic 20pt Calibri';
                }
                loadedCardIndex = (currCardsRowIndex*6)+i;
                //loadedCardId = card.cardId;

                if(!isBuyingCards){
                    loadedActionImg[0] = new TransitionArrow(false, "MasterOfDeck", "sellCard", canvas.GetCanvasContextLevel(2), new Area(new Point(430 * canvas.CanvasScale, 590* canvas.CanvasScale), 432, 67), 1);
                    loadedActionImg[1] = new TransitionArrow(false, "MyChambers", "cancel",canvas.GetCanvasContextLevel(2), new Area(new Point(510 * canvas.CanvasScale, 660 * canvas.CanvasScale), 240, 57), 0);

                    setEvents(-1);
                }else{
                    loadedActionImg[0] = new TransitionArrow(false, "MasterOfDeck", "buyCard", canvas.GetCanvasContextLevel(2), new Area(new Point(430 * canvas.CanvasScale, 590* canvas.CanvasScale), 460, 68), 1);
                    loadedActionImg[1] = new TransitionArrow(false, "MyChambers", "cancel",canvas.GetCanvasContextLevel(2), new Area(new Point(510 * canvas.CanvasScale, 660 * canvas.CanvasScale), 240, 57), 0);
                    setEvents(1);
                }
                var background = new AreaImage(new Area(new Point(0,0),900,900));
                background.SetContext(canvas.GetCanvasContextLevel(2));
                background.SetSource("resources\\Images\\Backgrounds\\actionBackground.png");
                background.Draw();

                card.Draw();
                card.DrawEnd();
                Draw(info);
                loadedActionImg[0].Load();
                loadedActionImg[1].Load();
                break;
            }
        }

    }

    function OnMoveCardAction(event){
        var point = new Point((event.pageX - canvas.CanvasPos_X) * canvas.CanvasScale, (event.pageY - canvas.CanvasPos_Y) * canvas.CanvasScale);
        for (var i = 0; i < loadedActionImg.length; ++i) {
            loadedActionImg[i].OnMove(point);
        }
    }

    //-----------------------------------------------------------------------

    //-----------------------------Отрисовка-----------------------------------

    function DrawFriend() {
    }

    function DrawPlayer() {
    }

    function Draw(input) {
        for (var i = 0; i < input.length; ++i) {
            if (input[i].__proto__ instanceof Card) {
                input[i].ClearDrawableArea(canvas.GetCanvasContextLevel(1));
                input[i].Draw();
                input[i].DrawEnd();
            }
            else {
                input[i].Draw();
            }

        }
    }

    function ReDrawCanvas() {

        setTransitionElements();
        setCardList();
        setLables();
        setMasterOfDeckMap();
        setTimeout(function () {
                Draw(loadedCards);
                Draw(loadedLabels);
                Draw(loadedStatic);
                Draw(transitionArrows);
                setEvents(0);
        }, 200);
    }

    //-----------------------------------------------------------------------

    //-----------------------SetElements------------------------------

    function setTransitionElements() {
        var deckArrowWidth = 38 * canvas.CanvasScale;
        var deckArrowHeigth = 53 * canvas.CanvasScale;

        transitionArrows[0] = new TransitionArrow(false, "MasterOfDeck", "upCards", canvas.GetCanvasContextLevel(1), new Area(new Point(302 * canvas.CanvasScale, 145 * canvas.CanvasScale), deckArrowWidth, deckArrowHeigth), -1);
        transitionArrows[1] = new TransitionArrow(false, "MasterOfDeck", "downCards", canvas.GetCanvasContextLevel(1), new Area(new Point(825 * canvas.CanvasScale, 145 * canvas.CanvasScale), deckArrowWidth, deckArrowHeigth), 1);

        if(isBuyingCards){
            transitionArrows[2] = new TransitionArrow(false, "MasterOfDeck", "sellCards", canvas.GetCanvasContextLevel(1), new Area(new Point(60 * canvas.CanvasScale, 630 * canvas.CanvasScale), 163, 87), 0);
        }else{
            transitionArrows[2] = new TransitionArrow(false, "MasterOfDeck", "sellCards", canvas.GetCanvasContextLevel(1), new Area(new Point(60 * canvas.CanvasScale, 630 * canvas.CanvasScale), 163, 87), 0);

        }
        for (var i = 0; i < transitionArrows.length; ++i) {
            transitionArrows[i].Load();
        }
    }

    function setCardList() {
        switch (isBuyingCards) {
            case false:
                setAllUserCards();
                break;
            case true:
                setAllMasterOfDeckCards();
                break;
        }
    }

    function setAllUserCards() {
        var curr_x = 300;
        var curr_y = 230;
        var cardsInRow = 6;
        var restart_X = 300;
        loadedCards = [];

        for (var i = 0; i < 4 * cardsInRow; i++) {

            if (allPlayerCards[i + (currCardsRowIndex * cardsInRow)]) {
                loadedCards[i] = initPlayerCard(allPlayerCards[i + (currCardsRowIndex * cardsInRow)],
                    new Area(new Point(curr_x, curr_y), cardsWidth, cardsHeigth), 1);
                //loadedCardsUserId[i] = allPlayerCards1[i + (currCardsRowIndex * cardsInRow)].userCardId;

            }
            else {
                var emptyHollow = new EmptyHollow(canvas.GetCanvasContextLevel(1), new Area(new Point(curr_x, curr_y), cardsWidth, cardsHeigth));
                EmptyCard.prototype = emptyHollow;
                loadedCards[i] = new EmptyCard("MasterOfDeck");
            }

            if ((i + 1) % cardsInRow != 0) {
                curr_x += cardsWidth + 10;
            }
            else {
                curr_y += cardsHeigth + 10;
                curr_x = restart_X;
            }
        }


    }

    function setAllMasterOfDeckCards() {
        var curr_x = 300;
        var curr_y = 230;
        var cardsInRow = 6;
        var restart_X = 300;
        loadedCards = [];

        for (var i = 0; i < 4 * cardsInRow; i++) {

            if (allMasterOfDeckCards[i + (currCardsRowIndex * cardsInRow)]) {
                loadedCards[i] = initMasterOfDeckCard(allMasterOfDeckCards[i + (currCardsRowIndex * cardsInRow)],
                    new Area(new Point(curr_x, curr_y), cardsWidth, cardsHeigth), 1);
                // loadedCardsUserId[i] = allPlayerCards[i + (currCardsRowIndex * cardsInRow)].userCardId;

            }
            else {
                var emptyHollow = new EmptyHollow(canvas.GetCanvasContextLevel(1), new Area(new Point(curr_x, curr_y), cardsWidth, cardsHeigth));
                EmptyCard.prototype = emptyHollow;
                loadedCards[i] = new EmptyCard("MyChambers");
            }

            if ((i + 1) % cardsInRow != 0) {
                curr_x += cardsWidth + 10;
            }
            else {
                curr_y += cardsHeigth + 10;
                curr_x = restart_X;
            }
        }


    }

    function setBackground() {
        var index = loadedBackground.length;
        loadedBackground[index] = new AreaImage(new Area(new Point(), 900, 900));
        loadedBackground[index].SetContext(canvas.GetCanvasContextLevel(0));
        loadedBackground[index].SetSource("resources\\Images\\Backgrounds\\masterOfDeckBackground.png");
    }

    function setLables() {
        loadedLabels[0] = new AreaImage(new Area(new Point(400, 145), 376, 45));
        loadedLabels[0].SetContext(canvas.GetCanvasContextLevel(1));
        switch (isBuyingCards) {
            case false:
                loadedLabels[0].SetSource("resources\\Images\\MasterOfDeck\\Lables\\sellCardsLable.png");
                break;
            case true:
                loadedLabels[0].SetSource("resources\\Images\\MasterOfDeck\\Lables\\buyCardsLable.png");
                break;
            default:
                break;
        }
    }

    function setBorders() {
        var index = loadedBorders.length;
        loadedBorders[index] = new AreaImage(new Area(new Point(280, 120), 608, 650), canvas.GetCanvasContextLevel(1));
        loadedBorders[index].SetContext(canvas.GetCanvasContextLevel(0));
        loadedBorders[index].SetSource("resources\\Images\\MasterOfDeck\\Borders\\cardsBorder.png");
    }

    function setStaticElements() {
        loadedStatic[0] = new AreaImage(new Area(new Point(10, 150), 262, 331));
        loadedStatic[0].SetContext(canvas.GetCanvasContextLevel(0));
        loadedStatic[0].SetSource("resources\\Images\\MasterOfDeck\\Hollows\\master.png");

        loadedStatic[1] = new AreaText(new Area(new Point(30, 550), 50, 50),player.gameProfile.money);
        loadedStatic[1].SetContext(canvas.GetCanvasContextLevel(1));
        loadedStatic[1].textColor = "rgb(255,185,15)";
        loadedStatic[1].fontStyle ='italic 20pt Calibri';

        loadedStatic[2] = new AreaText(new Area(new Point(100, 550), 50, 50),player.gameProfile.donateMoney);
        loadedStatic[2].SetContext(canvas.GetCanvasContextLevel(1));
        loadedStatic[2].textColor = "rgb(192,192,192)";
        loadedStatic[2].fontStyle ='italic 20pt Calibri';
    }

    function setNavigationBar() {
        navigationBar = new NavigationBar(canvas, 1);
    }

    function setMasterOfDeckMap() {
        masterOfDeckMap[0] = [];
        masterOfDeckMap[0][0] = new Area(new Point(0, 0), 900, 100);
        masterOfDeckMap[0][1] = function (point) {
            OnClickNavigationBar(point);
        };
        masterOfDeckMap[0][2] = function (point) {
            OnMoveNavigationBar(point);
        };

        //masterOfDeckMap[1] = [];
        //masterOfDeckMap[1][0] = new Area(new Point(40, 200), deckWidth, (deckHeigth * 2) + 50);
        //masterOfDeckMap[1][1] = function (point) { OnClickDeck(point); };
        //masterOfDeckMap[1][2] = function (point) { };

        masterOfDeckMap[1] = [];
        masterOfDeckMap[1][0] = new Area(new Point(300, 130), 600, 100);
        masterOfDeckMap[1][1] = function (point) {
            OnClickTransitionElement(point);
        };
        masterOfDeckMap[1][2] = function (point) {
            OnMoveTransitionElement(point);
        };

        masterOfDeckMap[2] = [];
        masterOfDeckMap[2][0] = new Area(new Point(60, 630), 163, 87);
        masterOfDeckMap[2][1] = function (point) {
            OnClickTransitionElement(point);
        };
        masterOfDeckMap[2][2] = function (point) {
            OnMoveTransitionElement(point);
        };

        masterOfDeckMap[3] = [];
        masterOfDeckMap[3][0] = new Area(new Point(300, 230), cardsWidth * 6 + 100, cardsHeigth * 4 + 100);
        masterOfDeckMap[3][1] = function (point) {
            OnClickCardAction(point);
        };
        masterOfDeckMap[3][2] = function (point) {
            return;
        };

        //myChambersMap[4] = [];
        //myChambersMap[4][0] = new Area(new Point(0, 0), 900, 100);
        //myChambersMap[4][1] = function (point) { OnClickTopBar(point); };

    }

    function setEvents(id) {
        canvas.ReInitCanvasEventLevel();
        switch (id) {
            case 0:
                canvas.GetCanvasLevel(3).addEventListener("click", OnClickWrapper);
                canvas.GetCanvasLevel(3).addEventListener("mousemove", OnMoveWrapper);
                break;
            case 1:canvas.GetCanvasLevel(3).addEventListener("click", OnClickCardBuy);
                canvas.GetCanvasLevel(3).addEventListener("mousemove", OnMoveCardAction);
                break;
            case -1:canvas.GetCanvasLevel(3).addEventListener("click", OnClickCardSell);
                canvas.GetCanvasLevel(3).addEventListener("mousemove", OnMoveCardAction);
                break;
        }
    }

    function BuyCard(){
        $.ajax({
            url: "/profile/cards/buy/"+allMasterOfDeckCards[loadedCardIndex].mastersCardId+"/false",
            dataType: "json",
            type: "POST",
            async: false,
            success: function () {
            }
        });

        getUserCards();
        ReDrawCanvas();
    }

    function SellCard(){
        $.ajax({
            url: "/profile/cards/sell/"+allPlayerCards[loadedCardIndex].userCardId,
            dataType: "json",
            type: "POST",
            async: false,
            success: function () {
            }
        });

        getUserCards();
        ReDrawCanvas();
    }

    function getUser(){
        $.ajax({
            url: "/profile/gameProfile/",
            dataType: "json",
            type: "GET",
            async: false,
            success: function (data) {
                player = data;
            }
        });
    }

    function getUserCards() {
        $.ajax({
            url: "/profile/userCards/",
            dataType: "json",
            type: "GET",
            async: false,
            success: function (data) {
                allPlayerCards = data;
            }
        });
    }

    function getMasterOfDeckCards() {
        $.ajax({
            url: "/profile/masterOfDeckCards/",
            dataType: "json",
            type: "GET",
            async: false,
            success: function (data) {
                allMasterOfDeckCards = data;
            }
        });
    }

    function initPlayerCard(data, area, canvaslevel) {
        var card;
        var isWarrior;
        var cardObject;
        if (data.warriorCard != null) {
            card = new CardFighter();
            cardObject = data.warriorCard;
        }
        else {
            card = new CardSupport();
            cardObject = data.supportCard;

        }

        card.Init(area, cardObject, data.cardName, data.cardSlogan, data.cardProperty);
        card.SetContext(canvas.GetCanvasContextLevel(canvaslevel));
        card.SetContextSmall(canvas.GetCanvasContextLevel(canvaslevel));

        return card;
    }

    function initMasterOfDeckCard(data, area, canvaslevel) {
        var card;
        var isWarrior;
        var cardObject;
        if (data.warriorCard != null) {
            card = new CardFighter();
            cardObject = data.warriorCard;
        }
        else {
            card = new CardSupport();
            cardObject = data.supportCard;

        }

        card.Init(area, cardObject, cardObject.cardName, cardObject.cardSlogan, cardObject.cardProperty);
        card.SetContext(canvas.GetCanvasContextLevel(canvaslevel));
        card.SetContextSmall(canvas.GetCanvasContextLevel(canvaslevel));

        return card;
    }

    //-----------------------------------------------------------------------

}