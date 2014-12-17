package network;

/**
 * Sends the map id information to the client that requested it.
 * 
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 *
 */
public class RequestMapIdPart4Command extends Command<TowerClient> {

	private Integer mapId;

	/**
	 * Creates a command with the map id of the multiplayer session.
	 * 
	 * @param mapId
	 *            The map used in this multiplayer session.
	 */
	public RequestMapIdPart4Command(Integer mapId) {
		this.mapId = mapId;
	}

	@Override
	public void execute(TowerClient executeOn) {
		executeOn.processRequestMapIdPart4Command(mapId);
	}

}
