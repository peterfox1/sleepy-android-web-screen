var exec = require('cordova/exec');

exports.wakeDevice = function (success, error) {
	if (!success) { success = function() {}; }
	if (!error) { error = function() {}; }
    exec(success, error, 'PowerManagerPlugin', 'wakeDevice', []);
};
