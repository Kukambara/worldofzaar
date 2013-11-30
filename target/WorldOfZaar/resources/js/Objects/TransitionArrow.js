function TransitionArrow(isDeckArrow,place,name,contex,area,direction) {

    var name = name;
    var place = place;
    var area = area;

    var direction = direction;
    this.isDeckArrow = isDeckArrow;                        //флаг
    var canvasContext = contex;
    var isPointerIn = false;

    var img ;
    var imgHower ;
    var imgClick;

    this.Draw = function () {
        Draw(0);
    }

    this.Load = function (){
        setImages();
    }

    function setImages()
    {
        img = new Image();
        imgHower = new Image();
        imgClick = new Image();

        img.src = "Images\\"+place+"\\NavigationArrows\\" + name + ".png";
        imgHower.src = "Images\\" + place + "\\NavigationArrows\\" + name + "Hower.png";
        imgClick.src = "Images\\" + place + "\\NavigationArrows\\" + name + "Click.png";
    }

    function Draw(id) {
        switch (id) {
            case 0:                                      //Покой
                if (!isPointerIn) {
                    canvasContext.clearRect(area.beginPoint.x, area.beginPoint.y, area.width, area.height);
                    
                    canvasContext.drawImage(img, area.beginPoint.x, area.beginPoint.y, area.width, area.height);
                }
                    break;
                
            case 1:                                      //Наведено
                if (!isPointerIn) {
                    canvasContext.clearRect(area.beginPoint.x, area.beginPoint.y, area.width, area.height);
                    canvasContext.drawImage(imgHower, area.beginPoint.x, area.beginPoint.y, area.width, area.height);
                }
                    break;
                
            case 2:                                     //Нажато
                canvasContext.clearRect(area.beginPoint.x, area.beginPoint.y, area.width, area.height);
                canvasContext.drawImage(imgClick, area.beginPoint.x, area.beginPoint.y, area.width, area.height);
                setTimeout(function () {
                    canvasContext.clearRect(area.beginPoint.x, area.beginPoint.y, area.width, area.height);
                    canvasContext.drawImage(imgHower, area.beginPoint.x, area.beginPoint.y, area.width, area.height);
                }, 150);
                break;
            default: break;
        }
        return;
    }

    this.OnClick =  function (point) {
        if (area.IsPointInArea(point)) {
            Draw(2);
            return true;
        }
        return false;
    }

    this.OnMove = function (point) {
        if (area.IsPointInArea(point)) {
            Draw(1);
            isPointerIn = true;
            return;
        }
        Draw(0);
        isPointerIn = false;
    }

    this.GetDirection = function () {
        return direction;
    }

}