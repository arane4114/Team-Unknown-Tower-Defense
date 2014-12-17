package network;

/**
 * Sends a request from the server to the host client for map id.
 * 
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 *
 */
public class RequestMapIdPart2Command extends Command<TowerClient> {

	@Override
	public void execute(TowerClient executeOn) {
		executeOn.processRequestMapIdPart2Command();
	}

}
