function NavigationBar(canvas,id) {
    
    var menuIndex = id;
    var canvas = canvas;
    var lables = [];
    var backgroundImg ;

    function DrawBackground () {
        canvas.GetCanvasContextLevel(0).drawImage(backgroundImg, 0, 0);
    }

    function setBackground(){
        backgroundImg = new Image();
        backgroundImg.src = "\\resources\\Images\\NavigationBar\\Background\\topBackground.png";
        resources.loadByUrl("\\resources\\Images\\NavigationBar\\Background\\topBackground.png");
    }

    this.OnClick = function (point)
    {
        for (var i = 0; i < lables.length; ++i) {
            lables[i][0].OnClick(point);
        }
    }

    this.OnMove = function (point)
    {
        for (var i = 0; i < lables.length; ++i) {
            lables[i][0].OnMove(point);
        }
    }

    function setLables(){
        lables[0] = [];
        lables[0][0] = new TopLable(0, "myChambers", canvas.GetCanvasContextLevel(1), menuIndex == 0, new Area(new Point(5, 10), 166, 55));
        lables[0][1] = "";

        lables[1] = [];
        lables[1][0] = new TopLable(1, "deckMaster", canvas.GetCanvasContextLevel(1), menuIndex == 1, new Area(new Point(180, 10), 238, 63));
        lables[1][1] = "";

        lables[2] = [];
        lables[2][0] = new TopLable(2, "auction", canvas.GetCanvasContextLevel(1), menuIndex == 2, new Area(new Point(425, 10), 138, 68));
        lables[2][1] = "";

        lables[3] = [];
        lables[3][0] = new TopLable(3, "duel", canvas.GetCanvasContextLevel(1), menuIndex == 3, new Area(new Point(570, 10), 154, 63));
        lables[3][1] = "";

        lables[4] = [];
        lables[4][0] = new TopLable(4, "tournament", canvas.GetCanvasContextLevel(1), menuIndex == 4, new Area(new Point(730, 12), 120, 63));
        lables[4][1] = "";

        for (var i = 0; i < lables.length; ++i) {
            lables[i][0].Load();
        }
    }

    this.Draw = function(){
        DrawBackground();
        for (var i = 0; i < lables.length; ++i)
        {
            lables[i][0].Draw();
        }

    }

    this.Init = function () {
        setLables();
        setBackground();
    }
}