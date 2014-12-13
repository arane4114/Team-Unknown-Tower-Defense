package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import chatPanelModel.Command;
import chatPanelModel.DisconnectCommand;
import view.ChatPanel;

/**
 * The client side of NRC. This class displays the current chat log and
 * sends AddMessageCommands to the server.
 * 
 * @author Gabriel Kishi
 */
public class NRCClient {
	private String clientName; // user name of the client
	private ChatPanel chatPanel;
	
	private Socket server; // connection to server
	private ObjectOutputStream out; // output stream
	private ObjectInputStream in; // input stream

	/**
	 * This class reads and executes commands sent from the server
	 * 
	 * @author Gabriel Kishi
	 *
	 */
	private class ServerHandler implements Runnable{
		public void run() {
			try{
				while(true){
					// read a command from server and execute it
					Command<NRCClient> c = (Command<NRCClient>)in.readObject();
					c.execute(NRCClient.this);
				}
			}
			catch(SocketException e){
				return; // "gracefully" terminate after disconnect
			}
			catch (EOFException e) {
				return; // "gracefully" terminate
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public NRCClient(){
		// ask the user for a host, port, and user name
		String host = JOptionPane.showInputDialog("Host address:");
		String port = JOptionPane.showInputDialog("Host port:");
		clientName = JOptionPane.showInputDialog("User name:");
		
		if (host == null || port == null || clientName == null)
			return;
		
		try{
			// Open a connection to the server
			server = new Socket(host, Integer.parseInt(port));
			out = new ObjectOutputStream(server.getOutputStream());
			in = new ObjectInputStream(server.getInputStream());
			
			// write out the name of this client
			out.writeObject(clientName);
			
			// start a thread for handling server events
			new Thread(new ServerHandler()).start();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void willClose(){
		try {
			out.writeObject(new DisconnectCommand(clientName));
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 	Creates a ChatPanel and adds it to this frame
	 */
	public ChatPanel getChatPanel() {
		chatPanel = new ChatPanel(clientName, out);
		return chatPanel;
	}

	public static void main(String[] args){
		new NRCClient();
	}

	/**
	 * Updates the ChatPanel with the updated message log
	 * 
	 * @param messages	the log of messages to display
	 */
	public void update(List<String> messages) {
		chatPanel.update(messages);
	}
}
