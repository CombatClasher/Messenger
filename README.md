# Messenger
Contains a java project that give the basic implementation of using ServerSocket(for server) and Socket(for clients) for sending data.
It has three java files:
1. ChatServer.java: creates a ServerSocket at port 2855 and accept client connections. It has AcceptClient as inner class for handling
the clients
2. ChatClient.java: creates a connection to server and GUI for sending the message.
3. Login.java: create a simple dialog box for inputing the username
It implements the chat on localhost, can be changed just by uodating the IP address.
Usefull in college LAN coverage. Can also built file sharing on the same principle. The new version will be introduced soon.
