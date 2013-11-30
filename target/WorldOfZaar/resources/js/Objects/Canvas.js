function Canvas()
{
    this.CanvasMain;
    this.CanvasMainContext;

    this.CanvasBackground;
    this.CanvasBackgroundContext;

    this.CanvasPos_X;
    this.CanvasPos_Y;
    this.CanvasWidth;
    this.CanvasHeigth;
    this.CanvasScale;

    this.Init = function() {
        this.CanvasMain = document.getElementById("сanvas");
        this.CanvasMainContext = this.CanvasMain.getContext("2d");

        this.CanvasBackground = document.getElementById("backgroundCanvas");
        this.CanvasBackgroundContext = this.CanvasBackground.getContext("2d");

        this.CanvasMain.style.position = "absolute";

        this.CanvasPos_X = this.CanvasMain.offsetLeft;
        this.CanvasPos_Y = this.CanvasMain.offsetTop;
        this.CanvasWidth = this.CanvasMain.width = 900;
        this.CanvasHeigth = this.CanvasMain.height = 900;
        this.CanvasScale = this.CanvasWidth / 900;
        
    }

    this.ClearArea = function ()
    {

    }
}