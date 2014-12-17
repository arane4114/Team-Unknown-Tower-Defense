package network;

/**
 * This is sent by the player that has taken damage to the server.
 * 
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 */
public class HasBeenHitCommand extends Command<TowerServer> {
	private String clientName;

	public HasBeenHitCommand(String clientName) {
		this.clientName = clientName;
	}

	/**
	 * Tells the server to process the loss of health.
	 */
	@Override
	public void execute(TowerServer executeOn) {
		executeOn.hasBeenHit(clientName);
	}

}
