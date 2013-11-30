function PlayerCard()
{

    this.CardAction = function ()
    {
        switch (isInDeck) {
            case false:
                if (confirm("Добавить карту")) {
                    isInDeck = !isInDeck;
                    this.Draw();
                }
                break;
            case true:
                if (confirm("Удалить карту ?")) {
                    isInDeck = !isInDeck;
                    this.Draw();
                }
                break;
        }
    }
}