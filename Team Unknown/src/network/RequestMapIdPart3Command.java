package network;

public class RequestMapIdPart3Command extends Command<TowerServer> {

	private String clientName;
	private Integer mapId;
	
	public RequestMapIdPart3Command(String clientName, Integer mapId) {
		this.clientName = clientName;
		this.mapId = mapId;
	}

	@Override
	public void execute(TowerServer executeOn) {
		executeOn.processMapIdRequestPart3(clientName, mapId);
	}

}
