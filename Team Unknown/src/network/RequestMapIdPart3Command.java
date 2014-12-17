package network;

/**
 * Sends the map id information from the host client to the server.
 * 
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 *
 */
public class RequestMapIdPart3Command extends Command<TowerServer> {

	private String clientName;
	private Integer mapId;

	/**
	 * Creates a new command with the client that sent it and the map id.
	 * 
	 * @param clientName
	 *            Sender name
	 * @param mapId
	 *            Map information
	 */
	public RequestMapIdPart3Command(String clientName, Integer mapId) {
		this.clientName = clientName;
		this.mapId = mapId;
	}

	@Override
	public void execute(TowerServer executeOn) {
		executeOn.processMapIdRequestPart3(clientName, mapId);
	}

}
