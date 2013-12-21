function Chair(id, context, area) {
    this.id = id;
    var area = area;
    var canvasContext = context;
    //var chairs = [];
    var isPointerIn = false;

    var isAvailable = true;
    this.playerBlason;

    var img;
    var imgHower;


    this.Init = function () {
        img = new Image();
        imgHower = new Image();


        img.src = "\\resources\\Images\\Duel\\Sits\\chair.png";
        imgHower.src = "\\resources\\Images\\Duel\\Sits\\chairHower.png";
    }

    this.SetPlayer = function (imgSrc) {
        this.playerBlason = new Image();
        this.playerBlason.src = imgSrc;
        this.isAvailable = false;
        Draw(0);
    }

    this.Draw = function () {
        Draw(0);
    }

    function Draw(id) {
        switch (isPointerIn) {
            case false:                                      //Покой
                canvasContext.clearRect(area.beginPoint.x, area.beginPoint.y, area.width, area.height);
                if (isAvailable) {
                    canvasContext.drawImage(img, area.beginPoint.x, area.beginPoint.y, area.width, area.height);
                }
                else {
                    canvasContext.drawImage(playerBlason, area.beginPoint.x, area.beginPoint.y, area.width, area.height);
                }
                break;
            case true:                                      //Наведено
                canvasContext.clearRect(area.beginPoint.x, area.beginPoint.y, area.width, area.height);
                canvasContext.drawImage(imgHower, area.beginPoint.x, area.beginPoint.y, area.width, area.height);

                break;
            default:
                break;
        }
        return;
    }

    this.OnClick = function (point) {
        if (area.IsPointInArea(point) && isAvailable) {
            return true;
        }
        return false;
    }

    this.OnMove = function (point) {
        if (area.IsPointInArea(point) && isAvailable) {
            isPointerIn = true;
            return;
        }
        isPointerIn = false;
    }
}