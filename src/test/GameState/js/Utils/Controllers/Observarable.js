function Observarable() {
	this.listeners = new Array();

	Observarable.prototype.addListener = function (object, evt, callback) {
		if (!this.listeners.hasOwnProperty(evt)) {
			this.listeners[evt] = [];
		}
		this.listeners[evt].push(object[callback]);
	},

	Observarable.prototype.removeListener = function (object, evt, callback) {
		if (this.listeners.hasOwnProperty(evt)) {
			var i, length;
			for (i = 0, length = this.listeners[evt].length; i < length; i += 1) {
				if (this.listeners[evt][i] === object[callback]) {
					this.listeners[evt].splice(i, 1);
				}
			}
		}
	},

	Observarable.prototype.triggerEvent = function (evt, args) {
		if (this.listeners.hasOwnProperty(evt)) {
			var i, length;
			for (i = 0, length = this.listeners[evt].length; i < length; i += 1) {
				this.listeners[evt][i](args);
			}
		}
	}
};