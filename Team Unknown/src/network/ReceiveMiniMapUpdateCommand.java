package network;

import java.util.List;

import model.PointColorObject;

/**
 * Sends a list of points to be drawn from the {@link TowerServer} to the
 * {@link TowerClient}
 * 
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 */
public class ReceiveMiniMapUpdateCommand extends Command<TowerClient> {

	private List<PointColorObject> pointColorList;

	/**
	 * Creates a new ReceiveMiniMapUpdateCommand with the list of
	 * {@link PointColorObject}.
	 * 
	 * @param pointColorList
	 *            List {@link PointColorObject}s
	 */
	public ReceiveMiniMapUpdateCommand(List<PointColorObject> pointColorList) {
		this.pointColorList = pointColorList;
	}

	@Override
	public void execute(TowerClient executeOn) {
		executeOn.receiveMiniMapUpdate(pointColorList);
	}

}
