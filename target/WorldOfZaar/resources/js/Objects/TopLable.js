function TopLable(id,lableName,context,isSelected,area)
{
    var id = id;
    var lableName = lableName;
    var area = area;
    var isSelected = isSelected;
    var canvasContext = context;
    var isPointerIn = false;

    var img ;
    var imgHower ;

    function setImages(){
        img = new Image();
        imgHower = new Image();

        if (isSelected) {
            img.src = "Images\\NavigationBar\\Lables\\" + lableName + "Select.png";
        }
        else {
            img.src = "Images\\NavigationBar\\Lables\\" + lableName + ".png";
        }
        imgHower.src = "Images\\NavigationBar\\Lables\\" + lableName + "Hower.png";
    }

    this.Load = function (){
        setImages();
    }

    this.Draw = function () {
        Draw(0);
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
            default: break;
        }
        return;
    }

    this.OnClick = function (point) {
        if (area.IsPointInArea(point)) {

            ContentWrapper(id);
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

}