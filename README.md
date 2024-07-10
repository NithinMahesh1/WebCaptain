# WebCaptain
Parameter based application allowing users to send commands to their computer through a cloud based webapp

## Architecture - NEW
Three main components that comprise this application:

Front End: Vue.js, Axios
Back End: ASP.NET Core Web API
Client/Desktop Side: .NET 6 Worker Service

This will allow for requests to be made via the client vue.js front end via HTTP/REST api calls to server side Web api which will have a websocket SignalR connection for real time communication with the Desktop client.