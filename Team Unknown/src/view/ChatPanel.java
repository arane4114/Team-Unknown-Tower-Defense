package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import chatPanelModel.AddMessageCommand;

/**
 * This class encapsulates the NRC chat components. It writes out commands to the server in response to user input
 * 
 * @author Gabriel Kishi
 *
 */
public class ChatPanel extends JPanel{
	private static final long serialVersionUID = 7686336736079994065L;
	
	private JTextArea textArea; // chat log displayed here
	private JTextField textField; // field where user enters text
	
	private ObjectOutputStream output; // output stream to server
	private String clientName;
	
	private class EnterListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String s = textField.getText();
			try{
				output.writeObject(new AddMessageCommand(clientName + ":  " + s));
			}catch(Exception e){
				e.printStackTrace();
			}
			textField.setText("");
		}
	}
	
	/**
	 * Constructs a new ChatPanel for given username, using the given OutputStream
	 * 
	 * @param clientName	user name of client
	 * @param output		output stream to server
	 */
	public ChatPanel(String clientName, ObjectOutputStream output){
		this.output = output;
		this.clientName = clientName;
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		
		/* Setup the GUI */
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(800, 600));
		
		// create gui components
		textField = new JTextField();
		JButton enterButton = new JButton("Send");
		
		textField.setPreferredSize(new Dimension(600, 40));
		enterButton.setPreferredSize(new Dimension(100, 40));
		
		// add button and field to a lower panel
		JPanel bottomPanel = new JPanel();
		bottomPanel.add(textField);
		bottomPanel.add(enterButton);
		
		// add text area and lower panel
		this.add(new JScrollPane(textArea), BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
		
		// create a listener for writing messages to server
		ActionListener listener = new EnterListener();
		
		// attach listener to field & button
		textField.addActionListener(listener);
		enterButton.addActionListener(listener);
	}
	
	/**
	 * Updates the chat log. Called by UpdateClientCommands
	 * 
	 * @param messages	the current chat log
	 */
	public void update(List<String> messages) {
		String s = "";
		for (String message: messages)
			s = s + message + "\n";
		
		textArea.setText(s);
		textArea.setCaretPosition(s.length());
		repaint();
	}
}
