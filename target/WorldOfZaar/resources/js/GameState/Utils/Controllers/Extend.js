function extend(Child, Parent) {

	var F = function () { }
	F.prototype = Parent.prototype
	var f = new F();
	for (var prop in Child.prototype) {
		f[prop] = Child.prototype[prop];
	}
	Child.prototype = f;
	Child.prototype.super = Parent.prototype;
	/*Child.prototype = new F()
	Child.prototype.constructor = Child
	Child.superclass = Parent.prototype*/
}
