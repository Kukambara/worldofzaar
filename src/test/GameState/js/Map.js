function Map(x, y, width, height) {

	/*this.area = new Area();
	this.area.Init(x, y, width, height);*/
	this.background = new AreaImage(
			new Area(x, y, width, height),
			new Image(width, height));
	this.background.image.src = "Picture/table.jpg";

	this.playerField1 = new PlayerField();
	this.playerField1.InitAreas(x+width/6, y, width, height / 6);
	this.playerField1.InitTexts();
	this.playerField1.SetSourceHightElf();

	this.playerField2 = new PlayerField();
	this.playerField2.rotate = 90;
	this.playerField2.InitAreas(x + 5 * width / 6, y + height / 6, width, height / 6);
	this.playerField2.InitTexts();
	this.playerField2.SetSourceHightElf();

	this.canvas = document.getElementById("canvas");
	if (this.canvas.getContext) {
		this.background.context = this.canvas.getContext("2d");
		this.playerField1.SetContext(this.background.context);
		this.playerField2.SetContext(this.background.context);
		this.background.context.font = "15pt Arial";
		//this.context = this.canvas.getContext("2d");
	} else {
		alert("Sorry, but your browser isn`t support canvas:(");
	}

	this.Draw = function () {
		//this.background.Draw();
		//this.playerField1.Draw();
		this.playerField2.Draw();

	}

	this.Draw();
}