package ChatClient;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



@SuppressWarnings("serial")
public class GuiSignUp extends JPanel {
	private JTextField txUsername;
	private JTextField txPassword;

	/**
	 * Create the panel.
	 */
	public GuiSignUp() {
		setLayout(null);
		this.setBounds(0, 0, 450, 278);
		
		JLabel lblSignUp = new JLabel("Sign Up");
		lblSignUp.setBounds(34, 25, 61, 16);
		add(lblSignUp);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(34, 73, 79, 16);
		add(lblUsername);
		
		txUsername = new JTextField();
		txUsername.setBounds(118, 68, 130, 26);
		add(txUsername);
		txUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(34, 114, 79, 16);
		add(lblPassword);
		
		txPassword = new JTextField();
		txPassword.setBounds(118, 109, 130, 26);
		add(txPassword);
		txPassword.setColumns(10);
		
		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChatClientApp.frame.client.start();
				String username = txUsername.getText();
				String password = txPassword.getText();
				if(username.equals("") || password.equals("")){
					JOptionPane.showMessageDialog(ChatClientApp.frame,
						    "Both username and password can not be empty!",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);
					return;
				}
				String[] response = ChatClientApp.frame.client.signup(username, password);
				ChatClientApp.frame.client.stop();
				JOptionPane.showMessageDialog(ChatClientApp.frame,
					    response[2],
					    "Message from server",
					    JOptionPane.INFORMATION_MESSAGE);
				if(response[1].equals("true")){
					ChatClientApp.frame.setContentPane(
							ChatClientApp.frame.guiSignin);
					ChatClientApp.frame.setTitle("Sign in");
				}
			}
		});
		btnSignUp.setBounds(34, 172, 91, 29);
		add(btnSignUp);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChatClientApp.frame.setContentPane(
						ChatClientApp.frame.guiSignin);
				ChatClientApp.frame.setTitle("Sign in");
			}
		});

		btnCancel.setBounds(157, 172, 91, 29);
		add(btnCancel);
		
		

	}
}
