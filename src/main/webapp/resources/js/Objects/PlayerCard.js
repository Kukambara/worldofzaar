function PlayerCard()
{

    this.CardAction = function ()
    {
        switch (isInDeck) {
            case false:
                if (confirm("�������� �����")) {
                    isInDeck = !isInDeck;
                    this.Draw();
                }
                break;
            case true:
                if (confirm("������� ����� ?")) {
                    isInDeck = !isInDeck;
                    this.Draw();
                }
                break;
        }
    }
}