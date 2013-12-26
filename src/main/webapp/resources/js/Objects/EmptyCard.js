function EmptyCard(place) {

    this.img = new Image();
    this.img.src = "/resources/Images/"+place+"/Hollows/cardHollow.png";
    resources.loadByUrl("/resources/Images/"+place+"/Hollows/cardHollow.png");
    this.OnClick = function (point) {
        return false;
    }

}