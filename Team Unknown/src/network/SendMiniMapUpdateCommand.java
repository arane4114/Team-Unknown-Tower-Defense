package network;

import java.util.List;

public class SendMiniMapUpdateCommand extends Command<TowerServer> {

	private List<PointColorObject> pointColorList;
	private String client;

	public SendMiniMapUpdateCommand(List<PointColorObject> pointColorList, String client) {
		this.pointColorList = pointColorList;
		this.client = client;
	}

	@Override
	public void execute(TowerServer executeOn) {
		executeOn.processSendMiniMapUpdateCommand(pointColorList, client);
	}

}
