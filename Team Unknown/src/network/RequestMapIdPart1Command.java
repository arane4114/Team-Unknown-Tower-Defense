package network;

public class RequestMapIdPart1Command extends Command<TowerServer> {

	private String clientId;
	
	public RequestMapIdPart1Command(String clientId){
		this.clientId = clientId;
	}
	@Override
	public void execute(TowerServer executeOn) {
		executeOn.processMapIdRequestPart1(clientId);
	}

}
