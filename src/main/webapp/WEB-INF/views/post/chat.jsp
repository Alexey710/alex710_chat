<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Chat room</title>
         <link href="/webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
    <script>
    var stompClient = null;

    function setConnected(connected) {
        $("#connect").prop("disabled", connected);
        $("#disconnect").prop("disabled", !connected);
        if (connected) {
            $("#conversation").show();
        }
        else {
            $("#conversation").hide();
        }
        $("#greetings").html("");
    }

    function connect() {
        console.log("start connect()");
        var socket = new SockJS('/gs-guide-websocket');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            //setConnected(true);
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/greetings', function (greeting) {
                showGreeting(JSON.parse(greeting.body));
            });
        });
    }

    function disconnect() {
         console.log("start disconnect()");
        if (stompClient !== null) {
            stompClient.disconnect();
        }
        //setConnected(false);
        console.log("Disconnected"); 
    }

    function sendName() {
        console.log("start sendName()");
   
        stompClient.send("/app/chat", {}, 
        JSON.stringify({'id': "${id}", 'content': $("#message").val()}));
    }

    function showGreeting(message) {
        console.log("showGreeting => " + message);
        $("#greetings").append("<tr><td>" + '<span style="color:'+ message.senderColor + '; font-weight:600;font-size: 150%">' + message.sender + ":" + '</span>' +  message.content + "</td></tr>");
    }

    $(function () {
        $("form").on('submit', function (e) {
            e.preventDefault();
        });
        //$( "#connect" ).click(function() { connect(); });
        //$( "#disconnect" ).click(function() { disconnect(); });
        $("#send").click(function() { sendName(); scrollDown(); flush(); 
            document.getElementById('message').value='';});
    });
    
    function flush() {
        console.log("start flush()");
        let query = JSON.stringify({'id': "${id}"});
        $.ajax({
            type: "POST",
            url: 'http://localhost:8080/cache',
            data: query,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data) {
                console.log("success");

                console.log(data);
            },
            error: function(errMsg) {
                console.log(errMsg);
            }
        });
    }
    
    function scrollDown() {
            console.log("start scrollDown")
            var conversation = document.getElementById("conversation");
            conversation.scrollTop = conversation.scrollHeight;
        }
    window.onbeforeunload = disconnect();  
  
    </script>
    
        <style>
            .scroll {
                height: 400px;
                width: 700px;
                background: #fff;
                border: 3px solid #C1C1C1; 
                overflow-y: scroll; 
                border-radius: 10px
            }
            
        </style>
   </head>
    
<body onload="connect(); scrollDown();">

<span style="color: blueviolet; font-weight:600;font-size: 250%"><c:out value="Чат: ${chatName}"/></span>

<br>
<div id="conversation" class="scroll">
     <div class="row">
        <div class="col-md-12">
            <table id="conversation" class="table table-striped">
                <thead>
               
                </thead>
                <tbody id="greetings">
                    <c:forEach items="${chat}" var="chat_m">
                        <tr>
                            <td>
                                <span style="color: ${chat_m.user.colorCSS}; font-weight:600;font-size: 150%"><c:out value="${chat_m.user.username}:"/></span>
                                <c:out value="${chat_m.content}"/>   
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>    

<form>
        <br>
        <textarea id="message" type="text" style="border-radius: 30px;border: 3px solid #C1C1C1;
                  width: 400px;height: 100px;resize: none" name="message">
        </textarea>
        <br><br>
      
        <div><input id="send" style="border-radius: 10px;
                  width: 160px;height: 40px;background: blueviolet;color: white" 
                  name="submit" type="submit" value="Отправить сообщение" />
        </div>      
</form>
</body>
</html>