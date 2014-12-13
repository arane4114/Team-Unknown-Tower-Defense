package serverCommands;

import server.NRCServer;

/**
 *	Adds a text message to the server's chat log
 */
public class AddMessageCommand extends Command<NRCServer>{
	private static final long serialVersionUID = 8394654307009158284L;
	private String message; // message from client
	
	/**
	 * Creates an AddMessageCommand with the given message
	 * 
	 * @param message	message to add to log
	 */
	public AddMessageCommand(String message){
		this.message = message;
	}
	
	public void execute(server.NRCServer executeOn) {
		// add message to server's chat log
		executeOn.addMessage(message);
	}

}
