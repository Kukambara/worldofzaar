function MyChambers() {

    //-----------------------------Канвас----------------------------------

    var canvas;
    var canvasContext;

    var canvasPos_X;
    var canvasPos_Y;
    var canvasWidth;
    var canvasHeigth;
    var canvasScale;
    //-----------------------------------------------------------------------
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
    var myChambersMap = [];
    var navigationBar;
    //-----------------------------------------------------------------------

    //---------------------------Изображения--------------------------------
    var backgroundImg;
    var deckLabelImg;
    var availableCardsImg;
    var deckBorderImg ;
    var cardBorderImg;
    //-----------------------------------------------------------------------




    //------------------------Входная точка----------------------------------
    function OnLoadCanvas() {

        setCanvas();
        setNavigationBar();
        setTransitionElements();
        setAllCards();
        setBackground();
        setBorders();
        setLables();
        setDecks();
        setMyChambersMap();

        setTimeout(function () {
            DrawBackground();
            DrawNavigationBar();
            setTimeout(function () {
                DrawAllCards();
                DrawLables();
                DrawBorders();
                DrawDecks();
                DrawTransitionElements();
                canvas.addEventListener("click", OnClickWraper);
                canvas.addEventListener("mousemove", OnMoveWrapper);
            }, 200);

        }, 50);
    }

    OnLoadCanvas();

    //-----------------------------------------------------------------------






    //------------------------------События-----------------------------------

    function OnClickWraper(event) {
        var point = new Point((event.pageX - canvasPos_X) * canvasScale, (event.pageY - canvasPos_Y) * canvasScale);
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
        var point = new Point((event.pageX - canvasPos_X) * canvasScale, (event.pageY - canvasPos_Y) * canvasScale);
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

    function OnMoveNavigationBar(point)
    {
        navigationBar.OnMove(point);
    }

    function OnClickTransitionElement(point) {

        for (var i = 0; i < transitionArrows.length; ++i) {
            var state = transitionArrows[i].OnClick(point);
            if (state) {
                if (currDeckRowIndex + transitionArrows[i].GetDirection() >= 0 && i < 2) {
                    currDeckRowIndex += transitionArrows[i].GetDirection();
                    DrawDecks();
                }
                else {
                    if (currCardsRowIndex + transitionArrows[i].GetDirection() >= 0 && (i >= 2 || isDeckEdit)) {

                        currCardsRowIndex += transitionArrows[i].GetDirection();
                        DrawAllCards();
                    }
                }
            }

        }
    }

    function OnClickDeck(event) {
        //Обработка клика, выбор деки, и подгрузка ее в CardsOverviewMap
        //Клик на активную деку ничего не делает (пока что)
        //
        //if (!isDeckEdit) {
        //    for (var i = 0; i < PlayerSets.length; ++i) {
        //        if (PlayerSets[i].OnClick(event.x, event.y)) {
        //            if (currDeckIndex != i) {
        //                if (currDeckIndex != -1) {
        //                    PlayerSets[currDeckIndex].ActivationSwitch(false);
        //                }
        //                PlayerSets[i].ActivationSwitch(true);
        //                currDeckIndex = i;
        //                isDeckEdit = !isDeckEdit;
        //                var ctx = canvas.getContext("2d");
        //                ctx.clearRect(0, 0, canvas.width, canvas.height);
        //                LoadDeckCards(currPage);
        //                TopBarDraw();
        //            }
        //        }
        //    }
        //}

        DrawAllCards();
    }

    function OnClickCard(point) {
        for (var i = 0; i < loadedCards.length; ++i) {
            if (loadedCards[i].OnClick(point)) {
                loadedCards[i].CardAction();
                DrawAllCards();
            }
        }

    }

    function OnClickNavigationBar(point) {
        navigationBar.OnClick(point)
    }



    //--------------------------------------------------------------------------

    //-----------------------------Отрисовка-----------------------------------

    function DrawDecks() {
        for (var i = 0 ; i < loadedDecks.length ; i++) {
            loadedDecks[i].Draw();
        }
    }

    function DrawBackground() {
        document.getElementById('backgroundCanvas').getContext("2d").drawImage(backgroundImg, 0, 0);
    }

    function DrawBorders() {
        switch (isDeckEdit) {
            case false:
                    canvasContext.drawImage(deckBorderImg, 9, 120, 243, 652);
                    canvasContext.drawImage(cardBorderImg, 260, 120, 363, 652);
                break;
            case true:
                canvasContext.drawImage(deckBorderImg, 9, 120, 610, 652);
                break;
            default:
                break;
        }

    }

    function DrawLables() {

        canvasContext.clearRect(45, 140, 163, 54);
        canvasContext.clearRect(300, 140, 270, 93);

        canvasContext.drawImage(deckLabelImg, 45, 140, 163, 54);
        canvasContext.drawImage(availableCardsImg, 300, 140, 270, 93);
    }

    function DrawAllCards() {

        canvasContext.clearRect(300, 230, cardsWidth * 3 + 40, cardsHeigth * 4 + 35);

        for (var i = 0 ; i < loadedCards.length ; i++) {
                    loadedCards[i].Draw();
            }
    }

    function DrawTransitionElements(){
        for (var i = 0; i < transitionArrows.length; ++i){
            transitionArrows[i].Draw();
        }
    }

    function DrawNavigationBar(){
        navigationBar.Load();
    }

    function DrawDeckCards() {
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

    function DrawFriend() {
    }

    function DrawPlayer() {

    }


    //-----------------------------------------------------------------------




    //-----------------------Переключатели-----------------------------

    //Активирует карту, входящую в деку. Вызывается из DrawAllCards
    function CardEnabler(cardId) {
        for (var i = 0 ; i < cardsInDeck[currDeckIndex].length; ++i) {
            if (cardsInDeck[currDeckIndex][i] == cardId) {
                return true;
            }
        }
        return false;
    }


    //------------------------------------------------------------------




    //-----------------------SetElements------------------------------

    function setTransitionElements() {

        switch (isDeckEdit) {
            case true:
                var deckEditArrowWidth = 38 * canvasScale;
                var deckEditArrowHeigth = 46 * canvasScale;

                transitionArrows[0] = new TransitionArrow( false,"MyChambers", "upDeckEdit", canvas.getContext("2d"), new Area(new Point( 15 * canvasScale, 140 * canvasScale), deckEditArrowWidth, deckEditArrowHeigth), -1);
                transitionArrows[1] = new TransitionArrow(false, "MyChambers", "downDeckEdit", canvas.getContext("2d"), new Area(new Point(570 * canvasScale, 140 * canvasScale), deckEditArrowWidth, deckEditArrowHeigth), 1);

                break;
            case false:
                var deckArrowWidth = 38 * canvasScale;
                var deckArrowHeigth = 53 * canvasScale;
                var cardsArrowWidth = 38 * canvasScale;
                var cardsArrowHeigth = 87 * canvasScale;

                transitionArrows[0] = new TransitionArrow(true, "MyChambers", "upDeck", canvas.getContext("2d"), new Area(new Point(15 * canvasScale, 140 * canvasScale), deckArrowWidth, deckArrowHeigth), -1);
                transitionArrows[1] = new TransitionArrow(true, "MyChambers", "downDeck", canvas.getContext("2d"), new Area(new Point(205 * canvasScale, 140 * canvasScale), deckArrowWidth, deckArrowHeigth), 1);
                transitionArrows[2] = new TransitionArrow(false, "MyChambers", "upCards", canvas.getContext("2d"), new Area(new Point(270 * canvasScale, 140 * canvasScale), cardsArrowWidth, cardsArrowHeigth), -1);
                transitionArrows[3] = new TransitionArrow(false, "MyChambers", "downCards", canvas.getContext("2d"), new Area(new Point(570 * canvasScale, 140 * canvasScale), cardsArrowWidth, cardsArrowHeigth), 1);
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
            if (allPlayerCards[i]) {
                loadedCards[i] = allPlayerCards[i + (currCardsRowIndex * cardsInRow)]
                loadedCards[i].StartPointChange(new Point(curr_x, curr_y));
                loadedCards[i].SetDeckEntry(cardsEnabler(loadedCards[i].cardId));
            }
            else {
                var emptyHollow = new EmptyHollow(canvasContext, new Area(new Point(curr_x, curr_y), cardsWidth, cardsHeigth));
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

    function setBackground() {
        backgroundImg = new Image();
        backgroundImg.src = "Images\\Backgrounds\\balikuruBackground.png";
    }

    function setLables() {

        deckLabelImg = new Image();
        availableCardsImg = new Image();

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
    }

    function setBorders() {
        deckBorderImg = new Image();
        cardBorderImg = new Image();

        switch (isDeckEdit) {
            case false:
                deckBorderImg.src = "Images\\MyChambers\\Borders\\cardsBorder.png";
                cardBorderImg.src = "Images\\MyChambers\\Borders\\deckBorder.png";
                break;
            case true:
                deckBorderImg.src = "Images\\MyChambers\\Borders\\deckEditBorder.png";
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
                EmptyDeck.prototype = new EmptyHollow(canvasContext,new Area(new Point(curr_x, curr_y), deckWidth, deckHeigth));

                loadedDecks[i] = new EmptyDeck();
            }
            curr_y += deckHeigth + 40;
        }
    }

    function setNavigationBar() {
        navigationBar = new NavigationBar(canvasContext, 0);
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

    function setCanvas() {
        canvas = document.getElementById("сanvas");
        canvasContext = canvas.getContext("2d");

        canvasPos_X = canvas.offsetLeft;
        canvasPos_Y = canvas.offsetTop;
        canvasWidth = canvas.width = 900;
        canvasHeigth = canvas.height = 900;
        canvasScale = canvasWidth / 900;
        canvas.style.position = "absolute";
    }

    function ReDrawCanvas() { }
    function initAllPlayerCards() { }
    function initAllPlayerDecks() { }
    function initCardsInDeck() { }
    //-----------------------------------------------------------------------

}