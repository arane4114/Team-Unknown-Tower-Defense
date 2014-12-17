package network;

import java.util.List;

public class ReceiveMiniMapUpdateCommand extends Command<TowerClient> {

	private List<PointColorObject> pointColorList;
	
	public ReceiveMiniMapUpdateCommand(List<PointColorObject> pointColorList){
		this.pointColorList = pointColorList;
	}
	
	@Override
	public void execute(TowerClient executeOn) {
		executeOn.receiveMiniMapUpdate(pointColorList);
	}

}
