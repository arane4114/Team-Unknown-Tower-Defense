package network;
/**
 * Send by the server when the other player has been hit.
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 */
public class OtherPlayerHasBeenHitCommand extends Command<TowerClient> {
	
	/**
	 * Tells the client that the other player has been hit and it should reduce health.
	 */
	@Override
	public void execute(TowerClient executeOn) {
		executeOn.otherPlayerHasBeenHit();
	}
}
