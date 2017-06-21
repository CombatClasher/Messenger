import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.*;


public class Login {
	public static void main(String args[]){
		JFrame fr = new JFrame("Login");
		JPanel panel = new JPanel();
		JLabel lb = new JLabel("Enter your username ");
		JTextField tf = new JTextField(20);
		JButton login = new JButton("Login");
		panel.add(lb);
		panel.add(tf);
		panel.add(login);
		fr.add(panel);
		fr.setSize(300, 150);
		fr.setVisible(true);

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
                        ChatClient client = new ChatClient(tf.getText().toString());
                        fr.setVisible(false);
                        fr.dispose();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
		
		login.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					ChatClient client = new ChatClient(tf.getText().toString());
					fr.setVisible(false);
					fr.dispose();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
	}

}
