package network;

/**
 * Sends a message from the second client to the server requesting the map id.
 * 
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 */
public class RequestMapIdPart1Command extends Command<TowerServer> {

	private String clientId;

	/**
	 * Creates a RequestMapIdPart1Command with the client that wants the
	 * information.
	 * 
	 * @param clientId
	 *            The client that requested the map id.
	 */
	public RequestMapIdPart1Command(String clientId) {
		this.clientId = clientId;
	}

	@Override
	public void execute(TowerServer executeOn) {
		executeOn.processMapIdRequestPart1(clientId);
	}

}
