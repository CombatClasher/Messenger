import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import java.io.*;

public class ChatClient extends JFrame implements Runnable {
	Socket socket;
	JTextArea ta;
	JTextField tf;
	JButton send, logout;
	
	Thread thread;
	
	DataInputStream din;
	DataOutputStream dout;
	
	String LoginName;
	
	ChatClient(String login) throws UnknownHostException, IOException{
		super(login);
		LoginName = login;
		
		ta = new JTextArea(18, 50);
		tf = new JTextField(50);
		send = new JButton("Send");
		logout = new JButton("Logout");

		addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {

            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                try {
                    dout.writeUTF(LoginName + " LOGOUT");
                    System.exit(1);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {

            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {

            }
        });

		tf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {

            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER){
                    try {
                        if( tf.getText().length() > 0)
                            dout.writeUTF(LoginName + " " +"DATA " +tf.getText().toString());
                        tf.setText("");
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
		send.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
				    if(tf.getText().length() > 0)
					     dout.writeUTF(LoginName + " " +"DATA " +tf.getText().toString());
					tf.setText("");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		logout.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					dout.writeUTF(LoginName + " LOGOUT");
					System.exit(1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		socket = new Socket("localhost", 2855);
		
		din = new DataInputStream(socket.getInputStream());
		dout = new DataOutputStream(socket.getOutputStream());
		
		dout.writeUTF(LoginName);
		dout.writeUTF(LoginName + " " + "LOGIN");
		
		thread = new Thread(this);
		thread.start();
		setUp();
	}
	
	private void setUp() {
		// TODO Auto-generated method stub
		setSize(600, 400);
		JPanel panel = new JPanel();
		panel.add(new JScrollPane(ta));
		panel.add(tf);
		panel.add(send);
		panel.add(logout);
		add(panel);
		setVisible(true);
		
	}

	public void run(){
		while(true){
			try {
				ta.append("\n" + din.readUTF());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
