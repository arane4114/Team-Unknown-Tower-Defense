package network;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Player;
import view.ChatPanel;
import view.MiniMapPanel;

/**
 * Modified from the NRC code provided in section. This class is the interface
 * between the game and the server.
 * 
 * @author Gabriel Kishi
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 */
public class TowerClient {
	private String clientName; // user name of the client
	private ChatPanel chatPanel;

	private Socket server; // connection to server
	private ObjectOutputStream out; // output stream
	private ObjectInputStream in; // input stream
	private Player player;
	private MiniMapPanel miniMap;

	/**
	 * This class reads and executes commands sent from the server
	 * 
	 * @author Gabriel Kishi
	 *
	 */
	private class ServerHandler implements Runnable {
		public void run() {
			try {
				while (true) {
					// read a command from server and execute it
					Command<TowerClient> c = (Command<TowerClient>) in
							.readObject();
					c.execute(TowerClient.this);
				}
			} catch (SocketException e) {
				return; // "gracefully" terminate after disconnect
			} catch (EOFException e) {
				return; // "gracefully" terminate
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Creates a tower client and connects to the server.
	 * 
	 * @param host
	 * @param clientName
	 */
	public TowerClient(String clientName, String host, Player player, MiniMapPanel miniMap) {
		// ask the user for a host, port, and user name
		String port = "9001";
		this.clientName = clientName;
		this.player = player;
		this.miniMap = miniMap;

		if (host == null || port == null || clientName == null)
			return;

		try {
			// Open a connection to the server
			server = new Socket(host, Integer.parseInt(port));
			out = new ObjectOutputStream(server.getOutputStream());
			in = new ObjectInputStream(server.getInputStream());

			// write out the name of this client
			out.writeObject(clientName);

			// start a thread for handling server events
			new Thread(new ServerHandler()).start();

			if (chatPanel == null) {
				chatPanel = new ChatPanel(clientName, out);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tells the client that it is about to close, allowing a clean disconnect
	 * from the server.
	 */
	public void willClose() {
		try {
			out.writeObject(new DisconnectCommand(clientName));
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a ChatPanel and adds it to this frame
	 */
	public ChatPanel getChatPanel() {
		return chatPanel;
	}

	/**
	 * Occurs when the client has been hit. Sends a {@link HasBeenHitCommand} to
	 * the {@link TowerServer}.
	 */
	public void hasBeenHit() {
		try {
			out.writeObject(new HasBeenHitCommand(clientName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends a certain amount of money to another player.
	 * 
	 * @param money
	 *            The amount of money to be sent.
	 * @return Checks if the sending was successful. Allows the client to be
	 *         refunded if not.
	 */
	public boolean sendMoney(Integer money) {
		try {
			out.writeObject(new SendMoneyCommand(money, clientName));
			System.out.println(clientName + ": Sending " + money);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Called when an {@link ReceiveMoneyCommand} was sent by the {@link Server}
	 * 
	 * @param money
	 *            The amount of money to be earned.
	 */
	public void addMoney(Integer money) {
		System.out.println(clientName + ": Received " + money);
		player.earn(money);
	}

	/**
	 * Updates the ChatPanel with the updated message log
	 * 
	 * @param messages
	 *            the log of messages to display
	 */
	public void update(List<String> messages) {
		if (chatPanel == null) {
			chatPanel = new ChatPanel(clientName, out);
		}
		chatPanel.update(messages);

	}

	/**
	 * Damages the player if the other player was hit.
	 */
	public void otherPlayerHasBeenHit() {
		player.damage(1);
	}

	public void sendMiniMapUpdate(List<PointColorObject> pointColorList) {
		try {
			out.writeObject(new SendMiniMapUpdateCommand(pointColorList, clientName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void receiveMiniMapUpdate(List<PointColorObject> pointColorList) {
		miniMap.setPointColorList(pointColorList);
	}
}
