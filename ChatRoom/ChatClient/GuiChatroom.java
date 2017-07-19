package ChatClient;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class GuiChatroom extends JPanel {
	private JTextField txInput;
	private JList<String> listChat;
	private Thread thread;
	private Timer timer;
	private JScrollPane scrollPane;
	private JButton btnSend;

	/**
	 * Create the panel.
	 */
	public GuiChatroom() {
		setLayout(null);
		this.setBounds(0, 0, 800, 600);
		
		listChat = new JList<String>();
		listChat.setValueIsAdjusting(true);
		listChat.setModel(new DefaultListModel<String>());
		scrollPane = new JScrollPane(listChat);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(6, 6, 788, 514);
		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
			private int prevMax = 0;
		    public void adjustmentValueChanged(AdjustmentEvent e) {  
		    	if(e.getAdjustable().getMaximum() != prevMax){
		    		prevMax = e.getAdjustable().getMaximum();
		    		e.getAdjustable().setValue(e.getAdjustable().getMaximum());
		    	}
		        
		    }
		});
		add(scrollPane);
		
		txInput = new JTextField();
		txInput.setBounds(16, 532, 587, 29);
		txInput.setColumns(10);
		txInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSend.doClick();
			}
		});
		add(txInput);
		
		
		btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = txInput.getText();
				if(msg.equals("")){
					JOptionPane.showMessageDialog(ChatClientApp.frame,
						    "You can not send empty messages!",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);
					return;
				}
				txInput.setText("");
				ChatClientApp.frame.client.send_message(msg);
				txInput.grabFocus();
			}
		});
		btnSend.setBounds(640, 533, 83, 29);
		add(btnSend);

	}
	
	public void StartCheckingMessages(){
		
		thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					String[] response = ChatClientApp.frame.client.getResponse();
					if(response[0].equals("get-message")){
						DefaultListModel<String> model = (DefaultListModel<String>) listChat.getModel();
						for(int i=1; i<response.length; i=i+4){
							if(ChatClientApp.frame.client.offset < Integer.parseInt(response[i])){
								ChatClientApp.frame.client.offset = Integer.parseInt(response[i]);
								model.addElement(String.format("%s @ (%s): %s", response[i+1], response[i+2], response[i+3]));
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
						
					}
					if(response[0].equals("send-message")){
						if(response[1].equals("true")){
							System.out.println("Message sent.");
						}else{
							JOptionPane.showMessageDialog(ChatClientApp.frame,
								    "Cannot send message!",
								    "Error",
								    JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			}
		});
		thread.start();
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				ChatClientApp.frame.client.get_message();
			}
		}, 1000, 2000);
		
	}
	public void StopCheckingMessages(){
		timer.cancel();
	}
}


