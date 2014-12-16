package serverCommands;

import java.util.List;
import java.util.LinkedList;

import controller.NRCClient;

/**
 * Updates a client with the current list of chat messages
 * 
 * @author Gabriel Kishi
 *
 */
public class UpdateChatClient extends Command<NRCClient> {
	private static final long serialVersionUID = 4222014184904080846L;
	private List<String> messages; // the message log from the server
	
	/**
	 * Creates a new UpdateClientCommand with the given log of messages
	 * @param messages	the log of messages
	 */
	public UpdateChatClient(List<String> messages){
		this.messages = new LinkedList<String>(messages); // note: we are making a copy of the given list
	}

	public void execute(NRCClient executeOn) {
		// update the client
		executeOn.update(messages);
	}
}
