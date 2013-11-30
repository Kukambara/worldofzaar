function ContentWrapper(id)
{
    var canvas = document.getElementById("сanvas");
    canvas.parentNode.removeChild(canvas);

    var newCanvas = document.createElement('canvas');
    newCanvas.id = "сanvas";
    document.body.appendChild(newCanvas);

    document.getElementById('backgroundCanvas').getContext("2d").clearRect(0, 0 ,900,900);

    switch (id)
    {
        case 0:
            MyChambers();
            break;
        case 1:
            MasterOfDeck();
            break;
        case 2:
            alert("In future");
            MyChambers();
            break;
        case 3:
            Duel();
            break;
        case 4:
            alert("In future");
            MyChambers();
            break;
        default: break;
    }
}