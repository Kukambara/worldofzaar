function EmptyDeck(area) {

    this.area=area;
    this.img = new Image();
    this.img.src = "Images\\MyChambers\\Hollows\\deckHollow.png";


    this.OnClick = function (point) {
        return (area.IsPointInArea(point)) ? buyDeck() : false;
    }

    function buyDeck(){
        alert("You can buy it in future ;)");
    }
}