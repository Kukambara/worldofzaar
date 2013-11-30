function MyChambers() {

    //-----------------------------Канвас----------------------------------
    var canvas;

    //--------------------------Переменные характеристики--------------------


    var currCardsRowIndex = 0;                //Индекс строки отрисовки
    var currDeckRowIndex = 0;
    var currDeckIndex = (-1);             //Индекс текущей активной деки. (-1) - никакая не активна 


    var cardsWidth = 85;
    var cardsHeigth = 120;

    var deckWidth = 170;
    var deckHeigth = 241;

    var isDeckEdit = false;                 //флаг

    //-----------------------------------------------------------------------
    //------------------------Массивы данных--------------------------------

    var cardsInDeck = [];                 //id дек и id карт
    var allPlayerCards = [];              //Все пользовательские карты
    var allPlayerDecks = [];             //Все пользовательские деки

    var transitionArrows = [];           //Стрелки навигации
    var loadedCards = [];
    var loadedDecks = [];
    var loadedBorders = [];
    var loadedLabels = [];
    var loadedBackground = [];

    var myChambersMap = [];
    var navigationBar;

    //-----------------------------------------------------------------------
    //------------------------Входная точка----------------------------------
    //-----------------------------------------------------------------------
    function OnLoadCanvas() {
        canvas = new Canvas();
        canvas.Init();
        test();
        setNavigationBar();
        setTransitionElements();
        setAllCards();
        setBackground();
        setBorders();
        setLables();
        setDecks();
        setMyChambersMap();

        setTimeout(function () {
            Draw(loadedBackground);
            navigationBar.Init();
            setTimeout(function () {
                Draw(loadedCards);
                Draw(loadedLabels);
                Draw(loadedBorders);
                Draw(loadedDecks);
                Draw(transitionArrows);
                canvas.CanvasMain.addEventListener("click", OnClickWraper);
                canvas.CanvasMain.addEventListener("mousemove", OnMoveWrapper);
            }, 200);

        }, 50);
    }

    OnLoadCanvas();

    //-----------------------------------------------------------------------
    //------------------------------События-----------------------------------
    //-----------------------------------------------------------------------

    function OnClickWraper(event) {
        var point = new Point((event.pageX - canvas.CanvasPos_X) * canvas.CanvasScale, (event.pageY - canvas.CanvasPos_Y) * canvas.CanvasScale);
        for (var i = 0; i < myChambersMap.length; i++)
        {
            if (myChambersMap[i][0].IsPointInArea(point))
            {
                myChambersMap[i][1](point);
                break;
            }
        }
    }

    function OnMoveWrapper(event)
    {
        var point = new Point((event.pageX - canvas.CanvasPos_X) * canvas.CanvasScale, (event.pageY - canvas.CanvasPos_Y) * canvas.CanvasScale);
        for (var i = 0; i < myChambersMap.length; i++) {
            //if (myChambersMap[i][0].IsPointInArea(point)) {
            //    myChambersMap[i][2](point);
            //    break;
            //}
            myChambersMap[i][2](point);
        }
    }

    function OnMoveTransitionElement(point) {
        for (var i = 0; i < transitionArrows.length; ++i) {
            transitionArrows[i].OnMove(point);
        }
    }

    function OnMoveNavigationBar(point){
        navigationBar.OnMove(point);
    }

    function OnClickTransitionElement(point) {
        for (var i = 0; i < transitionArrows.length; ++i) {
            var state = transitionArrows[i].OnClick(point);
            if (state) {
                switch (transitionArrows[i].isDeckArrow) {
                    case true:
                        currDeckRowIndex += transitionArrows[i].GetDirection();
                        if (currDeckRowIndex < 0) {
                            currDeckRowIndex = 0;
                            break;
                        }
                        //Вызов перерисовки
                        break;
                    case false:
                        currCardsRowIndex += transitionArrows[i].GetDirection();
                        if (currCardsRowIndex < 0) {
                            currCardsRowIndex = 0;
                            break;
                        }
                        //Вызов перерисовки
                        setAllCards();
                        setTimeout(function () {
                            Draw(loadedCards);
                        }, 10);
                        break;
                    default: break;
                }
            }
        }
    }    

    function OnClickDeck(point) {
        //Обработка клика, выбор деки, и подгрузка ее в CardsOverviewMap
        //Клик на активную деку ничего не делает (пока что)
        
        if (!isDeckEdit) {
            for (var i = 0; i < loadedDecks.length; ++i) {
                if (loadedDecks[i].OnClick(point)) {
                    if (currDeckIndex != i) {
                        currDeckIndex = i;
                    } else {
                        isDeckEdit = !isDeckEdit;
                    }
                }
            }
        }

        DrawAllCards();
    }

    function OnClickCard(point) {
        for (var i = 0; i < loadedCards.length; ++i) {
            if (loadedCards[i].OnClick(point)) {
                loadedCards[i].CardAction();
                //перерисовка карт
            }
        }

    }

    function OnClickNavigationBar(point) {
        navigationBar.OnClick(point)
    }
    //--------------------------------------------------------------------------
    //-----------------------------Отрисовка-----------------------------------
    //-----------------------------------------------------------------------
    function DrawFriend() {
    }

    function DrawPlayer() {

    }

    function Draw(input)
    {
        for (var i = 0; i < input.length; ++i)
        {
            input[i].Draw();
        }
    }
    //-----------------------------------------------------------------------
    //-----------------------Переключатели----------------------------------
    //-----------------------------------------------------------------------


    //-----------------------------------------------------------------------
    //-----------------------SetElements------------------------------------
    //-----------------------------------------------------------------------

    function setTransitionElements() {

        switch (isDeckEdit) {
            case true:
                var deckEditArrowWidth = 38 * canvas.CanvasScale;
                var deckEditArrowHeigth = 46 * canvas.CanvasScale;

                transitionArrows[0] = new TransitionArrow(false, "MyChambers", "upDeckEdit", canvas.CanvasMainContext, new Area(new Point(15 * canvas.CanvasScale, 140 * canvas.CanvasScale), deckEditArrowWidth, deckEditArrowHeigth), -1);
                transitionArrows[1] = new TransitionArrow(false, "MyChambers", "downDeckEdit", canvas.getContext("2d"), new Area(new Point(570 * canvas.CanvasScale, 140 * canvas.CanvasScale), deckEditArrowWidth, deckEditArrowHeigth), 1);

                break;
            case false:
                var deckArrowWidth = 38 * canvas.CanvasScale;
                var deckArrowHeigth = 53 * canvas.CanvasScale;
                var cardsArrowWidth = 38 * canvas.CanvasScale;
                var cardsArrowHeigth = 87 * canvas.CanvasScale;

                transitionArrows[0] = new TransitionArrow(true, "MyChambers", "upDeck", canvas.CanvasMainContext, new Area(new Point(15 * canvas.CanvasScale, 140 * canvas.CanvasScale), deckArrowWidth, deckArrowHeigth), -1);
                transitionArrows[1] = new TransitionArrow(true, "MyChambers", "downDeck", canvas.CanvasMainContext, new Area(new Point(205 * canvas.CanvasScale, 140 * canvas.CanvasScale), deckArrowWidth, deckArrowHeigth), 1);
                transitionArrows[2] = new TransitionArrow(false, "MyChambers", "upCards", canvas.CanvasMainContext, new Area(new Point(270 * canvas.CanvasScale, 140 * canvas.CanvasScale), cardsArrowWidth, cardsArrowHeigth), -1);
                transitionArrows[3] = new TransitionArrow(false, "MyChambers", "downCards", canvas.CanvasMainContext, new Area(new Point(570 * canvas.CanvasScale, 140 * canvas.CanvasScale), cardsArrowWidth, cardsArrowHeigth), 1);
                break;
            default:
                break;
        }

        for (var i = 0; i < transitionArrows.length; ++i) {
            transitionArrows[i].Load();
        }
    }

    function setAllCards()
    {
        var curr_x;
        var curr_y;
        var cardsInRow;
        var restart_X;
        loadedCards = [];

        switch (isDeckEdit) {
            case false:
                curr_x = 300;
                curr_y = 230;
                cardsInRow = 3;
                restart_X = 300;
                break;
            case true:
                curr_x = 30;
                curr_y = 220;
                cardsInRow = 3;
                restart_X = 30;
                break;
            default: break;
        }
        

        for (var i = 0  ; i < 12 ; i++) {
            if (allPlayerCards[i + (currCardsRowIndex * cardsInRow)]) {
                loadedCards[i] = allPlayerCards[i + (currCardsRowIndex * cardsInRow)]
                loadedCards[i].StartPointChange(new Area(new Point(curr_x, curr_y), cardsWidth, cardsHeigth));
                //loadedCards[i].SetDeckEntry(cardsEnabler(loadedCards[i].cardId));
            }
            else {
                var emptyHollow = new EmptyHollow(canvas.CanvasMainContext, new Area(new Point(curr_x, curr_y), cardsWidth, cardsHeigth));
                EmptyCard.prototype = emptyHollow;

                loadedCards[i] = new EmptyCard("MyChambers");
            }

            if ((i + 1) % cardsInRow != 0) {
                curr_x += cardsWidth + 15;
            }
            else {
                curr_y += cardsHeigth + 10;
                curr_x = restart_X;
            }
        }
    }

    function setDeckCards() {
        LoadedCards = [];
        var curr_x = cardsDrawingStart_X;
        var curr_y = cardsDrawingStart_Y;
        var startPass = 0;

        for (var i = 0 + (10 * page) ; i < 10 + (10 * page) ; ++i) {
            LoadedCards[i] = new Card("1.jpg", i, i, curr_x, curr_y, cardsHeigth, cardsWidth, сanvas);

            for (var j = startPass; j < playerCardsInDeck[currSetIndex].length; j++) {
                if (playerCardsInDeck[currSetIndex][j] == i) {
                    LoadedCards[i].SetDeckEntry(true);
                    startPass++;
                    break;
                }
            }

            LoadedCards[i].Draw();

            curr_x += 160;
            if (curr_x + 170 <= 800) {
            }
            else {
                curr_y += 210;
                curr_x = cardsDrawingStart_X;
            }
        }

    }

    function setBackground() {
        var bg = new Image();
        bg.src = "Images\\Backgrounds\\balikuruBackground.png";
        loadedBackground[0] = new AreaImage(new Area(new Point(0, 0), 900, 900), bg, canvas.CanvasBackgroundContext);
    }

    function setLables() {

        var deckLabelImg = new Image();
        var availableCardsImg = new Image();
        loadedLabels = [];
        switch (currDeckIndex != (-1)) {
            case false:
                deckLabelImg.src = "Images\\MyChambers\\Lables\\deck.png";
                availableCardsImg.src = "Images\\MyChambers\\Lables\\availableCards.png";
                break;
            case true:
                deckLabelImg.src = "Images\\MyChambers\\Lables\\deckActive.png";
                availableCardsImg.src = "Images\\MyChambers\\Lables\\availableCardsActive.png";
                break;
            default:
                break;
        }

        loadedLabels[0] = new AreaImage(new Area(new Point(45, 140), 163, 54), deckLabelImg, canvas.CanvasMainContext);
        loadedLabels[1] = new AreaImage(new Area(new Point(300, 140), 270, 93), availableCardsImg, canvas.CanvasMainContext);
    }

    function setBorders() {
        deckBorderImg = new Image();
        cardBorderImg = new Image();
        loadedBorders = [];
        switch (isDeckEdit) {
            case false:
                deckBorderImg.src = "Images\\MyChambers\\Borders\\cardsBorder.png";
                cardBorderImg.src = "Images\\MyChambers\\Borders\\deckBorder.png";

                loadedBorders[0] = new AreaImage(new Area(new Point(9, 120), 243, 652), deckBorderImg, canvas.CanvasMainContext);
                loadedBorders[1] = new AreaImage(new Area(new Point(260, 120), 363, 652), cardBorderImg, canvas.CanvasMainContext);
                break;
            case true:
                deckBorderImg.src = "Images\\MyChambers\\Borders\\deckEditBorder.png";
                loadedBorders[0] = new AreaImage(new Area(new Point(9, 120), 610, 652), deckBorderImg, canvas.CanvasMainContext);
                break;
            default: break;
        }
    }

    function setStaticElement() {

    }

    function setDecks() {
        var curr_x = 45;
        var curr_y = 200;
        loadedDecks = [];

        for (var i = 0  ; i < 2 ; i++) {
            if (allPlayerDecks[i]) {
                loadedDecks[i] = allPlayerDecks[i + (currDeckRowIndex)];
                loadedDecks[i].StartPointChange(new Point(curr_x, curr_y));
            }
            else {
                EmptyDeck.prototype = new EmptyHollow(canvas.CanvasMainContext, new Area(new Point(curr_x, curr_y), deckWidth, deckHeigth));

                loadedDecks[i] = new EmptyDeck(new Area(new Point(curr_x, curr_y), deckWidth, deckHeigth));
            }
            curr_y += deckHeigth + 40;
        }
    }

    function setNavigationBar() {
        navigationBar = new NavigationBar(canvas, 0);
    }

    function setMyChambersMap()
    {
        myChambersMap[0] = [];
        myChambersMap[0][0] = new Area(new Point(0, 0), 900, 100);
        myChambersMap[0][1] = function (point) { OnClickNavigationBar(point); };
        myChambersMap[0][2] = function (point) { OnMoveNavigationBar(point);};

        myChambersMap[1] = [];
        myChambersMap[1][0] = new Area(new Point(40, 200), deckWidth, (deckHeigth*2)+50);
        myChambersMap[1][1] = function (point) { OnClickDeck(point); };
        myChambersMap[1][2] = function (point) { };

        myChambersMap[2] = [];
        myChambersMap[2][0] = new Area(new Point(10, 130), 700, 100);
        myChambersMap[2][1] = function (point) { OnClickTransitionElement(point); };
        myChambersMap[2][2] = function (point) { OnMoveTransitionElement(point); };

        myChambersMap[3] = [];
        myChambersMap[3][0] = new Area(new Point(300, 230), cardsWidth*3+100, cardsHeigth*4+100);
        myChambersMap[3][1] = function (point) { OnClickCard(point); };
        myChambersMap[3][2] = function (point) {  };

        //myChambersMap[4] = [];
        //myChambersMap[4][0] = new Area(new Point(0, 0), 900, 100);
        //myChambersMap[4][1] = function (point) { OnClickTopBar(point); };

    }

    function test() {
        allPlayerCards[0] = new Card(0,0,0,0,0,0,canvas);

    }

    function ReDrawCanvas() { }
    function initAllPlayerCards() { }
    function initAllPlayerDecks() { }
    function initCardsInDeck() { }
    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
}