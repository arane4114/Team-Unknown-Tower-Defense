package network;

public class ReceiveMoneyCommand extends Command<TowerClient> {
	private Integer money;
	
	public ReceiveMoneyCommand(Integer money){
		this.money = money;
	}
	@Override
	public void execute(TowerClient executeOn) {
		executeOn.addMoney(money);
	}

}
