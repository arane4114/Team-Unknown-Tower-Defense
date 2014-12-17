package network;

public class RequestMapIdPart4Command extends Command<TowerClient> {

	private Integer mapId;
	
	public RequestMapIdPart4Command(Integer mapId) {
		this.mapId = mapId;
	}

	@Override
	public void execute(TowerClient executeOn) {
		executeOn.processRequestMapIdPart4Command(mapId);
	}

}
