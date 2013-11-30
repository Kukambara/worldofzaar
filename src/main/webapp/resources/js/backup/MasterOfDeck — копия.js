function MasterOfDeck() {

    //-----------------------------Канвас-----------------------

    var canvas ;
    var canvasContext ;

    var canvasPos_X ;
    var canvasPos_Y;
    var canvasWidth;
    var canvasHeigth ;
    var canvasScale;
    //-----------------------------------------------------------------------



    //--------------------------Переменные характеристики--------------------


    var currCardsRowIndex = 0;                //Индекс строки отрисовки
    var  isBuyingCards= true;               //флаг: true- покупаем; false - продаем

    var cardsWidth = 85;
    var cardsHeigth = 120;
    //-----------------------------------------------------------------------



    //------------------------Массивы данных--------------------------------

    var allMasterOfDeckCards = [];              //Все пользовательские карты
    var allPlayerCards = [];
    var transitionArrows = [];           //Стрелки навигации
    var loadedCards = [];

    var masterOfDeckMap = [];
    var navigationBar;
    //-----------------------------------------------------------------------



    //---------------------------Изображения--------------------------------
    var backgroundImg;
    var deckLabelImg;
    var buySellImg;
    var cardsBorderImg;
    var buySellCardsImg;
    var master;
    //-----------------------------------------------------------------------


    //--------------------------Входная точка---------------------------------
    function OnLoadCanvas() {

        setCanvas();
        setTransitionElements();
        setAllCards(allMasterOfDeckCards);
        setBackground();
        setBorders();
        setLables();
        setMasterOfDeckMap();
        setNavigationBar();
        setStaticElements();

        setTimeout(function () {
            DrawBackground();
            DrawNavigationBar();
            setTimeout(function () {
                DrawAllCards();
                DrawBorders();
                DrawLables();
                DrawStaticElements()
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
        for (var i = 0; i < masterOfDeckMap.length; i++) {
            if (masterOfDeckMap[i][0].IsPointInArea(point)) {
                masterOfDeckMap[i][1](point);
                break;
            }
        }
    }

    function OnMoveWrapper(event) {
        var point = new Point((event.pageX - canvasPos_X) * canvasScale, (event.pageY - canvasPos_Y) * canvasScale);
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
    //-----------------------------------------------------------------------




    //-----------------------------Отрисовка-----------------------------------


    function DrawBackground() {
        document.getElementById('backgroundCanvas').getContext("2d").drawImage(backgroundImg, 0, 0);
    }

    function DrawBorders() {
        canvasContext.drawImage(cardsBorderImg, 280, 120, 608, 650);
    }

    function DrawLables() {
        canvasContext.clearRect(400, 145, 376, 45);
        canvasContext.drawImage(availableCardsImg, 400, 145, 376, 45);
    }

    function DrawAllCards() {

        canvasContext.clearRect(400, 230, cardsWidth * 5 + 40, cardsHeigth * 4 + 35);

        for (var i = 0 ; i < loadedCards.length ; i++) {
            loadedCards[i].Draw();
        }
    }

    function DrawTransitionElements() {
        for (var i = 0; i < transitionArrows.length; ++i) {
            transitionArrows[i].Draw();
        }
    }

    function DrawNavigationBar() {
        navigationBar.Load();
    }

    function DrawStaticElements()
    {
        canvasContext.clearRect(10, 150, 262, 331);
        canvasContext.drawImage(master, 10, 150, 262, 331);
    }

    function DrawDeckCards() {

    }

    function DrawFriend() {
    }

    function DrawPlayer() {

    }

    function ReDrawCanvas() { }
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
    //-----------------------------------------------------------------------



    //-----------------------SetElements------------------------------

    function setTransitionElements() {
        var deckArrowWidth = 38 * canvasScale;
        var deckArrowHeigth = 53 * canvasScale;

        transitionArrows[0] = new TransitionArrow(false,"MasterOfDeck", "upCards", canvas.getContext("2d"), new Area(new Point(302 * canvasScale, 145 * canvasScale), deckArrowWidth, deckArrowHeigth), -1);
        transitionArrows[1] = new TransitionArrow(false,"MasterOfDeck","downCards", canvas.getContext("2d"), new Area(new Point(825 * canvasScale, 145 * canvasScale), deckArrowWidth, deckArrowHeigth), 1);
        transitionArrows[2] = new TransitionArrow(false, "MasterOfDeck", "sellCards", canvas.getContext("2d"), new Area(new Point(60 * canvasScale, 630 * canvasScale), 163, 87), 1);

        for (var i = 0; i < transitionArrows.length; ++i) {
            transitionArrows[i].Load();
        }
    }

    function setAllCards(source) {
        var curr_x = 340;
        var curr_y = 230;
        var cardsInRow = 5;
        var restart_X = 340;
        loadedCards = [];


        for (var i = 0  ; i < 20 ; i++) {
            if (source[i]) {
                loadedCards[i] = source[i + (currCardsRowIndex * cardsInRow)]
                loadedCards[i].StartPointChange(new Point(curr_x, curr_y));
                loadedCards[i].SetDeckEntry(cardsEnabler(loadedCards[i].cardId));
            }
            else {
                var emptyHollow = new EmptyHollow(canvasContext, new Area(new Point(curr_x, curr_y), cardsWidth, cardsHeigth));
                EmptyCard.prototype = emptyHollow;

                loadedCards[i] = new EmptyCard("MasterOfDeck");
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
        backgroundImg.src = "Images\\Backgrounds\\masterOfDeckBackground.png";
    }

    function setLables() {

        availableCardsImg = new Image();

        switch (isBuyingCards) {
            case false:
                availableCardsImg.src = "Images\\MasterOfDeck\\Lables\\sellCardsLable.png";
                break;
            case true:
                availableCardsImg.src = "Images\\MasterOfDeck\\Lables\\buyCardsLable.png";
                break;
            default:
                break;
        }
    }

    function setBorders() {
        cardsBorderImg = new Image();
        cardsBorderImg.src = "Images\\MasterOfDeck\\Borders\\cardsBorder.png";
    }

    function setStaticElement() {

    }

    function setStaticElements() {
        master = new Image();

        master.src = "Images\\MasterOfDeck\\Hollows\\master.png";
    }

    function setNavigationBar() {
        navigationBar = new NavigationBar(canvasContext, 1);
    }

    function setMasterOfDeckMap() {
        masterOfDeckMap[0] = [];
        masterOfDeckMap[0][0] = new Area(new Point(0, 0), 900, 100);
        masterOfDeckMap[0][1] = function (point) { OnClickNavigationBar(point); };
        masterOfDeckMap[0][2] = function (point) { OnMoveNavigationBar(point); };

        //masterOfDeckMap[1] = [];
        //masterOfDeckMap[1][0] = new Area(new Point(40, 200), deckWidth, (deckHeigth * 2) + 50);
        //masterOfDeckMap[1][1] = function (point) { OnClickDeck(point); };
        //masterOfDeckMap[1][2] = function (point) { };

        masterOfDeckMap[1] = [];
        masterOfDeckMap[1][0] = new Area(new Point(300, 130), 600, 100);
        masterOfDeckMap[1][1] = function (point) { OnClickTransitionElement(point); };
        masterOfDeckMap[1][2] = function (point) { OnMoveTransitionElement(point); };

        masterOfDeckMap[2] = [];
        masterOfDeckMap[2][0] = new Area(new Point(300, 230), cardsWidth * 3 + 100, cardsHeigth * 4 + 100);
        masterOfDeckMap[2][1] = function (point) { OnClickCard(point); };
        masterOfDeckMap[2][2] = function (point) { return;};

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

    function initAllPlayerCards() { }
    function initAllPlayerDecks() { }
    function initCardsInDeck() { }
    //-----------------------------------------------------------------------

}