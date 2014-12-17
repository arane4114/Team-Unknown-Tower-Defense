package network;

public class RequestMapIdPart2Command extends Command<TowerClient> {

	@Override
	public void execute(TowerClient executeOn) {
		executeOn.processRequestMapIdPart2Command();
	}

}
