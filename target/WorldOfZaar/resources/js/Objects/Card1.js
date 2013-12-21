function Card(cardId, cardEnergy, cardPicture,cardClassId,isElite,setId,canvas)
{

    //-----------------Характеристики карт---------------------
    this.cardId = cardId;
    this.cardEnergy = cardEnergy;
    this.cardPicture = cardPicture;
    this.cardClassId = cardClassId;
    this.isElite = isElite;
    this.setId = setId;

   //------------------------Переменные характеристики-----------------------
    this.area;
    this.canvas = canvas;
    this.isInDeck = false;

    this.Draw = function (){
        switch(this.isInDeck)
        {
            case true: this.canvas.GetCanvasContextLevel(1).fillStyle = "orange";
                this.canvas.GetCanvasContextLevel(1).fillRect(area.beginPoint.x, area.beginPoint.y, area.width, area.heigth); break;
            case false: this.canvas.GetCanvasContextLevel(1).fillStyle = "orange";
                this.canvas.GetCanvasContextLevel(1).fillRect(this.area.beginPoint.x, this.area.beginPoint.y, this.area.width, this.area.height);
                var a = 0;
                break;
            default: break;
        }
    }

    this.StartPointChange = function (area){
        this.area = area;
    }

    this.SetDeckEntry = function(val){
        this.isInDeck = val;
    }

    this.CardAction = function(){
    }

    this.OnClick = function(point) {
        if (this.area.IsPointInArea(point))
        {
            this.Draw();
            this.CardAction();
        }
    }

}