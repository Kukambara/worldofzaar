function EmptyHollow(canvasContext,area) {

    this.img ;
    var area = area;
    var canvasContext = canvasContext;

    this.Draw = function () {
        canvasContext.clearRect(area.beginPoint.x, area.beginPoint.y, area.width, area.height);
        canvasContext.drawImage(this.img, area.beginPoint.x, area.beginPoint.y, area.width, area.height);
    }

    this.OnClick = function () {
    }

}