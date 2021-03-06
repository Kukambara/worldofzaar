﻿function Canvas()
{

    var CanvasStore = [];

    this.CanvasPos_X;
    this.CanvasPos_Y;
    this.CanvasWidth;
    this.CanvasHeigth;
    this.CanvasScale;

    this.Init = function() {
        CanvasStore[0] =  [];
        CanvasStore[0][0] =  document.getElementById("canvasLevel_0");
        CanvasStore[0][1] = CanvasStore[0][0].getContext("2d");

        CanvasStore[1] = [];
        CanvasStore[1][0] = document.getElementById("canvasLevel_1");
        CanvasStore[1][1] = CanvasStore[1][0].getContext("2d");

        CanvasStore[2] = [];
        CanvasStore[2][0] = document.getElementById("canvasLevel_2");
        CanvasStore[2][1] = CanvasStore[1][0].getContext("2d");

        CanvasStore[3] = [];
        CanvasStore[3][0] = document.getElementById("canvasLevel_event");
        CanvasStore[3][1] = CanvasStore[1][0].getContext("2d");

        this.CanvasPos_X = CanvasStore[1][0].offsetLeft;
        this.CanvasPos_Y = CanvasStore[1][0].offsetTop;
        this.CanvasWidth = CanvasStore[1][0].width = 900;
        this.CanvasHeigth = CanvasStore[1][0].height = 900;
        this.CanvasScale = this.CanvasWidth / 900;

    }

    this.ClearArea = function (area,canvasLevel){
        CanvasStore[canvasLevel][1].clearRect(area.beginPoint.x,area.beginPoint.y,area.width,area.height);
    }


    this.GetCanvasContextLevel = function (inputLevel){
        if(CanvasStore.length >inputLevel){
            return CanvasStore[inputLevel][0].getContext("2d");
        }
        return -1;
    }

    this.GetCanvasLevel = function (inputLevel){
        if(CanvasStore.length >inputLevel){
            return CanvasStore[inputLevel][0];
        }
        return -1;
    }

    this.ReInitCanvasEventLevel = function(){
        var canvas = document.getElementById("canvasLevel_event");
        canvas.parentNode.removeChild(canvas);

        var newCanvas = document.createElement('canvas');
        newCanvas.width = newCanvas.height = 900;
        newCanvas.style.position = "absolute";
        newCanvas.id = "canvasLevel_event";
        document.getElementById("placeholder_event").appendChild(newCanvas);

        CanvasStore[3] = [];
        CanvasStore[3][0] = document.getElementById("canvasLevel_event");
        CanvasStore[3][1] = CanvasStore[1][0].getContext("2d");
    }
}