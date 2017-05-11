<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.tong.javabean.User"%>
<!DOCTYPE html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>vue</title>
    <style>
        *,
        *:before,
        *:after {
            box-sizing: border-box;
        }
        
        body,
        html {
            height: 100%;
            overflow: hidden;
        }
        
        body,
        ul {
            margin: 0;
            padding: 0;
        }
        
        body {
            color: #4d4d4d;
            font: 14px/1.4em 'Helvetica Neue', Helvetica, 'Microsoft Yahei', Arial, sans-serif;
            background-size: cover;
            font-smoothing: antialiased;
        }
        
        ul {
            list-style: none;
        }
        
        #chat {
            /*<!-- margin: 20px auto; -->
            <!-- width: 800px; -->
            <!-- height: 600px; -->*/
            height: 100%;
        }
    </style>
    <style type="text/css">
        #chat {
            overflow: hidden;
            border-radius: 3px
        }
        
        #chat .main,
        #chat .sidebar {
            height: 100%
        }
        
        #chat .sidebar {
            float: left;
            width: 200px;
            color: #f4f4f4;
            background-color: #2e3238
        }
        
        #chat .main {
            position: relative;
            overflow: hidden;
            background-color: #eee
        }
        
        #chat .m-text {
            position: absolute;
            width: 100%;
            bottom: 0;
            left: 0
        }
        
        #chat .m-message {
            height: calc(100% - 10pc)
        }
    </style>
    <style type="text/css">
        .m-card {
            padding: 9pt;
            border-bottom: 1px solid #24272c
        }
        
        .m-card footer {
            margin-top: 10px
        }
        
        .m-card .avatar,
        .m-card .name {
            vertical-align: middle
        }
        
        .m-card .avatar {
            border-radius: 2px
        }
        
        .m-card .name {
            display: inline-block;
            margin: 0 0 0 15px;
            font-size: 1pc
        }
        
        .m-card .search {
            padding: 0 10px;
            width: 100%;
            font-size: 9pt;
            color: #fff;
            height: 30px;
            line-height: 30px;
            border: 1px solid #3a3a3a;
            border-radius: 4px;
            outline: 0;
            background-color: #26292e
        }
    </style>
    <style type="text/css">
        .m-list li {
            padding: 9pt 15px;
            border-bottom: 1px solid #292c33;
            cursor: pointer;
            -webkit-transition: background-color .1s;
            transition: background-color .1s
        }
        
        .m-list li:hover {
            background-color: hsla(0, 0%, 100%, .03)
        }
        
        .m-list li.active {
            background-color: hsla(0, 0%, 100%, .1)
        }
        
        .m-list .avatar,
        .m-list .name {
            vertical-align: middle
        }
        
        .m-list .avatar {
            border-radius: 2px
        }
        
        .m-list .name {
            display: inline-block;
            margin: 0 0 0 15px
        }
    </style>
    <style type="text/css">
        .m-text {
            height: 10pc;
            border-top: 1px solid #ddd
        }
        
        .m-text textarea {
            padding: 10px;
            height: 100%;
            width: 100%;
            border: none;
            outline: 0;
            font-family: Micrsofot Yahei;
            resize: none
        }
    </style>
    <style type="text/css">
        .m-message {
            padding: 10px 15px;
            overflow-y: scroll
        }
        
        .m-message li {
            margin-bottom: 15px
        }
        
        .m-message .time {
            margin: 7px 0;
            text-align: center
        }
        
        .m-message .time>span {
            display: inline-block;
            padding: 0 18px;
            font-size: 9pt;
            color: #fff;
            border-radius: 2px;
            background-color: #dcdcdc
        }
        
        .m-message .avatar {
            float: left;
            margin: 0 10px 0 0;
            border-radius: 3px
        }
        
        .m-message .text {
            display: inline-block;
            position: relative;
            padding: 0 10px;
            max-width: calc(100% - 40px);
            min-height: 30px;
            line-height: 2.5;
            font-size: 9pt;
            text-align: left;
            word-break: break-all;
            background-color: #fafafa;
            border-radius: 4px
        }
        
        .m-message .text:before {
            content: " ";
            position: absolute;
            top: 9px;
            right: 100%;
            border: 6px solid transparent;
            border-right-color: #fafafa
        }
        
        .m-message .self {
            text-align: right
        }
        
        .m-message .self .avatar {
            float: right;
            margin: 0 0 0 10px
        }
        
        .m-message .self .text {
            background-color: #b2e281
        }
        
        .m-message .self .text:before {
            right: inherit;
            left: 100%;
            border-right-color: transparent;
            border-left-color: #b2e281
        }
    </style>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>

<body>

    <div id="chat" class="row">
        <div id="list" class="sidebar hidden-xs">
            <div class="m-card">
                <header>
                    <p class="name">{{userName}}</p>
                </header>

<!--                 <button onclick="testonlie()" type="button" class="btn btn-primary">测试用户上线</button> -->
<!--                 <button onclick="testofflie()" type="button" class="btn btn-primary">测试用户下线</button> -->
<!--                 <button onclick="testchetp()" type="button" class="btn btn-primary">测试用户群聊</button> -->
<!--                 <button onclick="testchets()" type="button" class="btn btn-primary">测试用户私聊</button> -->
            </div>
            <!--v-component-->
            <div class="m-list">
                <ul>
                    <!--v-for-start-->
                    <template v-for="site in userList">
                    <li @click="select(site)"><p class="name">{{ site.name }}</p></li>
                    </template>
                    <!--v-for-end-->
                </ul>
            </div>
            <!--v-component-->
        </div>
        <div class="main">
            <div id="message-box">
                <div style="padding: 10px; background-color: #2E3238; color: white;">
                    <span class="glyphicon glyphicon-menu-hamburger" style="padding-left: 15px;"></span> <span style="text-align: center; display: inline-block; width: 80%">{{toName}}</span>
                </div>
                <div id="m-message" class="m-message" style="overflow-y: auto;height:680px;">
                    <ul id="message-list" style="padding: 10px 15px;">
                        <!--v-for-start-->
                        <template v-for="site in sessionMessage.messages">
                        <li>
                            <p class="time">
                                <span>{{site.date}}</span>
                            </p>
                            <div v-bind:class="{ self: mestae(site.fromId) }">
                                <span style="font-size: 10px;">{{site.fromName}}</span>
                            </div>
                            <div v-bind:class="{ self: mestae(site.fromId) }">
                                <div class="text">{{site.text}}</div>
                            </div>
                            <!--<div v-bind:class="{ self: site.state }">
                                <span style="font-size: 10px;">{{site.name}}</span>
                            </div>
                            <div v-bind:class="{ self: site.state }">
                                <div class="text">{{site.text}}</div>
                            </div>-->
                        </li>
                       
                        </template>
                        <!--v-for-end-->
                    </ul>
                </div>
            </div>
            <!--v-component-->
            <div id="text" class="m-text" style="background-color: white;">
                <textarea v-model="message" @keyup="inputing" placeholder="按 Ctrl + Enter 发送" style="padding: 10px 25px; height: 70%"></textarea>
                <button v-on:click="sendMessage" id="sendButton" type="button" class="btn btn-primary" style="float: right; margin-right: 25px">发送</button>
            </div>
            <!--v-component-->
        </div>
    </div>
    <!--<%
        User M2 = (User) session.getAttribute("data");
        M2.toString();

        //User user = M2;
    %>-->
    <script src="js/vue.min.js"></script>
    <script>
        "use strict";
        let now = new Date();
        let data = {
            // 登录用户
              user: {
                  userId: <%out.print(M2.getId());%>,
                  name: '<%out.print(M2.getUsername());%>',
                  email: '<%out.print(M2.getEmail());%>',
                  sessionId: '<%out.print(session.getId());%>',
                  webSocketId: null,
              },

//             user: {
//                 userId: 2,
//                 name: 'leilonggei',
//                 email: 'tongyunjung@java.com',
//                 sessionId: '123456789',
//                 webSocketId: null,
//             },

            // 用户列表
            userList: [{
                userId: 0,
                name: '大厅',
                email: 'daiting@java.com'
            }, {
                userId: 1,
                name: '测试用户',
                email: 'chakshiyungwu@java.com'
            }],

            // 会话列表
            sessionList: [{
                userId: 0,
                messages: [{
                    fromId: 1,
                    toId: 0,
                    fromName: '测试用户',
                    toName: '大厅',
                    text: 'Hello，这是一个基于Vue构建的简单chat示例。',
                    date: now,
                    type: 1,
                }, {
                    fromId: 1,
                    toId: 0,
                    fromName: '测试用户',
                    toName: '大厅',
                    text: '测试',
                    date: now,
                    type: 1,
                }]
            }, {
                userId: 1,
                messages: [],
            }]
        };
        let list = new Vue({
            el: '#list',
            data: {
                userName: data.user.name,
                userId: data.user.userId,
                userList: data.userList,
            },
            methods: {
                select: function(item) {
                    console.log(item.userId);
                    for (let i = 0; i < this.userList.length; i++) {
                        console.log("i= " + i + " userId= " + this.userList[i].userId);
                        if (data.sessionList[i] != null && item.userId == data.sessionList[i].userId) {

                            messageBox.sessionMessage = data.sessionList[i];
                            text.sessionId = i;
                            text.toId = item.userId;
                            text.toName = item.name;
                            messageBox.toName = text.toName;
                            console.log("item.name=" + item.name + " item.userId= " + item.userId);
                            console.log("text.toName=" + text.toName + " text.toId= " + text.toId);
                            console.log("messageBox.toName=" + messageBox.toName);
                            return;
                        }
                    }
                    console.log(item.userId);
                    let newSession = {
                        userId: item.userId,
                        messages: [],
                    };

                    data.sessionList.push(newSession);
                    messageBox.sessionMessage = data.sessionList[this.userList.length - 1];
                    text.sessionId = this.userList.length - 1;
                    // console.log("i= " + i);


                    // console.log(text.sessionId);
                }
            }
        })
        let text = new Vue({
            el: '#text',
            data: {
                fromId: list.userId,
                fromName: list.userName,
                toId: 0,
                toName: '大厅',
                message: 'Runoob!',
                sessionId: 0,
                sessionMessage: data.sessionList[this.sessionId],
            },
            methods: {
                sendMessage: function(event) {
                    if (this.message.length) {
                        console.log(this.message);
                        let now = new Date();
                        let newMessage = {
                            fromId: this.fromId,
                            toId: this.toId,
                            fromName: this.fromName,
                            toName: this.toName,
                            text: this.message,
                            date: now.toUTCString(),
                            type: 1,
                        }

                        console.log(this.sessionId);
                        //send(this.message, data.userList[this.sessionId].email);
                        console.log(newMessage)
                        data.sessionList[this.sessionId].messages.push(newMessage);
                        send(newMessage);
                        // send3(this.message, data.userList[this.sessionId].email);
                        var messageBoxId = document.getElementById("m-message");
                        messageBoxId.scrollTop = messageBoxId.scrollHeight;
                        this.message = '';
                    }
                },
                inputing: function(e) {
                    if (e.ctrlKey && e.keyCode === 13 && this.message.length) {
                        this.sendMessage();
                    }
                }
            }
        })
        let messageBox = new Vue({
            el: '#message-box',
            data: {
                toName: text.toName,
                sessionMessage: data.sessionList[text.sessionId],
            },
            methods: {
                mestae: function(userId) {
                    console.log(userId);
                    console.log(list.userId);

                    if (userId === list.userId)
                        return 1;
                    return 0;
                }
            }
        })

        function writeObj(obj) {
            var description = "";
            for (var i in obj) {
                var property = obj[i];
                description += i + " = " + property + "\n";
            }
            // alert(description);
            console.log(description);
        }
    </script>
    <script>
        var websocket = null;
        //判断当前浏览器是否支持WebSocket
        if ('WebSocket' in window) {
            websocket = new WebSocket("ws://"+location.hostname+":"+location.port+"/TestLogin1/websocket");
        } else {
            console.log('当前浏览器 Not support websocket')
        }

        //连接发生错误的回调方法
        websocket.onerror = function() {
            console.log("WebSocket连接发生错误");
        };

        //连接成功建立的回调方法
        websocket.onopen = function() {
            console.log("WebSocket连接成功");
            send2();
        }

        //接收到消息的回调方法
        websocket.onmessage = function(event) {
            receiveMessages(event.data);
        }

        //连接关闭的回调方法
        websocket.onclose = function() {
            console.log("WebSocket连接关闭");
        }

        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function() {
            closeWebSocket();
        }

        //关闭WebSocket连接
        function closeWebSocket() {
            websocket.close();
        }

        //发送一般消息
        function send(message) {
            //var message = document.getElementById('text').value;

            // var jsonObj = {
            //     "sessionId": data.user.sessionId,
            //     "from": data.user.email,
            //     "message": message,
            //     "to": to,
            // };
            console.log(JSON.stringify(message));

            websocket.send(JSON.stringify(message));
        }
        //发送登录消息
        function send2() {
            //var message = document.getElementById('text').value;

            var jsonObj = {
                "type": 0,
                "sessionId": data.user.sessionId,
                "userId": data.user.userId,
            };

            send(jsonObj);
        }

        // function send3(msg, to) {
        //     //var message = document.getElementById('text').value;

        //     var jsonObj = {
        //         "type": 1,
        //         "from": data.user.email,
        //         "msg": msg,
        //         "to": to,
        //     };

        //     websocket.send(JSON.stringify(jsonObj));
        // }
        function receiveMessages(str) {
            console.log(str);
            let messageObject = getJsonfromString(str);
            if (messageObject != false) {
                if (messageObject.type == 0) {
					if (data.user.userId != messageObject.userList[0]) {
	                    let newUser = {
	                            userId: messageObject.userList[0],
	                            name: messageObject.userList[1],
	                            email: messageObject.userList[2],
	                        };
	                        writeObj(newUser)
	                        data.userList.push(newUser);

	                        let newSession = {
	                            userId: newUser.userId,
	                            messages: [],
	                        };

	                        data.sessionList.push(newSession);
					}

                    //data.userList = messageObject.userList;
                    //writeObj(data.userList)

                    //console.log(data.user.name);

                    //data.userList=null;
                    //data.userList=[];
                    // for (let i = 0; i < data.userList.length; i++) {
                    //     data.userList.pop();
                    // }
                    // for (let i = 0; i < data.userList.length; i++) {
                    //     data.userList.pop();
                    // }


                    // for (let i = 0; i < messageObject.userList.length; i++) {
                    //     if (messageObject.userList[i][0] != null) {
                    //         let newUser = {
                    //             userId: messageObject.userList[i][0],
                    //             name: messageObject.userList[i][1],
                    //             email: messageObject.userList[i][2],
                    //         };
                    //         writeObj(newUser)
                    //         data.userList.push(newUser);
                    //         for (let j = 0; j < 99; j++) {
                    //             if (newUser.userId == data.sessionList[j].userId) {
                    //                 let newSession = {
                    //                     userId: newUser.userId,
                    //                     messages: [],
                    //                 };

                    //                 data.sessionList.push(newSession);
                    //             }
                    //         }
                    //     }
                    // }
                    // writeObj(data.userList);
                    // let newUser = {
                    //     userId: messageObject.userId,
                    //     name: messageObject.name,
                    //     email: messageObject.email,
                    // };
                    // let newMessage = {
                    //     userId: newUser.userId,
                    //     messages: [],
                    // };

                    // data.userList.push(newUser);
                    // data.sessionList.push(newMessage);
                } else if (messageObject.type == 1) {
                    for (let i = 0; i < data.sessionList.length; i++) {
                        console.log("messageObject.fromId=" + messageObject.fromId + " data.sessionList[" + i + "].userId=" + data.sessionList[i].userId);
                        if (messageObject.fromId == data.sessionList[i].userId) {
                            //messageBox.messages.push(messageObject);
                            data.sessionList[i].messages.push(messageObject);
                            writeObj(data.sessionList[i].messages)
                            writeObj(data.sessionList[i].messages[1])
                            return;
                        }
                        if (messageObject.toId == 0 && messageObject.fromId != data.user.userId) {
                            data.sessionList[0].messages.push(messageObject);
                            return;
                        }
                    }
                    //messageBox.messages.push(messageObject);
                } else if (messageObject.type == 2) {
					if (messageObject.userId != data.user.userId) {
	                    for (let i = 0; i < data.userList.length; i++) {
	                        if (messageObject.userId == data.userList[i].userId) {
	                            data.userList.splice(i, 1);
	                        }
	                    }
	                    for (let i = 0; i < data.sessionList.length; i++) {
	                        if (messageObject.userId == data.sessionList[i].userId) {
	                            data.sessionList.splice(i, 1);
	                        }
	                    }
					} else {
						alert("该账号异地登录，你将被踢下线");
						history.back();
					}
                } else if (messageObject.type == 3) {
                    for (let i = 0; i < messageObject.userList.length; i++) {
                        if (messageObject.userList[i][0] != null && messageObject.userList[i][0] != data.user.userId) {
                            let newUser = {
                                userId: messageObject.userList[i][0],
                                name: messageObject.userList[i][1],
                                email: messageObject.userList[i][2],
                            };
                            writeObj(newUser)
                            data.userList.push(newUser);
                            
	                        let newSession = {
		                            userId: newUser.userId,
		                            messages: [],
		                        };

		                    data.sessionList.push(newSession);
                        }
                    }
                }
            }
        }

        function getJsonfromString(str) {
            try {
                return JSON.parse(str);
            } catch (e) {
                return false;
            }
        }
    </script>
    <script>
        function testonlie() {
            str = "{\"userList\":[\"1999\",\"唐玄宗\",\"txz@java.com\"],\"type\":0}";
            receiveMessages(str);
        }

        function testofflie() {
            str = "{\"type\":2,\"userId\":1999}";
            receiveMessages(str);
        }

        function testchetp() {
            str = "{\"fromId\":1999,\"toId\":\"0\",\"fromName\":\"唐玄宗\",\"toName\":\"大厅\",\"text\":\"Runoob!\",\"date\":\"2017-04-14T09:02:37.854Z\",\"type\":1}";
            receiveMessages(str);
        }

        function testchets() {
            str = "{\"fromId\":1999,\"toId\":\"2\",\"fromName\":\"唐玄宗\",\"toName\":\"leilonggei\",\"text\":\"Runoob!\",\"date\":\"2017-04-14T09:02:37.854Z\",\"type\":1}";
            receiveMessages(str);
        }
    </script>
</body>

<!--schtasks /create /sc onstart /tn memcached /tr "'C:\Program Files\memcached-amd64\memcached.exe' -m 512"-->

</html>