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
	
	
		function setUrl(url) {
			$('.webScreenIframe').attr('src', url);
		}
		function reload() {
			$('.webScreenIframe').attr('src', function(i, val) { return val; });
		}
		
		
		const push = PushNotification.init({
			android: {
			},
		});
		
		push.on('registration', (data) => {
			// data.registrationId
			
			var postData = {
				pushId: data.registrationId
			};
			
			$.ajax({
				url: 'https://86ea3e81.ngrok.io/_register',
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
		
		push.on('notification', (payload) => {
			//payload.additionalData
			
			var data = {
				'_action': 'wake',	// wake|url
				'_actionData': {},
			};
			$.extend(data, payload.additionalData);
			
			var action = data._action;
			var actionData = data._actionData;
			
			if (action == 'url') {
				
				if (
					typeof(actionData.url) === 'string'
					&&
					actionData.url.indexOf('http') === 0
				) {
					setUrl(actionData.url);
				}
				else {
					reload();
				}
				
			}
			
			// data.message,
			// data.title,
			// data.count,
			// data.sound,
			// data.image,
			// data.additionalData
			//alert('push:notificationy ' + JSON.stringify(data));
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