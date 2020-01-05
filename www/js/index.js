/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
var app = {
    // Application Constructor
    initialize: function() {
        document.addEventListener('deviceready', this.onDeviceReady.bind(this), false);
    },

    // deviceready Event Handler
    //
    // Bind any cordova events here. Common events are:
    // 'pause', 'resume', etc.
    onDeviceReady: function() {
        this.receivedEvent('deviceready');
        
        
        // alert('wakeIn 10s');
        // setTimeout(function () {
		// 	cordova.plugins.powerManager.wakeDevice()
		// }, 10000);
	
		
		
		
		const push = PushNotification.init({
			android: {
			},
		});
		
		push.on('registration', (data) => {
			// data.registrationId
			
			var postData = {
				registrationId: data.registrationId
			};
			
			$.ajax({
				url: 'https://88393ca4.ngrok.io/register',
				method: 'POST',
				data: postData,
				success: function () {
					//alert('ajax:success');
				},
				error: function (jqXHR, textStatus, errorThrown) {
					//alert('ajax:error: ' + textStatus + errorThrown);
				},
			});
			//alert('push:registrationqq ' + JSON.stringify(data));
		});
		
		push.on('notification', (data) => {
			// data.message,
			// data.title,
			// data.count,
			// data.sound,
			// data.image,
			// data.additionalData
			alert('push:notification ' + JSON.stringify(data));
			
			cordova.plugins.powerManager.wakeDevice();
		});
		
		push.on('error', (e) => {
			// e.message
			alert('push:error ' + JSON.stringify(e));
		});
		
    },

    // Update DOM on a Received Event
    receivedEvent: function(id) {
        var parentElement = document.getElementById(id);
        var listeningElement = parentElement.querySelector('.listening');
        var receivedElement = parentElement.querySelector('.received');

        listeningElement.setAttribute('style', 'display:none;');
        receivedElement.setAttribute('style', 'display:block;');

        console.log('Received Event: ' + id);
    }
};

app.initialize();