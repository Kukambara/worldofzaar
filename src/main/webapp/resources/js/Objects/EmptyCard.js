function EmptyCard(place) {

    this.img = new Image();
    this.img.src = "Images\\"+place+"\\Hollows\\cardHollow.png";

    this.OnClick = function (point) {
        return false;
    }

}