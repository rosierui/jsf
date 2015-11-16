
function WebSocketConnect(login)
{
    url = ((window.location.protocol == "https:") ? "wss:" : "ws:") + "//" + window.location.host + ctx+"/concurrent";
    if ("WebSocket" in window)
    {
       // Let us open a web socket
         var ws = new WebSocket(url);
        ws.onopen = function ()
        {
            // Web Socket is connected, send data using send()
            ws.send('{"login":"' + login + '", "page":"' + window.location.pathname + window.location.search + '"}');
        };
        //Recieve a message from the server.  This should contain a Json object containing all the users.
        ws.onmessage = function (evt)
        {
            var obj = JSON.parse(evt.data);
            ws.pageFilter(obj);
        };
        ws.onclose = function (close) {
            console.log('[close]', close.code, close.reason);
            if (close.data) {
                var obj = JSON.parse(close.data);
                ws.pageFilter(obj);
            }
          
        };

        ws.onerror = function (error) {
            console.log('[error]', error.message);
        };

        ws.ontimeout = function (evt)
        {
            console.log('[timeout]', evt.message);
            

        };
        ws.pageFilter = function (evt)
        {
            var output = "";
            var totalUsers = "";
            for (var prop in evt) {
                totalUsers = totalUsers + "Name: " + evt[prop].name + " URI: " + evt[prop].url;
                if (window.location.pathname + window.location.search == evt[prop].url) {
                    // output = output +"Key:" + prop + "<BR/>";
                    // output = output +"Value:" + evt[prop].url + "<BR/>";
                    output = output + evt[prop].name + "<BR/>";
                }
            }
            console.log(totalUsers);
            var test = document.getElementById('users');
            test.innerHTML = output;
        }

    }
    else
    {
        // The browser doesn't support WebSocket
        console.log("WebSocket NOT supported by your Browser!");
    }
}