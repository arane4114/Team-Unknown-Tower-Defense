package network;

public class OtherPlayerHasBeenHitCommand extends Command<TowerClient> {

	@Override
	public void execute(TowerClient executeOn) {
		executeOn.otherPlayerHasBeenHit();
	}
}
