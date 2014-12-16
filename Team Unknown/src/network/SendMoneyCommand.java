package network;


public class SendMoneyCommand extends Command<TowerServer> {
	
	private String senderName;
	private Integer money;
	
	public SendMoneyCommand(Integer money, String senderName){
		this.senderName = senderName;
		this.money = money;
	}
	
	@Override
	public void execute(TowerServer executeOn) {
		executeOn.sendMoney(senderName, money);
	}

}
