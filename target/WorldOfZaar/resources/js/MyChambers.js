function MyChambers() {

    //-----------------------------Канвас----------------------------------
    var canvas;

    //--------------------------Переменные характеристики--------------------


    var currCardsRowIndex = 0;                //Индекс строки отрисовки
    var currDeckRowIndex = 0;
    var currDeckIndex = (-1);             //Индекс текущей активной деки. (-1) - никакая не активна 
    var tmpIndexForDeckCards = 0;

    var cardsWidth = 85;
    var cardsHeigth = 120;

    var deckWidth = 170;
    var deckHeigth = 241;

    var isDeckEdit = false;                 //флаг

    //-----------------------------------------------------------------------
    //------------------------Массивы данных--------------------------------

    var cardsInDeck = [];                 //id дек и id карт
    var allPlayerCards = [];              //Все пользовательские карты
    var allPlayerCards1 = [];
    var allPlayerDecks = [];             //Все пользовательские деки

    var transitionArrows = [];           //Стрелки навигации

    var loadedCards = [];
    var loadedCardsUserId = [];

    var loadedDecks = [];
    var loadedBorders = [];
    var loadedLabels = [];
    var loadedBackground = [];
    var loadedActionImg = [];
    var loadedPlayer = [];
    var loadedCardIndex = 0;
    var loadedCardId = 0;

    var myChambersMap = [];
    var navigationBar;
    var player;
    var playerClassName = "";
    var playerRaceName = "";
    //-----------------------------------------------------------------------

    //------------------------Входная точка----------------------------------

    //-----------------------------------------------------------------------
    function OnLoadCanvas() {
        canvas = new Canvas();
        canvas.Init();
        canvas.ReInitCanvasEventLevel();

        canvas.ClearArea(new Area(new Point(0,0),900,900),0);
        canvas.ClearArea(new Area(new Point(0,0),900,900),1);
        canvas.ClearArea(new Area(new Point(0,0),900,900),2);

        query();
        getUser();
        getClassName();
        getRaceName();
        setNavigationBar();
        setTransitionElements();
        SetCards();
        setBackground();
        setBorders();
        setLables();
        setDecks();
        setMyChambersMap();
        setPlayer();
        test();

        setTimeout(function(){
            postInit();
        },2000);

    }

    OnLoadCanvas();
    function test(){
        var cardObject;
        for(var i=0 ;i< allPlayerCards1.length;++i){
            if (allPlayerCards1[i].warriorCard != null) {
                cardObject = allPlayerCards1[i].warriorCard;
            }
            else {
                cardObject = allPlayerCards1[i].supportCard;
            }

            resources.loadByUrl(cardObject.cardPicture);
        }
        var counter=0;
        while(!resources.isReady()){
            counter ++;
            if(counter >50000){break;}
        }
    }
    //-----------------------------------------------------------------------

    //------------------------------События-----------------------------------

    //-----------------------------------------------------------------------

    function OnClickWraper(event) {
        var point = new Point((event.pageX - canvas.CanvasPos_X) * canvas.CanvasScale, (event.pageY - canvas.CanvasPos_Y) * canvas.CanvasScale);
        for (var i = 0; i < myChambersMap.length; i++) {
            if (myChambersMap[i][0].IsPointInArea(point)) {
                myChambersMap[i][1](point);
                break;
            }
        }
    }

    function OnMoveWrapper(event) {
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

    function OnMoveNavigationBar(point) {
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
                        break;
                    case false:
                        currCardsRowIndex += transitionArrows[i].GetDirection();
                        if (currCardsRowIndex < 0) {
                            currCardsRowIndex = 0;
                            break;
                        }

                        SetCards();
                        setTimeout(function () {
                            Draw(loadedCards);
                        }, 150);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    function OnClickDeck(point) {
        if (!isDeckEdit) {
            for (var i = 0; i < loadedDecks.length; ++i) {
                if (loadedDecks[i].OnClick(point)) {
                    if (currDeckIndex != i) {
                        currDeckIndex = i;
                        loadedDecks[i].ActivationSwitch(true);
                        loadedDecks[i].Draw();
                        SetCards();
                        Draw(loadedCards);
                    } else {
                        isDeckEdit = !isDeckEdit;
                        currCardsRowIndex = 0;
                        ReDrawCanvas();
                    }
                }
            }
        }
    }

    function OnClickCardAdd(event) {
        var point = new Point((event.pageX - canvas.CanvasPos_X) * canvas.CanvasScale, (event.pageY - canvas.CanvasPos_Y) * canvas.CanvasScale);

        for (var i = 0; i < loadedActionImg.length; ++i) {
            if (loadedActionImg[i].OnClick(point)) {
                if (loadedActionImg[i].GetDirection()) {
                    AddCard();
                }
                setTimeout(function () {
                    setEvents(0);
                    canvas.GetCanvasContextLevel(2).clearRect(0, 0, 900, 900);
                }, 250);
                break;
            }
        }
    }

    function OnClickCardRemove(event) {
        var point = new Point((event.pageX - canvas.CanvasPos_X) * canvas.CanvasScale, (event.pageY - canvas.CanvasPos_Y) * canvas.CanvasScale);

        for (var i = 0; i < loadedActionImg.length; ++i) {
            if (loadedActionImg[i].OnClick(point)) {
                if (loadedActionImg[i].GetDirection()) {
                    RemoveCard();
                }
                setTimeout(function () {
                    setEvents(0);
                    canvas.GetCanvasContextLevel(2).clearRect(0, 0, 900, 900);
                }, 250);
                break;
            }
        }

    }

    function OnClickNavigationBar(point) {
        navigationBar.OnClick(point)
    }

    function OnClickCardAction(point) {
        for (var i = 0; i < loadedCards.length; ++i) {
            if (loadedCards[i].OnClick(point)) {
                loadedActionImg = [];

                var area = new Area(new Point(250, 100), 400, 600);
                var card = initPlayerCard(allPlayerCards1[(currCardsRowIndex * 3) + i], area, 2);
                loadedCardIndex = (currCardsRowIndex * 3) + i;
                loadedCardId = card.cardId;
                card.Draw();
                card.DrawEnd();
                if (loadedCards[i].GetDeckEntry() || isDeckEdit) {
                    loadedActionImg[0] = new TransitionArrow(false, "MyChambers", "deleteCard", canvas.GetCanvasContextLevel(2), new Area(new Point(235 * canvas.CanvasScale, 740 * canvas.CanvasScale), 432, 67), 1);
                    loadedActionImg[1] = new TransitionArrow(false, "MyChambers", "cancel", canvas.GetCanvasContextLevel(2), new Area(new Point(285 * canvas.CanvasScale, 810 * canvas.CanvasScale), 240, 57), 0);

                    setEvents(-1);
                } else {
                    loadedActionImg[0] = new TransitionArrow(false, "MyChambers", "addCard", canvas.GetCanvasContextLevel(2), new Area(new Point(235 * canvas.CanvasScale, 740 * canvas.CanvasScale), 460, 68), 1);
                    loadedActionImg[1] = new TransitionArrow(false, "MyChambers", "cancel", canvas.GetCanvasContextLevel(2), new Area(new Point(285 * canvas.CanvasScale, 810 * canvas.CanvasScale), 240, 57), 0);
                    setEvents(1);
                }

                loadedActionImg[0].Load();
                loadedActionImg[1].Load();
                break;
            }
        }

    }

    function OnMoveCardAction(event) {
        var point = new Point((event.pageX - canvas.CanvasPos_X) * canvas.CanvasScale, (event.pageY - canvas.CanvasPos_Y) * canvas.CanvasScale);
        for (var i = 0; i < loadedActionImg.length; ++i) {
            loadedActionImg[i].OnMove(point);
        }
    }

    //--------------------------------------------------------------------------

    //-----------------------------Отрисовка-----------------------------------

    //-------------------------------------------------------------------------
    function postInit(){

        Draw(loadedBackground);
        navigationBar.Draw();

        Draw(loadedCards);
        Draw(loadedLabels);
        Draw(loadedBorders);
        Draw(loadedDecks);
        Draw(transitionArrows);
        Draw(loadedPlayer);
        setEvents(0);
    }

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
        canvas.GetCanvasContextLevel(1).clearRect(0, 0, 900, 900);
        canvas.GetCanvasContextLevel(2).clearRect(0, 0, 900, 900);

        setTransitionElements();
        SetCards();
        setBorders();
        setLables();
        setDecks();
        setMyChambersMap();

            setTimeout(function () {
                Draw(loadedCards);

                if (!isDeckEdit) {
                    Draw(loadedDecks);
                }
                Draw(loadedLabels);
                Draw(loadedBorders);
                Draw(transitionArrows);
                if (!isDeckEdit) {
                    Draw(loadedDecks);
                }
            }, 300);
    }

    //-----------------------------------------------------------------------

    //-----------------------SetElements------------------------------------

    //-----------------------------------------------------------------------

    function setTransitionElements() {
        transitionArrows = [];
        switch (isDeckEdit) {
            case true:
                var deckEditArrowWidth = 38 * canvas.CanvasScale;
                var deckEditArrowHeight = 46 * canvas.CanvasScale;

                transitionArrows[0] = new TransitionArrow(false, "MyChambers", "upDeckEdit", canvas.GetCanvasContextLevel(1), new Area(new Point(15 * canvas.CanvasScale, 140 * canvas.CanvasScale), deckEditArrowWidth, deckEditArrowHeight), -1);
                transitionArrows[1] = new TransitionArrow(false, "MyChambers", "downDeckEdit", canvas.GetCanvasContextLevel(1), new Area(new Point(570 * canvas.CanvasScale, 140 * canvas.CanvasScale), deckEditArrowWidth, deckEditArrowHeight), 1);

                break;
            case false:
                var deckArrowWidth = 38 * canvas.CanvasScale;
                var deckArrowHeight = 53 * canvas.CanvasScale;
                var cardsArrowWidth = 38 * canvas.CanvasScale;
                var cardsArrowHeight = 87 * canvas.CanvasScale;

                transitionArrows[0] = new TransitionArrow(true, "MyChambers", "upDeck", canvas.GetCanvasContextLevel(1), new Area(new Point(15 * canvas.CanvasScale, 140 * canvas.CanvasScale), deckArrowWidth, deckArrowHeight), -1);
                transitionArrows[1] = new TransitionArrow(true, "MyChambers", "downDeck", canvas.GetCanvasContextLevel(1), new Area(new Point(205 * canvas.CanvasScale, 140 * canvas.CanvasScale), deckArrowWidth, deckArrowHeight), 1);
                transitionArrows[2] = new TransitionArrow(false, "MyChambers", "upCards", canvas.GetCanvasContextLevel(1), new Area(new Point(270 * canvas.CanvasScale, 140 * canvas.CanvasScale), cardsArrowWidth, cardsArrowHeight), -1);
                transitionArrows[3] = new TransitionArrow(false, "MyChambers", "downCards", canvas.GetCanvasContextLevel(1), new Area(new Point(570 * canvas.CanvasScale, 140 * canvas.CanvasScale), cardsArrowWidth, cardsArrowHeight), 1);
                break;
            default:
                break;
        }

        for (var i = 0; i < transitionArrows.length; ++i) {
            transitionArrows[i].Load();
        }
    }

    function SetCards() {
        switch (isDeckEdit) {
            case true:
                setDeckCards();
                break;
            case false:
                setAllCards();
                break;
        }
    }

    function setAllCards() {
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
                cardsInRow = 6;
                restart_X = 30;
                break;
            default:
                break;
        }

        for (var i = 0; i < 4 * cardsInRow; i++) {

            if (allPlayerCards1[i + (currCardsRowIndex * cardsInRow)]) {
                loadedCards[i] = initPlayerCard(allPlayerCards1[i + (currCardsRowIndex * cardsInRow)],
                    new Area(new Point(curr_x, curr_y), cardsWidth, cardsHeigth), 1);
                loadedCardsUserId[i] = allPlayerCards1[i + (currCardsRowIndex * cardsInRow)].userCardId;
                //loadedCards[i] = allPlayerCards1[i + (currCardsRowIndex * cardsInRow)] ;
                //loadedCards[i].SetDrawableArea(new Area(new Point(curr_x, curr_y), cardsWidth, cardsHeigth));
                //loadedCards[i].SetDeckEntry(cardsEnabler(loadedCards[i].cardId));
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

        if (currDeckIndex != -1 && !isDeckEdit) {
            setDeckCardsEntry();
        }
    }

    function setDeckCardsEntry() {
        for (var i = 0; i < loadedCards.length; ++i) {
            for (var j = 0; j < cardsInDeck.length; ++j) {
                if (cardsInDeck[j][1] == 0) {
                    if (!(loadedCards[i] instanceof  EmptyHollow)) {
                        if ((loadedCards[i].GetCardId() == cardsInDeck[j][0].cardId)) {
                            loadedCards[i].SetDeckEntry(true);
                            cardsInDeck[j][1] = loadedCardsUserId[i];
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    if (!(loadedCards[i] instanceof  EmptyHollow)) {
                        if ((loadedCardsUserId[i] == cardsInDeck[j][1])) {
                            loadedCards[i].SetDeckEntry(true);
                            break;
                        }
                    }
                }
            }
        }
    }

    function setDeckCards() {
        var curr_x;
        var curr_y;
        var cardsInRow;
        var restart_X;
        loadedCards = [];
        var index = 0;

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
                cardsInRow = 6;
                restart_X = 30;
                break;
            default:
                break;
        }

        var counter = 0;

        for (var j = 0; j < allPlayerCards1.length; ++j) {
            if (allPlayerCards1[j]) {
                if (getDeckCardId(getPlayerCardId(allPlayerCards1[j])) != -1) {
                    if (counter < currCardsRowIndex * cardsInRow) {
                        counter++;
                        continue;
                    }

                    loadedCards[index] = initPlayerCard(allPlayerCards1[j],
                        new Area(new Point(curr_x, curr_y), cardsWidth, cardsHeigth), 1);
                    loadedCardsUserId[index] = allPlayerCards1[j].userCardId;

                    if ((index + 1) % cardsInRow != 0) {
                        curr_x += cardsWidth + 10;
                    }
                    else {
                        curr_y += cardsHeigth + 10;
                        curr_x = restart_X;
                    }

                    index++;
                }
            }
            if (index == 24) {
                break;
            }
        }

        for (var j = index; j < 24; ++j) {

            var emptyHollow = new EmptyHollow(canvas.GetCanvasContextLevel(1), new Area(new Point(curr_x, curr_y), cardsWidth, cardsHeigth));
            EmptyCard.prototype = emptyHollow;
            loadedCards[j] = new EmptyCard("MyChambers");

            if ((j + 1) % cardsInRow != 0) {
                curr_x += cardsWidth + 10;
            }
            else {
                curr_y += cardsHeigth + 10;
                curr_x = restart_X;
            }
        }
    }

    function setBackground() {
        loadedBackground[0] = new AreaImage(new Area(new Point(0, 0), 900, 900));
        loadedBackground[0].SetSource("/resources/Images/Backgrounds/balikuruBackground.png");
        loadedBackground[0].SetContext( canvas.GetCanvasContextLevel(0));
    }

    function setLables() {
        loadedLabels = [];
        switch (isDeckEdit) {
            case false:
                loadedLabels[0] = new AreaImage(new Area(new Point(45, 140), 163, 54));
                loadedLabels[1] = new AreaImage(new Area(new Point(300, 140), 270, 93));

                loadedLabels[0].SetSource("\\resources\\Images\\MyChambers\\Lables\\deck.png");
                loadedLabels[1].SetSource("\\resources\\Images\\MyChambers\\Lables\\availableCards.png");

                loadedLabels[0].SetContext(canvas.GetCanvasContextLevel(1));
                loadedLabels[1].SetContext(canvas.GetCanvasContextLevel(1));
                break;
            case true:

                loadedLabels[0] = new AreaImage(new Area(new Point(85, 140), 446, 55));
                loadedLabels[0].SetSource("\\resources\\Images\\MyChambers\\Lables\\deckEdit.png");
                loadedLabels[0].SetContext(canvas.GetCanvasContextLevel(1));
                break;
            default:
                break;
        }


    }

    function setBorders() {
        loadedBorders = [];
        switch (isDeckEdit) {
            case false:

                loadedBorders[0] = new AreaImage(new Area(new Point(9, 120), 243, 652));
                loadedBorders[1] = new AreaImage(new Area(new Point(260, 120), 363, 652));

                loadedBorders[0].SetSource("\\resources\\Images\\MyChambers\\Borders\\cardsBorder.png");
                loadedBorders[1].SetSource("\\resources\\Images\\MyChambers\\Borders\\deckBorder.png");

                loadedBorders[0].SetContext(canvas.GetCanvasContextLevel(1));
                loadedBorders[1].SetContext(canvas.GetCanvasContextLevel(1));

                break;
            case true:
                loadedBorders[0] = new AreaImage(new Area(new Point(9, 120), 610, 652));
                loadedBorders[0].SetSource("\\resources\\Images\\MyChambers\\Borders\\deckEditBorder.png");
                loadedBorders[0].SetContext(canvas.GetCanvasContextLevel(1));
                break;
            default:
                break;
        }
    }

    function setStaticElement() {

    }

    function setDecks() {
        var curr_x = 45;
        var curr_y = 200;
        loadedDecks = [];

        for (var i = 0; i < 2; i++) {
            var area = new Area(new Point(curr_x, curr_y), deckWidth, deckHeigth);
            if (allPlayerDecks[i]) {
                loadedDecks[i] = new DeckSet();
                loadedDecks[i].Init(allPlayerDecks[i + (currDeckRowIndex)].deckId,
                    allPlayerDecks[i + (currDeckRowIndex)].deckName, canvas.GetCanvasContextLevel(1), area);
                loadedDecks[i].cardsCount = countDeckCards(loadedDecks[i].deckId);
            }
            else {
                EmptyDeck.prototype = new EmptyHollow(canvas.GetCanvasContextLevel(1), new Area(new Point(curr_x, curr_y), deckWidth, deckHeigth));
                loadedDecks[i] = new EmptyDeck(area);
            }
            curr_y += deckHeigth + 40;
        }
    }

    function setNavigationBar() {
        navigationBar = new NavigationBar(canvas, 0);
        navigationBar.Init();
    }

    function setMyChambersMap() {
        myChambersMap = [];

        myChambersMap[0] = [];
        myChambersMap[0][0] = new Area(new Point(0, 0), 900, 100);
        myChambersMap[0][1] = function (point) {
            OnClickNavigationBar(point);
        };
        myChambersMap[0][2] = function (point) {
            OnMoveNavigationBar(point);
        };

        if (!isDeckEdit) {
            myChambersMap[1] = [];
            myChambersMap[1][0] = new Area(new Point(40, 200), deckWidth, (deckHeigth * 2) + 50);
            myChambersMap[1][1] = function (point) {
                OnClickDeck(point);
            };
            myChambersMap[1][2] = function (point) {
            };
        }

        myChambersMap[2] = [];
        myChambersMap[2][0] = new Area(new Point(10, 130), 700, 100);
        myChambersMap[2][1] = function (point) {
            OnClickTransitionElement(point);
        };
        myChambersMap[2][2] = function (point) {
            OnMoveTransitionElement(point);
        };

        if (!isDeckEdit) {
            myChambersMap[3] = [];
            myChambersMap[3][0] = new Area(new Point(300, 230), cardsWidth * 3 + 100, cardsHeigth * 4 + 100);
            myChambersMap[3][1] = function (point) {
                OnClickCardAction(point);
            };
            myChambersMap[3][2] = function (point) {
            };
        } else {
            myChambersMap[1] = [];
            myChambersMap[1][0] = new Area(new Point(40, 200), cardsWidth * 6 + 100, cardsHeigth * 4 + 100);
            myChambersMap[1][1] = function (point) {
                OnClickCardAction(point);
            };
            myChambersMap[1][2] = function (point) {
            };
        }

    }

    function setEvents(id) {
        canvas.ReInitCanvasEventLevel();
        switch (id) {
            case 0:
                canvas.GetCanvasLevel(3).addEventListener("click", OnClickWraper);
                canvas.GetCanvasLevel(3).addEventListener("mousemove", OnMoveWrapper);
                break;
            case 1:
                canvas.GetCanvasLevel(3).addEventListener("click", OnClickCardAdd);
                canvas.GetCanvasLevel(3).addEventListener("mousemove", OnMoveCardAction);
                break;
            case -1:
                canvas.GetCanvasLevel(3).addEventListener("click", OnClickCardRemove);
                canvas.GetCanvasLevel(3).addEventListener("mousemove", OnMoveCardAction);
                break;
        }
    }

    function AddCard() {
        $.ajax({
            url: "/profile/cards/add/" + allPlayerDecks[currDeckIndex].deckId + "/" + allPlayerCards1[loadedCardIndex].userCardId,
            dataType: "json",
            type: "POST",
            async: false,
            success: function () {
            }
        });
        getDeckCards();
        ReDrawCanvas();
    }

    function RemoveCard() {
        $.ajax({
            url: "/profile/cards/remove/" + getDeckCardId(loadedCardId),
            dataType: "json",
            type: "POST",
            async: false,
            success: function () {
            }
        });
        getDeckCards();
        ReDrawCanvas();
    }

    function setPlayer() {
        loadedPlayer[0] = new AreaImage(new Area(new Point(680, 190), 157, 206));
        loadedPlayer[0].SetContext(canvas.GetCanvasContextLevel(1));
        loadedPlayer[0].SetSource(player.gameProfile.racePicture.picturePath);

        loadedPlayer[1] = new AreaImage(new Area(new Point(680, 190), 157, 206));
        loadedPlayer[1].SetContext(canvas.GetCanvasContextLevel(1));
        loadedPlayer[1].SetSource("resources\\Images\\Duel\\Borders\\avatarBorder.png");


        loadedPlayer[2] = new AreaImage(new Area(new Point(635, 400), 251, 109));
        loadedPlayer[2].SetContext(canvas.GetCanvasContextLevel(1));
        loadedPlayer[2].SetSource("resources\\Images\\Backgrounds\\userNameBackground.png");

        loadedPlayer[3] = new AreaText(new Area(new Point(760, 425), 0, 0), player.userName);
        loadedPlayer[3].SetContext(canvas.GetCanvasContextLevel(1));
        loadedPlayer[3].textColor = "rgb(0,0,0)";
        loadedPlayer[3].fontStyle = 'italic 20pt Calibri';

        loadedPlayer[4] = new AreaText(new Area(new Point(760, 450), 0, 0), playerRaceName);
        loadedPlayer[4].SetContext(canvas.GetCanvasContextLevel(1));
        loadedPlayer[4].textColor = "rgb(0,0,0)";
        loadedPlayer[4].fontStyle = 'italic 20pt Calibri';

        loadedPlayer[5] = new AreaText(new Area(new Point(760, 475), 0, 0), playerClassName);
        loadedPlayer[5].SetContext(canvas.GetCanvasContextLevel(1));
        loadedPlayer[5].textColor = "rgb(0,0,0)";
        loadedPlayer[5].fontStyle = 'italic 20pt Calibri';

        loadedPlayer[6] = new AreaImage(new Area(new Point(650, 490), 220, 220));
        loadedPlayer[6].SetContext(canvas.GetCanvasContextLevel(1));
        loadedPlayer[6].SetSource(player.gameProfile.blazon.cloth.clothPath);

        loadedPlayer[7] = new AreaImage(new Area(new Point(650, 490), 220, 220));
        loadedPlayer[7].SetContext(canvas.GetCanvasContextLevel(1));
        loadedPlayer[7].SetSource(player.gameProfile.blazon.blazonPath);

        loadedPlayer[8] = new AreaText(new Area(new Point(700, 770), 50, 50),player.gameProfile.money);
        loadedPlayer[8].SetContext(canvas.GetCanvasContextLevel(1));
        loadedPlayer[8].textColor = "rgb(255,185,15)";
        loadedPlayer[8].fontStyle ='italic 20pt Calibri';

        loadedPlayer[9] = new AreaText(new Area(new Point(820, 770), 50, 50),player.gameProfile.donateMoney);
        loadedPlayer[9].SetContext(canvas.GetCanvasContextLevel(1));
        loadedPlayer[9].textColor = "rgb(192,192,192)";
        loadedPlayer[9].fontStyle ='italic 20pt Calibri';

        loadedPlayer[10] = new AreaImage(new Area(new Point(650, 770), 50, 50));
        loadedPlayer[10].SetContext(canvas.GetCanvasContextLevel(1));
        loadedPlayer[10].SetSource("/resources/Images/MasterOfDeck/Lables/goldImg.png");

        loadedPlayer[11] = new AreaImage(new Area(new Point(770, 770), 50, 50));
        loadedPlayer[11].SetContext(canvas.GetCanvasContextLevel(1));
        loadedPlayer[11].SetSource("/resources/Images/MasterOfDeck/Lables/silverImg.png");

        loadedPlayer[12] = new AreaImage(new Area(new Point(740, 710), 50, 50));
        loadedPlayer[12].SetContext(canvas.GetCanvasContextLevel(1));
        loadedPlayer[12].SetSource("/resources/Images/MasterOfDeck/Lables/moneyBag.png");
    }

    //---------------------------------------------------------------------

    function getDeckCardId(cardId) {
        for (var i = 0; i < cardsInDeck.length; ++i) {
            if (cardsInDeck[i][0].cardId == cardId && cardsInDeck[i][0].deckId == allPlayerDecks[currDeckIndex].deckId) {
                return cardsInDeck[i][0].cardInDeckId;
            }
        }
        return -1;
    }

    function query() {
        //allPlayerCards[0] = new Card(0,0,0,0,0,0,canvas);
        getUserCards();
        getUserDecks();
        getDeckCards();
    }

    function getUser() {
        $.ajax({
            url: "/profile/gameProfile/",
            dataType: "json",
            type: "GET",
            async: false,
            success: function (data) {
                player = data;
            }
        });
        getRaceName();
        getClassName();
    }
    function getRaceName() {
        var raceId = player.gameProfile.blazon.classification.race.raceId;
        $.ajax({
            url: "/profile/raceName/" + raceId,
            dataType: "json",
            type: "POST",
            async: false,
            success: function (data) {
                playerRaceName = data.raceName;
            }
        });
    }

    function getClassName() {
        var classId = player.gameProfile.blazon.classification.classificationId;
        $.ajax({
            url: "/profile/className/" + classId,
            dataType: "json",
            type: "POST",
            async: false,
            success: function (data) {
                playerClassName = data.className;
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
                allPlayerCards1 = data;
            }
        });
    }

    function getUserDecks() {
        $.ajax({
            url: "/profile/userDecks/",
            dataType: "json",
            type: "GET",
            async: false,
            success: function (data) {
                initAllPlayerDecks(data);
            }
        });
    }

    function getDeckCards() {
        for (var i = 0; i < allPlayerDecks.length; ++i) {
            $.ajax({
                url: "/profile/deckCards/" + allPlayerDecks[i].deckId,
                dataType: "json",
                type: "POST",
                async: false,
                success: function (data) {
                    initCardsInDeck(data);
                }
            });
        }
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

    function getPlayerCardId(data) {
        if (data.warriorCard != null) {
            return  data.warriorCard.cardId;
        }
        return data.supportCard.cardId;
    }

    function initAllPlayerDecks(data) {
        for (var i = 0; i < data.length; ++i) {
            allPlayerDecks[i] = data[i];
        }
    }

    function initCardsInDeck(data) {
        cardsInDeck = [];
        for (var i = cardsInDeck.length; i < data.length; ++i) {
            cardsInDeck[i] = [];
            cardsInDeck[i][0] = data[i];
            cardsInDeck[i][1] = 0;
        }
    }

    function countDeckCards(inputDeckId) {
        var counter = 0;
        for (var i = 0; i < cardsInDeck.length; ++i) {
            if (cardsInDeck[i][0].deckId == inputDeckId) {
                counter++;
            }
        }
        return counter;
    }

    //-----------------------------------------------------------------------

    //-----------------------------------------------------------------------
}