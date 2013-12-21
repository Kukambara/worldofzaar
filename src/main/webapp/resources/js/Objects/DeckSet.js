function DeckSet(){

    var isActivated = false;
    var area;
    var canvasContext;
    var deckCovers= [];
    this.deckId;
    this.deckName;
    this.cardsCount;

    this.Init = function(deckId,deckName,context,inputArea){
        deckCovers[0] = new Image();
        deckCovers[0].src = "/resources/Images/Backgrounds/deckBackground.png";

        deckCovers[1] = new Image();
        deckCovers[1].src = "/resources/Images/MyChambers/Borders/deckActive.png";
        area = inputArea;
        canvasContext = context;
        this.deckId = deckId;
        this.deckName = deckName;
    }

    //Отрисовка деки
    //!!!как будет утвержден вариант отображения - изменить !!!
    this.Draw = function () {

        switch(isActivated){
            case true:
                canvasContext.drawImage(deckCovers[1], area.beginPoint.x-3,area.beginPoint.y-3,area.width+6,area.height+6);
                break;
            case false:
                  break;
        }
        canvasContext.font = "italic 20pt Segoe Script";
        canvasContext.drawImage(deckCovers[0], area.beginPoint.x,area.beginPoint.y,area.width,area.height);
        canvasContext.fillText(this.deckName,area.beginPoint.x+20,(area.beginPoint.y +area.height/3)-10);
        canvasContext.font = "italic 15pt Arial";
        canvasContext.fillText(this.cardsCount+"/30",area.beginPoint.x+60,(area.beginPoint.y +area.height*2/3)+15);
    }

    this.StartPointChange = function(point){
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