function DrawWrapText(context, text, marginLeft, marginTop, maxWidth, lineHeight) {
	var words = text.split(" ");
	var countWords = words.length;
	var line = "";
	for (var n = 0; n < countWords; n++) {
		var testLine = line + words[n] + " ";
		var testWidth = context.measureText(testLine).width;
		if (testWidth > maxWidth) {
			context.fillText(line, marginLeft, marginTop);
			line = words[n] + " ";
			marginTop += lineHeight;
		}
		else {
			line = testLine;
		}
	}
	context.fillText(line, marginLeft, marginTop);
}