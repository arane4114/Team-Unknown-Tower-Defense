package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import model.PointColorObject;

/**
 * This is a modified version of the NRC server provided in section. It handles
 * the inter-client connectivity.
 * 
 * @author Gabriel Kishi
 */
public class TowerServer {
	private ServerSocket socket; // the server socket

	private List<String> messages; // the chat log
	private HashMap<String, ObjectOutputStream> outputs; // map of all connected
															// users' output
															// streams

	/**
	 * This thread reads and executes commands sent by a client
	 */
	private class ClientHandler implements Runnable {
		private ObjectInputStream input; // the input stream from the client

		public ClientHandler(ObjectInputStream input) {
			this.input = input;
		}

		public void run() {
			try {
				while (true) {
					// read a command from the client, execute on the server
					@SuppressWarnings("unchecked")
					Command<TowerServer> command = (Command<TowerServer>) input
							.readObject();
					command.execute(TowerServer.this);

					// terminate if client is disconnecting
					if (command instanceof DisconnectCommand) {
						input.close();
						return;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This thread listens for and sets up connections to new clients
	 */
	private class ClientAccepter implements Runnable {
		public void run() {
			try {
				while (true) {
					// accept a new client, get output & input streams
					Socket s = socket.accept();
					ObjectOutputStream output = new ObjectOutputStream(
							s.getOutputStream());
					ObjectInputStream input = new ObjectInputStream(
							s.getInputStream());

					// read the client's name
					String clientName = (String) input.readObject();

					// map client name to output stream
					outputs.put(clientName, output);

					// spawn a thread to handle communication with this client
					new Thread(new ClientHandler(input)).start();

					// add a notification message to the chat log
					addMessage(clientName + " connected");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Swts up a tower server at port 9001.
	 */
	public TowerServer() {
		this.messages = new ArrayList<String>(); // create the chat log
		this.outputs = new HashMap<String, ObjectOutputStream>(); // setup the
																	// map

		try {
			// start a new server on port 9001
			socket = new ServerSocket(9001);
			System.out.println("Tower Server started on port 9001");

			// spawn a client accepter thread
			new Thread(new ClientAccepter()).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds a message to the chat log. Called by an AddMessageCommand.
	 * 
	 * @param message
	 *            message to add
	 */
	public void addMessage(String message) {
		messages.add(message);
		updateClients();
	}

	/**
	 * Writes an UpdateClientCommand to every connected user.
	 */
	public void updateClients() {
		// make an UpdateClientCommmand, write to all connected users
		UpdateChatClient update = new UpdateChatClient(messages);
		try {
			for (ObjectOutputStream out : outputs.values())
				out.writeObject(update);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Send money to the a client.
	 * 
	 * @param sender
	 *            The sender of the money.
	 * @param money
	 *            The amount of money to be sent.
	 */
	public void sendMoney(String sender, Integer money) {
		for (Entry<String, ObjectOutputStream> hashMapItem : outputs.entrySet()) {
			if (!hashMapItem.getKey().equals(sender)) {
				System.out.println("Server: Sending " + money + " to "
						+ hashMapItem.getKey());
				ReceiveMoneyCommand command = new ReceiveMoneyCommand(money);
				try {
					hashMapItem.getValue().writeObject(command);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Disconnects a given user from the server gracefully
	 * 
	 * @param clientName
	 *            user to disconnect
	 */
	public void disconnect(String clientName) {
		try {
			outputs.get(clientName).close(); // close output stream
			outputs.remove(clientName); // remove from map

			// add notification message
			addMessage(clientName + " disconnected");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Processed when one client is hit. Relays that information to the other
	 * client.
	 * 
	 * @param clientName
	 *            The client that was hit.
	 */
	public void hasBeenHit(String clientName) {
		for (Entry<String, ObjectOutputStream> hashMapItem : outputs.entrySet()) {
			if (!hashMapItem.getKey().equals(clientName)) {
				try {
					hashMapItem.getValue().writeObject(
							new OtherPlayerHasBeenHitCommand());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Relays mini map date between two clients.
	 * 
	 * @param pointColorList
	 *            List of information used to create a mini map render.
	 * @param client
	 *            the client that sent the information.
	 */
	public void processSendMiniMapUpdateCommand(
			List<PointColorObject> pointColorList, String client) {
		for (Entry<String, ObjectOutputStream> hashMapItem : outputs.entrySet()) {
			if (!hashMapItem.getKey().equals(client)) {
				try {
					hashMapItem.getValue().writeObject(
							new ReceiveMiniMapUpdateCommand(pointColorList));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Helps bridge the map id request.
	 * 
	 * @param clientId
	 *            Client that wants the map id.
	 */
	public void processMapIdRequestPart1(String clientId) {
		for (Entry<String, ObjectOutputStream> hashMapItem : outputs.entrySet()) {
			if (!hashMapItem.getKey().equals(clientId)) {
				try {
					hashMapItem.getValue().writeObject(
							new RequestMapIdPart2Command());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Returns a map id.
	 * 
	 * @param clientName
	 *            Senders name
	 * @param mapId
	 *            Map id value.
	 */
	public void processMapIdRequestPart3(String clientName, int mapId) {
		for (Entry<String, ObjectOutputStream> hashMapItem : outputs.entrySet()) {
			if (!hashMapItem.getKey().equals(clientName)) {
				try {
					hashMapItem.getValue().writeObject(
							new RequestMapIdPart4Command(mapId));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
