function DuelArrow(name, contex, area, areaInner, direction) {

    var name = name;
    var place = place;
    var area = area;
    var areaInner = areaInner;
    var isSelected = false;
    var direction = direction;
    var canvasContext = contex;
    var isPointerIn = false;

    var img;
    var imgHower;
    var imgSelect;

    this.Draw = function () {
        Draw(0);
    }

    this.Load = function () {
        setImages();
    }

    function setImages() {
        img = new Image();
        imgHower = new Image();
        imgSelect = new Image();

        img.src = "\\resources\\Images\\Duel\\NavigationArrows\\" + name + ".png";
        imgHower.src = "\\resources\\Images\\Duel\\NavigationArrows\\Hower.png";
        imgSelect.src = "\\resources\\Images\\Duel\\NavigationArrows\\Select.png";
    }

    this.Draw = function (state){
        isSelected = state;
        Draw(0);
    }

    function Draw(id) {
        switch (id) {
            case 0:                                      //Покой
                if (!isPointerIn) {
                    canvasContext.clearRect(area.beginPoint.x, area.beginPoint.y, area.width, area.height);
                    if (isSelected) {
                        canvasContext.drawImage(imgSelect, area.beginPoint.x, area.beginPoint.y, area.width, area.height);
                    }
                    canvasContext.drawImage(img, areaInner.beginPoint.x, areaInner.beginPoint.y, areaInner.width, areaInner.height);
                }
                break;

            case 1:                                      //Наведено
                if (!isPointerIn) {
                    canvasContext.clearRect(area.beginPoint.x, area.beginPoint.y, area.width, area.height);
                    canvasContext.drawImage(imgHower, area.beginPoint.x, area.beginPoint.y, area.width, area.height);
                    canvasContext.drawImage(img, areaInner.beginPoint.x, areaInner.beginPoint.y, areaInner.width, areaInner.height);
                }
                break;

            default: break;
        }
        return;
    }

    this.OnClick = function (point) {
        if (area.IsPointInArea(point) && !isSelected) {
            return true;
        }
        return false;
    }

    this.OnMove = function (point) {
        if (area.IsPointInArea(point) && !isSelected) {
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