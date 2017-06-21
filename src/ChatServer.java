import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import java.util.Vector;

public class ChatServer {
	static Vector ClientSockets;
	static Vector LoginNames;
	
	ChatServer() throws IOException{
		ServerSocket server = new ServerSocket(2855);
		LoginNames = new Vector();
		ClientSockets = new Vector();
		
		while(true){
			Socket client = server.accept();
			AcceptClient acceptClient = new AcceptClient(client);
		}
	}
	
	public static void main(String args[]) throws IOException{
		ChatServer server = new ChatServer();
	}
	
	class AcceptClient extends Thread{
		Socket ClientSocket;
		DataInputStream din;
		DataOutputStream dout;
		AcceptClient(Socket client) throws IOException{
			ClientSocket = client;
			din = new DataInputStream(client.getInputStream());
			dout = new DataOutputStream(client.getOutputStream());
			
			String LoginName = din.readUTF();
			LoginNames.add(LoginName);
			ClientSockets.add(ClientSocket);
			start();
		}
		
		public void run(){
			while(true){
				try {
					int lo = -1;
					String msgFromClient = din.readUTF();
					StringTokenizer st = new StringTokenizer(msgFromClient);
					String LoginName = st.nextToken();
					String MsgType = st.nextToken();
					String msg = "";
					while( st.hasMoreTokens()){
						msg = msg + " " + st.nextToken();
					}
					
					if( MsgType.equals("LOGIN")){
					for(int i=0; i<LoginNames.size(); i++){
						Socket pSocket = (Socket) ClientSockets.get(i);
						DataOutputStream pOut = new DataOutputStream(pSocket.getOutputStream());
						pOut.writeUTF(LoginName + " has logged in.");
						}
					}
					else if( MsgType.equals("LOGOUT")){
					for(int i=0; i<LoginNames.size(); i++){
						if(LoginName.equals(LoginNames.get(i)))
							lo = i;
						Socket pSocket = (Socket) ClientSockets.get(i);
						DataOutputStream pOut = new DataOutputStream(pSocket.getOutputStream());
						pOut.writeUTF(LoginName + " has logged out.");
						}
					if( lo >= 0){
					LoginNames.removeElementAt(lo);
					ClientSockets.removeElementAt(lo);
					}
					}
					else {
						for (int i = 0; i < LoginNames.size(); i++) {
							Socket pSocket = (Socket) ClientSockets.get(i);
							DataOutputStream pOut = new DataOutputStream(pSocket.getOutputStream());
							pOut.writeUTF(LoginName + ": " + msg);
						}
					}
					if(MsgType.equals("LOGOUT"))
						break;
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	
}
