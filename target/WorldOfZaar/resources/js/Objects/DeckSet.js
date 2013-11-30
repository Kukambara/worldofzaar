function DeckSet(deckId,context,area){


    var isActivated = false;
    var area = area;
    var canvasContext = context;
    var deckId = deckId;
    var cardsId = [];

    //Отрисовка деки
    //!!!как будет утвержден вариант отображения - изменить !!!
    this.Draw = function () {

        var b_context = canvas.getContext("2d");
        switch(isActivated){
            case true:  b_context.fillStyle="orange";
                b_context.fillRect(start_x, start_y,width, heigth); break;
            case false: b_context.fillStyle="black";
                b_context.drawImage(img,start_x, start_y, width, heigth); break;
        }
    }

    this.StartPointChange = function(point)
    {
        area.beginPoint = point;
    }

    this.OnClick = function(point){
         return area.IsPointInArea(point)
    }

    //Изменение статуса активной деки с перерисовкой
    this.ActivationSwitch =  function(parametr){
       isActivated = parametr;
       this.Draw();
    }

    this.setCardsId = function (cardsId){

    }

    //Не до конца обдуман
    this.CardInDeck = function(cardId)
    {
        for(var i=0; i<cardsSet.length;++i)
        {
            if(cardsSet[i][0]==cardId && cardsSet[i][1]<=3 )
            return true;
        }
        return false;
    }
}