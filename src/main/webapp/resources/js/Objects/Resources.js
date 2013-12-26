(function () {
	var resourceCache = [];
	var loading = [];
	var readyCallbacks = [];

	// Load an image url or an array of image urls
	function loadByArr(urls) {
		for (var i in urls) {
			loadByUrl(urls[i])
		}
	}

	function loadByUrl(url) {
		if (resourceCache[url]) {
			return resourceCache[url];
		}
		else {
			var img = new Image();
			img.onload = function () {
				resourceCache[url] = img;

				if (isReady()) {
					for (var i in readyCallbacks) {
						readyCallbacks[i]();
					}
					//readyCallbacks.forEach(function (func) { func(); });
				}
			};
			resourceCache[url] = false;
			img.src = url;
		}
	}

	function get(url) {
		return resourceCache[url];
	}

	function isReady() {
		for (var i in resourceCache) {
			if (resourceCache.hasOwnProperty(i) &&
               !resourceCache[i]) {
				return false;
			}
		}
		return true;
	}

	function onReady(func) {
        read
		readyCallbacks[0] = func;
	}

	window.resources = {
		loadByUrl: loadByUrl,
		loadByArr:loadByArr,
		get: get,
		onReady: onReady,
		isReady: isReady
	};
})();