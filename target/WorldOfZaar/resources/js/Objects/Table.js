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
            chairs[i] = new Chair(i,canvasContext,new Area(coordinateSet[type][i], 45, 37));
            chairs[i].Init();
        }

        tableImg = new Image();
        tableImg.src = "resources\\Images\\Duel\\Sits\\table.png";
    }

    function setCoordinateSet()
    {
        coordinateSet[2]=[];
        coordinateSet[2][0] = new Point(area.beginPoint.x + (area.width / 2) - 22, area.beginPoint.y);
        coordinateSet[2][1] = new Point(area.beginPoint.x+(area.width / 2) - 22, area.beginPoint.y + area.height - 37);

        coordinateSet[3]=[];
        coordinateSet[3][0] = new Point(area.beginPoint.x+(area.width / 2) - 22, area.beginPoint.y);
        coordinateSet[3][1] = new Point(area.beginPoint.x,area.beginPoint.y+area.height-37);
        coordinateSet[3][2] = new Point(area.beginPoint.x+area.width-45,area.beginPoint.y+area.height-37);

        coordinateSet[4]=[];
        coordinateSet[4][0] = new Point(area.beginPoint.x,area.beginPoint.y);
        coordinateSet[4][1] = new Point(area.beginPoint.x+area.width-45,area.beginPoint.y);
        coordinateSet[4][2] = new Point(area.beginPoint.x,area.beginPoint.y+area.height-37);
        coordinateSet[4][3] = new Point(area.beginPoint.x+area.width-45,area.beginPoint.y+area.height-37);
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
                if(chairs[i].OnClick(point));{
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

    this.SetPlayer = function (sit,playerId,playerBlazon){
        this.playersId[sit] = playerId;
        chairs[sit].SetPlayer(playerBlazon);
    }

    this.GetPlayer = function (index){
        if( typeof this.playersId[index] != "undefined" ){
            return this.playersId[index];
        }
        return -1;
    }
}