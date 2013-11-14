/*function Clickable() {
	this.listeners = [];
	this.area;

	Clickable.prototype.Init = function (area) {
		this.area = area;
	}

	Clickable.prototype.onClick = function (eventPoint) {
		if (this.area.IsPointInArea(eventPoint)) {
			this.triggerEvent("onClick", eventPoint);
		}
	}
};
Clickable.prototype = new Observarable();*/
//extend(Clickable, Observarable);

function Clickable() {
	this.area;

	Clickable.prototype.Init = function (area) {
		this.area = area;
	}

	Clickable.prototype.onClick = function (eventPoint) {
		if (this.CheckPoint(eventPoint)) {
			this._OnClick(eventPoint);
		}
	}

	Clickable.prototype.CheckPoint = function (eventPoint) {
		return this.area.IsPointInArea(eventPoint);
	}

	Clickable.prototype._OnClick = function (eventPoint) {
	}
};