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
import model.PointColorObject;
import view.ChatPanel;
import view.MiniMapPanel;
import view.TowerDefenseGUI;

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
	private int mapId;
	private TowerDefenseGUI gui;

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
	 * Creates the client in the multiplayer scenario.
	 * 
	 * @param clientName
	 *            The username to attibute all commands to.
	 * @param host
	 *            The address of the host {@link TowerServer}.
	 * @param player
	 *            A link to the {@link Player} object
	 * @param miniMap
	 *            A link to the {@link MiniMapPanel} object
	 * @param mapId
	 *            A link to the map id
	 * @param gui
	 *            A link to the {@link TowerDefenseGUI}
	 */
	public TowerClient(String clientName, String host, Player player,
			MiniMapPanel miniMap, int mapId, TowerDefenseGUI gui) {
		// ask the user for a host, port, and user name
		String port = "9001";
		this.clientName = clientName;
		this.player = player;
		this.miniMap = miniMap;
		this.mapId = mapId;
		this.gui = gui;

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
	 * Returns a chat panel.
	 * 
	 * @return A networked chat panel.
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
	 * Called when an {@link ReceiveMoneyCommand} was sent by the
	 * {@link TowerServer}
	 * 
	 * @param money
	 *            The amount of money to be earned.
	 */
	public void addMoney(Integer money) {
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

	/**
	 * Sends mini map information to the other client.
	 * 
	 * @param pointColorList
	 *            List of points and their color values.
	 */
	public void sendMiniMapUpdate(List<PointColorObject> pointColorList) {
		try {
			out.writeObject(new SendMiniMapUpdateCommand(pointColorList,
					clientName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Forwards mini map information to the mini map renderer.
	 * 
	 * @param pointColorList
	 *            List of points and their associated colors.
	 */
	public void receiveMiniMapUpdate(List<PointColorObject> pointColorList) {
		miniMap.setPointColorList(pointColorList);
	}

	/**
	 * Sets of the map id request process.
	 */
	public void getMapId() {
		try {
			out.writeObject(new RequestMapIdPart1Command(clientName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends a map id value back to the server.
	 */
	public void processRequestMapIdPart2Command() {
		try {
			out.writeObject(new RequestMapIdPart3Command(clientName, mapId));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends the map id from the other server to the {@link TowerDefenseGUI}
	 * linked to this client.
	 * 
	 * @param mapId2
	 *            Map id for the multiplayer session.
	 */
	public void processRequestMapIdPart4Command(Integer mapId2) {
		gui.setMapId(mapId2);
	}
}
