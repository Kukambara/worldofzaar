function ContentWrapper(id)
{
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