function Duel() {

    //-----------------------------Канвас-----------------------
    var canvas;
    //-----------------------------------------------------------------------

    //--------------------------Переменные характеристики--------------------


    var currSelectedGameType = 0;                
    var isWaiting = true;               //флаг: true- покупаем; false - продаем

    var cardsWidth = 85;
    var cardsHeigth = 120;

    //-----------------------------------------------------------------------

    //------------------------Массивы данных--------------------------------
    var player;
    var playerClassName = "";
    var playerRaceName="";
    var currDeckIndex;
    var cardsInDeck = [];

    var allPlayerDecks = [];
    var transitionArrows = [];           //Стрелки навигации
    var loadedDecks = [];

    var duelMap = [];
    var tables = [];
    var navigationBar;
    //-----------------------------------------------------------------------

    //---------------------------Изображения--------------------------------
    var loadedBackground = [];
    var loadedBorders = [];
    var loadedLabels = [];
    var loadedStatic = [];
    var loadedPlayer = [];

    //-----------------------------------------------------------------------

    //--------------------------Входная точка---------------------------------
    function OnLoadCanvas() {
        canvas = new Canvas();
        canvas.Init();

        getUser();
        getUserDecks();
        getDeckCards();

        setDecks();
        setPlayer();
        setTransitionElements();
        setBackground();
        setBorders();
        setDuelMap();
        setNavigationBar();
        //setStaticElements();
        setTables();
        setTimeout(function () {
            Draw(loadedBackground);
            navigationBar.Init();

            setTimeout(function () {
                //DrawStaticElements()
                DrawTransitionElements();
                Draw(loadedBorders);
                Draw(tables);
                Draw(loadedPlayer);
                Draw(loadedDecks);
                setEvents(0);
            }, 200);

        }, 50);
    }

    OnLoadCanvas();
    //-----------------------------------------------------------------------






    //------------------------------События-----------------------------------

    function OnClickWrapper(event) {
        var point = new Point((event.pageX - canvas.CanvasPos_X) * canvas.CanvasScale, (event.pageY - canvas.CanvasPos_Y) * canvas.CanvasScale);
        for (var i = 0; i < duelMap.length; i++) {
            if (duelMap[i][0].IsPointInArea(point)) {
                duelMap[i][1](point);
                break;
            }
        }
    }

    function OnMoveWrapper(event) {
        var point = new Point((event.pageX - canvas.CanvasPos_X) * canvas.CanvasScale, (event.pageY - canvas.CanvasPos_Y) * canvas.CanvasScale);
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
                var index = tables[i].OnClick(point);
                if (index != (-1)) {
                    if(isWaiting){
                        if(tables[i].GetPlayer(index)== player.userId){
                            registrateAbort();
                            break;
                        }
                        break;
                    }
                    registrateRequest(currSelectedGameType,tables[i].GetType(),index);
                    if(!isWaiting){
                        tables[i].SetPlayer(index,player.userId,"");
                    }
                    break;
                }
            }
    }

    function OnClickNavigationBar(point) {
        navigationBar.OnClick(point)
    }
    //-----------------------------------------------------------------------

    //-----------------------------Отрисовка-----------------------------------



    function DrawTransitionElements() {
        for (var i = 0; i < transitionArrows.length; ++i) {
            transitionArrows[i].Draw(currSelectedGameType ==i);
        }
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



    function ReDrawCanvas() { }
    //-----------------------------------------------------------------------

    //-----------------------SetElements------------------------------

    function setTransitionElements() {
        var deckArrowWidth = 177 * canvas.CanvasScale; //4 -----3-------3------4
        var deckArrowHeigth = 57 * canvas.CanvasScale;

        transitionArrows[0] = new DuelArrow("swords", canvas.GetCanvasContextLevel(1), new Area(new Point(285 * canvas.CanvasScale, 125 * canvas.CanvasScale), deckArrowWidth, deckArrowHeigth),
            new Area(new Point(340 * canvas.CanvasScale, 140 * canvas.CanvasScale), 73, 33),0);
        transitionArrows[1] = new DuelArrow("silver", canvas.GetCanvasContextLevel(1), new Area(new Point(464 * canvas.CanvasScale, 125 * canvas.CanvasScale), deckArrowWidth, deckArrowHeigth),
            new Area(new Point(517 * canvas.CanvasScale, 140 * canvas.CanvasScale), 84, 28), 1);
        transitionArrows[2] = new DuelArrow("gold", canvas.GetCanvasContextLevel(1), new Area(new Point(644 * canvas.CanvasScale, 125 * canvas.CanvasScale), deckArrowWidth, deckArrowHeigth),
            new Area(new Point(694 * canvas.CanvasScale, 140 * canvas.CanvasScale), 84, 28), 2);

        for (var i = 0; i < transitionArrows.length; ++i) {
            transitionArrows[i].Load();
        }
    }

    function setBackground() {
        var index = loadedBackground.length;
        loadedBackground[index] = new AreaImage(new Area(new Point(),900,900));
        loadedBackground[index].SetContext(canvas.GetCanvasContextLevel(0));
        loadedBackground[index].SetSource("resources\\Images\\Backgrounds\\duelBackground.png");
    }

    function setDecks() {
        loadedDecks = [];

        var area = new Area(new Point(45, 135), 170, 241);

        loadedDecks[0] = new DeckSet();
        loadedDecks[0].Init(allPlayerDecks[currDeckIndex].deckId,
            allPlayerDecks[currDeckIndex].deckName,canvas.GetCanvasContextLevel(1),area);
        loadedDecks[0].cardsCount =countDeckCards(loadedDecks[0].deckId);

    }

    function setBorders() {
        var index = loadedBorders.length;
        loadedBorders[index] = new AreaImage(new Area(new Point(280, 120), 548, 568));
        loadedBorders[index].SetContext(canvas.GetCanvasContextLevel(0));
        loadedBorders[index].SetSource("resources\\Images\\Duel\\Borders\\deskBorder.png");
    }

    function setStaticElement() {

    }

    function setStaticElements() {
    }

    function setNavigationBar() {
        navigationBar = new NavigationBar(canvas, 3);
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

    function setTables() {
        tables[0] = new Table(2, canvas.GetCanvasContextLevel(1), new Area(new Point(300,300),138,126));
        tables[1] = new Table(3, canvas.GetCanvasContextLevel(1), new Area(new Point(450,300),138,126));
        tables[2] = new Table(4, canvas.GetCanvasContextLevel(1), new Area(new Point(600, 300), 138, 126));

        tables[0].Init();
        tables[1].Init();
        tables[2].Init();
    }

    function setPlayer(){
        loadedPlayer[0] = new AreaImage(new Area(new Point(50,390),157,206));
        loadedPlayer[0].SetContext(canvas.GetCanvasContextLevel(1));
        loadedPlayer[0].SetSource(player.gameProfile.racePicture.picturePath);

        loadedPlayer[1] = new AreaImage(new Area(new Point(50,390),157,206));
        loadedPlayer[1].SetContext(canvas.GetCanvasContextLevel(1));
        loadedPlayer[1].SetSource("resources\\Images\\Duel\\Borders\\avatarBorder.png");



        loadedPlayer[2] = new AreaImage(new Area(new Point(5,600),251,109));
        loadedPlayer[2].SetContext(canvas.GetCanvasContextLevel(1));
        loadedPlayer[2].SetSource("resources\\Images\\Backgrounds\\userNameBackground.png");

        loadedPlayer[3] = new AreaText(new Area(new Point(130,625),0,0),player.userName);
        loadedPlayer[3].SetContext(canvas.GetCanvasContextLevel(1));
        loadedPlayer[3].textColor = "rgb(0,0,0)";
        loadedPlayer[3].fontStyle ='italic 20pt Calibri';

        loadedPlayer[4] = new AreaText(new Area(new Point(130,650),0,0),playerRaceName);
        loadedPlayer[4].SetContext(canvas.GetCanvasContextLevel(1));
        loadedPlayer[4].textColor = "rgb(0,0,0)";
        loadedPlayer[4].fontStyle ='italic 20pt Calibri';

        loadedPlayer[5] = new AreaText(new Area(new Point(130,675),0,0),playerClassName);
        loadedPlayer[5].SetContext(canvas.GetCanvasContextLevel(1));
        loadedPlayer[5].textColor = "rgb(0,0,0)";
        loadedPlayer[5].fontStyle ='italic 20pt Calibri';

        loadedPlayer[6] = new AreaImage(new Area(new Point(20,690),220,220));
        loadedPlayer[6].SetContext(canvas.GetCanvasContextLevel(1));
        loadedPlayer[6].SetSource(player.gameProfile.blazon.cloth.clothPath);

        loadedPlayer[7] = new AreaImage(new Area(new Point(20,690),220,220));
        loadedPlayer[7].SetContext(canvas.GetCanvasContextLevel(1));
        loadedPlayer[7].SetSource(player.gameProfile.blazon.blazonPath);
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

    function registrateRequest(gameType,gameTable,gameSit){
        var cost;
        switch(gameType){
            case 0:
                cost=0;break;
            case 1:
                cost=50;break;
            case 2:
                cost=100;break;
        }
        $.ajax({
            url: "/api/tables/getIn?size="+gameTable+"&position="+gameSit+"&cost="+cost,
            dataType: "json",
            type: "GET",
            async: false,
            success: function (data) {
                isWaiting = !data;
            }
        });
    }
    function registrateAbort(){
        $.ajax({
            url: "/api/tables/getOut",
            dataType: "json",
            type: "GET",
            async: false,
            success: function (data) {
                isWaiting = data;
            }
        });
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
        getRaceName();
        getClassName();
    }
    function getRaceName(){
        var raceId = player.gameProfile.blazon.classification.race.raceId;
        $.ajax({
            url: "/profile/raceName/"+raceId,
            dataType: "json",
            type: "POST",
            async: false,
            success: function (data) {
                playerRaceName = data.raceName;
            }
        });
    }
    function getClassName(){
        var classId = player.gameProfile.blazon.classification.classificationId;
        $.ajax({
            url: "/profile/className/"+classId,
            dataType: "json",
            type: "POST",
            async: false,
            success: function (data) {
                playerClassName = data.className;
            }
        });
    }
    function getUserDecks(){
        $.ajax({
            url: "/profile/userDecks/",
            dataType: "json",
            type: "GET",
            async: false,
            success: function (data) {
                initAllPlayerDecks(data);
            }
        });

        getDeckCards();
    }
    function getDeckCards(){
        for(var i = 0; i < allPlayerDecks.length;++i){
            $.ajax({
                url: "/profile/deckCards/"+allPlayerDecks[i].deckId,
                dataType: "json",
                type: "POST",
                async: false,
                success: function (data) {
                    initCardsInDeck(data);
                }
            });
        }
    }
    function initAllPlayerDecks(data) {
        for(var i = 0; i< data.length;++i){
            allPlayerDecks[i] = data[i];
            if(data[i].isActive){
                currDeckIndex=i;
            }
        }
    }
    function initCardsInDeck(data) {
        cardsInDeck = [];
        for(var i=cardsInDeck.length;i<data.length;++i){
            cardsInDeck[i] = [];
            cardsInDeck[i][0] = data[i];
            cardsInDeck[i][1] = 0;
        }
    }
    function countDeckCards(inputDeckId){
        var counter= 0;
        for(var i = 0; i<cardsInDeck.length;++i){
            if(cardsInDeck[i][0].deckId == inputDeckId){
                counter++;
            }
        }
        return counter;
    }
    //-----------------------------------------------------------------------

}