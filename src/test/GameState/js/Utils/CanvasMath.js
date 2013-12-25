(function () {

	function calculateScaleArea(/*Area*/ area, /*int*/ scale) {
		return new Area(area.beginPoint, area.width * scale, area.height * scale);
	}

	function calculateScaleArea(/*Point*/ point, /*int*/ scale) {
		return new Point(point.x * scale, point.y * scale);
	}

	function calculateTransposeArea(/*Area*/ area, /*int*/ angle) {
		
	}
})();