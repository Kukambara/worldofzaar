function Table(type,context,area)
{
    var type=type;
    var area=area;
    var canvasContext = context;
    var chairs = [];
    var coordinateSet = [];
    var tableImg;
    this.playersId =[];

    this.Init = function ()
    {
        setCoordinateSet();

        for (var i = 0; i < type; i++)
        {
            chairs[i] = new Chair(i,canvasContext,new Area(coordinateSet[0][i], 45, 37));
            chairs[i].Init();
        }

        tableImg = new Image();
        tableImg.src = "resources\\Images\\Duel\\Sits\\table.png";
    }

    function setCoordinateSet()
    {
        switch(type){
            case 2:
                coordinateSet[0]=[];
                coordinateSet[0][0] = new Point(area.beginPoint.x + (area.width / 2) - 22, area.beginPoint.y);
                coordinateSet[0][1] = new Point(area.beginPoint.x+(area.width / 2) - 22, area.beginPoint.y + area.height - 37);
                break;
            case 3:
                coordinateSet[0]=[];
                coordinateSet[0][0] = new Point(area.beginPoint.x+(area.width / 2) - 22, area.beginPoint.y);
                coordinateSet[0][1] = new Point(area.beginPoint.x,area.beginPoint.y+area.height-37);
                coordinateSet[0][2] = new Point(area.beginPoint.x+area.width-45,area.beginPoint.y+area.height-37);
                break;
            case 4:
                coordinateSet[0]=[];
                coordinateSet[0][0] = new Point(area.beginPoint.x,area.beginPoint.y);
                coordinateSet[0][1] = new Point(area.beginPoint.x+area.width-45,area.beginPoint.y);
                coordinateSet[0][2] = new Point(area.beginPoint.x,area.beginPoint.y+area.height-37);
                coordinateSet[0][3] = new Point(area.beginPoint.x+area.width-45,area.beginPoint.y+area.height-37);
                break;
        }
    }
    this.Draw = function ()
    {
        Draw();
    }

    function Draw()
    {
        canvasContext.clearRect(area.beginPoint.x, area.beginPoint.y, area.width, area.height);
        for (var i = 0; i < type; i++) {
            chairs[i].Draw();
        }
        DrawTable();
    }

    function DrawTable()
    {
        canvasContext.drawImage(tableImg, area.beginPoint.x + 13, area.beginPoint.y + (area.height / 2 - 34), 112, 68);
    }

    this.OnClick = function (point) {
        if (area.IsPointInArea(point)) {
            for (var i = 0; i < type; i++) {
                if(chairs[i].OnClick(point)){
                    return chairs[i].id;
                }
            }
        }
        return -1;
    }

    this.OnMove = function (point) {
            for (var i = 0; i < type; i++) {
                chairs[i].OnMove(point);
            }
            Draw();
    }

    this.GetType = function (){
        return type;
    }

    this.SetPlayer = function (sit,playerId,playerBlazon,playerCloth){
        this.playersId[sit] = playerId;
        chairs[sit].SetPlayer(playerBlazon,playerCloth);
    }

    this.ClearTable = function(){
        this.playersId = [];
        for(var i in chairs){
            chairs[i].DeletePlayer();
        }
    }

    this.GetPlayer = function (index){
        if( typeof this.playersId[index] != "undefined" ){
            return this.playersId[index];
        }
        return -1;
    }
}