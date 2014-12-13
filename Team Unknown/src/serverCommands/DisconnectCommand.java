package serverCommands;

import server.NRCServer;
/**
 * This command is sent by a client that is disconnecting
 * 
 * @author Gabriel Kishi
 *
 */
public class DisconnectCommand extends Command<NRCServer>{
	private static final long serialVersionUID = -8557424886231888586L;
	private String clientName; // client who is disconnecting
	
	/**
	 * Creates a disconnect command for the given client
	 * 
	 * @param name	username of client to disconnect
	 */
	public DisconnectCommand(String name){
		clientName = name;
	}
	
	@Override
	public void execute(NRCServer executeOn) {
		// disconnect client
		executeOn.disconnect(clientName);
	}

}
