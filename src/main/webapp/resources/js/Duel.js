function Duel() {

    //-----------------------------Канвас-----------------------

    var canvas;
    var canvasContext;

    var canvasPos_X;
    var canvasPos_Y;
    var canvasWidth;
    var canvasHeigth;
    var canvasScale;
    //-----------------------------------------------------------------------



    //--------------------------Переменные характеристики--------------------


    var currSelectedGameType = 0;                
    var isBuyingCards = true;               //флаг: true- покупаем; false - продаем

    var cardsWidth = 85;
    var cardsHeigth = 120;
    //-----------------------------------------------------------------------



    //------------------------Массивы данных--------------------------------

    var allMasterOfDeckCards = [];              //Все пользовательские карты
    var allPlayerCards = [];
    var transitionArrows = [];           //Стрелки навигации
    var loadedCards = [];

    var duelMap = [];
    var tables = [];
    var navigationBar;
    //-----------------------------------------------------------------------



    //---------------------------Изображения--------------------------------
    var backgroundImg;
    var deskLabelImg;
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
        //setLables();
        setDuelMap();
        setNavigationBar();
        //setStaticElements();
        setTables();
        setTimeout(function () {
            DrawBackground();
            DrawNavigationBar();
            setTimeout(function () {
                //DrawAllCards();
                
                //DrawLables();
                //DrawStaticElements()
                DrawTransitionElements();
                DrawBorders();
                DrawTables();
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
        for (var i = 0; i < duelMap.length; i++) {
            if (duelMap[i][0].IsPointInArea(point)) {
                duelMap[i][1](point);
                break;
            }
        }
    }

    function OnMoveWrapper(event) {
        var point = new Point((event.pageX - canvasPos_X) * canvasScale, (event.pageY - canvasPos_Y) * canvasScale);
        for (var i = 0; i < duelMap.length; i++) {
            duelMap[i][2](point);
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

    function OnMoveTables(point) {
        for (var i = 0; i < tables.length; ++i) {
            tables[i].OnMove(point)
        }

    }

    function OnClickTransitionElement(point) {

        for (var i = 0; i < transitionArrows.length; ++i) {
            var state = transitionArrows[i].OnClick(point);
            if (state) {
                    currSelectedGameType = transitionArrows[i].GetDirection();
                    DrawTransitionElements();
            }

        }
    }

    function OnClickTables(point) {
        for (var i = 0; i < tables.length; ++i) {
            if (tables[i].OnClick(point)) {
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
        canvasContext.drawImage(deskBorderImg, 280, 120, 548, 568);
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
            transitionArrows[i].Draw(currSelectedGameType ==i);
        }
    }

    function DrawNavigationBar() {
        navigationBar.Load();
    }

    function DrawStaticElements() {
        canvasContext.clearRect(10, 150, 262, 331);
        canvasContext.drawImage(master, 10, 150, 262, 331);
    }

    function DrawDeckCards() {

    }

    function DrawFriend() {
    }

    function DrawPlayer() {

    }

    function DrawTables()
    {
        for (var i = 0; i < tables.length; ++i) {
            tables[i].Draw();
        }
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
        var deckArrowWidth = 177 * canvasScale; //4 -----3-------3------4
        var deckArrowHeigth = 57 * canvasScale;

        transitionArrows[0] = new DuelArrow("swords", canvas.getContext("2d"), new Area(new Point(285 * canvasScale, 125 * canvasScale), deckArrowWidth, deckArrowHeigth),
            new Area(new Point(340 * canvasScale, 140 * canvasScale), 73, 33),0);
        transitionArrows[1] = new DuelArrow("silver", canvas.getContext("2d"), new Area(new Point(464 * canvasScale, 125 * canvasScale), deckArrowWidth, deckArrowHeigth),
            new Area(new Point(517 * canvasScale, 140 * canvasScale), 84, 28), 1);
        transitionArrows[2] = new DuelArrow("gold", canvas.getContext("2d"), new Area(new Point(644 * canvasScale, 125 * canvasScale), deckArrowWidth, deckArrowHeigth),
            new Area(new Point(694 * canvasScale, 140 * canvasScale), 84, 28), 2);

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
        backgroundImg.src = "Images\\Backgrounds\\duelBackground.png";
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
        deskBorderImg = new Image();
        deskBorderImg.src = "Images\\Duel\\Borders\\deskBorder.png";
    }

    function setStaticElement() {

    }

    function setStaticElements() {
        master = new Image();

        master.src = "Images\\MasterOfDeck\\Hollows\\master.png";
    }

    function setNavigationBar() {
        navigationBar = new NavigationBar(canvasContext, 3);
    }

    function setDuelMap() {
        duelMap[0] = [];
        duelMap[0][0] = new Area(new Point(0, 0), 900, 100);
        duelMap[0][1] = function (point) { OnClickNavigationBar(point); };
        duelMap[0][2] = function (point) { OnMoveNavigationBar(point); };

        //masterOfDeckMap[1] = [];
        //masterOfDeckMap[1][0] = new Area(new Point(40, 200), deckWidth, (deckHeigth * 2) + 50);
        //masterOfDeckMap[1][1] = function (point) { OnClickDeck(point); };
        //masterOfDeckMap[1][2] = function (point) { };

        duelMap[1] = [];
        duelMap[1][0] = new Area(new Point(300, 130), 600, 100);
        duelMap[1][1] = function (point) { OnClickTransitionElement(point); };
        duelMap[1][2] = function (point) { OnMoveTransitionElement(point); };

        duelMap[2] = [];
        duelMap[2][0] = new Area(new Point(300, 300), cardsWidth * 3 + 100, cardsHeigth * 4 + 100);
        duelMap[2][1] = function (point) { OnClickTables(point); };
        duelMap[2][2] = function (point) { OnMoveTables(point); };

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

    function setTables() {
        tables[0] = new Table(2, canvasContext, new Area(new Point(300,300),138,126));
        tables[1] = new Table(3, canvasContext, new Area(new Point(450,300),138,126));
        tables[2] = new Table(4, canvasContext, new Area(new Point(600, 300), 138, 126));

        tables[0].Init();
        tables[1].Init();
        tables[2].Init();
    }


    function initAllPlayerCards() { }
    function initAllPlayerDecks() { }
    function initCardsInDeck() { }
    //-----------------------------------------------------------------------

}