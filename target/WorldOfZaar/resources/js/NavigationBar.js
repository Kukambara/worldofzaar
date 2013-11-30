function NavigationBar(canvas,id) {
    
    var menuIndex = id;
    var canvas = canvas;
    var lables = [];
    var backgroundImg ;

    function DrawBackground ()
    {
        canvas.CanvasBackgroundContext.drawImage(backgroundImg, 0, 0);
    }

    function setBackground(){
        backgroundImg = new Image();
        backgroundImg.src = "Images\\NavigationBar\\Background\\topBackground.png";
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
        lables[0][0] = new TopLable(0, "myChambers", canvas.CanvasMainContext, menuIndex == 0, new Area(new Point(5, 10), 166, 55));
        lables[0][1] = "";

        lables[1] = [];
        lables[1][0] = new TopLable(1, "deckMaster", canvas.CanvasMainContext, menuIndex == 1, new Area(new Point(180, 10), 238, 63));
        lables[1][1] = "";

        lables[2] = [];
        lables[2][0] = new TopLable(2, "auction", canvas.CanvasMainContext, menuIndex == 2, new Area(new Point(425, 10), 138, 68));
        lables[2][1] = "";

        lables[3] = [];
        lables[3][0] = new TopLable(3, "duel", canvas.CanvasMainContext, menuIndex == 3, new Area(new Point(570, 10), 154, 63));
        lables[3][1] = "";

        lables[4] = [];
        lables[4][0] = new TopLable(4, "tournament", canvas.CanvasMainContext, menuIndex == 4, new Area(new Point(730, 12), 120, 63));
        lables[4][1] = "";

        for (var i = 0; i < lables.length; ++i) {
            lables[i][0].Load();
        }
    }

    function DrawLables()
    {
        for (var i = 0; i < lables.length; ++i)
        {
            lables[i][0].Draw();
        }
    }

    this.Init = function () {
        setLables();
        setBackground();
        
        setTimeout(function () {
            DrawBackground();
            DrawLables();
        }, 20);
    }
}