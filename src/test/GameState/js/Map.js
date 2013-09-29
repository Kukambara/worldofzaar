function Map(x, y, width, height) {

	var fieldHeightSize = 1 / 3;
	var fieldWidthSize = 2 / 3;
	var fieldStepX = (1 - fieldWidthSize)/2;
	var fieldStepY = 1 - fieldHeightSize;


	/*this.area = new Area();
	this.area.Init(x, y, width, height);*/
	this.background = new AreaImage(
			new Area(x, y, width, height),
			new Image(width, height));
	this.background.image.src = "Picture/table.jpg";

	this.playerField1 = new PlayerField();
	this.playerField1.InitAreas(x + width * fieldStepX, y + height * fieldStepY, width * fieldWidthSize, height * fieldHeightSize);
	this.playerField1.InitTexts();
	this.playerField1.SetSourceHightElf();

	this.playerField2 = new PlayerField();
	this.playerField2.rotate = 90;
	//this.playerField2.InitAreas(x + width * fieldStepX, y + height * fieldStepY, width * fieldWidthSize, height * fieldHeightSize);
	this.playerField2.InitAreas(x + width * (1 - fieldStepY), y + height * fieldStepX, width * fieldWidthSize, height * fieldHeightSize);
	this.playerField2.InitTexts();
	this.playerField2.SetSourceHightElf();

	this.playerField3 = new PlayerField();
	this.playerField3.rotate = -180;
	this.playerField3.InitAreas(x + width * (1 - fieldStepX), y + height * (1 - fieldStepY), width * fieldWidthSize, height * fieldHeightSize);
	this.playerField3.InitTexts();
	this.playerField3.SetSourceHightElf();

	this.playerField4 = new PlayerField();
	this.playerField4.rotate = -90;
	this.playerField4.InitAreas(x + width * fieldStepY, y + height * (1 - fieldStepX), width * fieldWidthSize, height * fieldHeightSize);
	this.playerField4.InitTexts();
	this.playerField4.SetSourceHightElf();
	
	this.canvas = document.getElementById("canvas");
	if (this.canvas.getContext) {
		this.background.context = this.canvas.getContext("2d");
		this.playerField1.SetContext(this.background.context);
		this.playerField2.SetContext(this.background.context);
		this.playerField3.SetContext(this.background.context);
		this.playerField4.SetContext(this.background.context);
		this.background.context.font = "15pt Arial";
		//this.context = this.canvas.getContext("2d");
	} else {
		alert("Sorry, but your browser isn`t support canvas:(");
	}

	this.Draw = function () {
		//this.background.Draw();
		this.playerField1.Draw();
		this.playerField2.Draw();
		this.playerField3.Draw();
		this.playerField4.Draw();

	}

	this.Draw();
}