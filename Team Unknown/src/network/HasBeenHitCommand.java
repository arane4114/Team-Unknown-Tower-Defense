package network;

public class HasBeenHitCommand extends Command<TowerServer> {
	private String clientName;
	
	public HasBeenHitCommand(String clientName){
		this.clientName = clientName;
	}
	
	@Override
	public void execute(TowerServer executeOn) {
		executeOn.hasBeenHit(clientName);
	}
	
}
