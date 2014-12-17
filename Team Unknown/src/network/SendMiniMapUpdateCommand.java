package network;

import java.util.List;

import model.PointColorObject;

/**
 * Sends mini map information from a client to a server.
 * 
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 *
 */
public class SendMiniMapUpdateCommand extends Command<TowerServer> {

	private List<PointColorObject> pointColorList;
	private String client;

	/**
	 * Encapsulates information to send to another client to allow it to creaete
	 * an accurate minimap.
	 * 
	 * @param pointColorList
	 *            List of {@link PointColorObject}s
	 * @param client
	 *            The client that sent the mini map info.
	 */
	public SendMiniMapUpdateCommand(List<PointColorObject> pointColorList,
			String client) {
		this.pointColorList = pointColorList;
		this.client = client;
	}

	@Override
	public void execute(TowerServer executeOn) {
		executeOn.processSendMiniMapUpdateCommand(pointColorList, client);
	}

}
