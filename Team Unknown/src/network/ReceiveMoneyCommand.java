package network;

/**
 * Send by a server to a client when the client is to receive a certain amount
 * of money.
 * 
 * @author Abhishek
 *
 */
public class ReceiveMoneyCommand extends Command<TowerClient> {
	private Integer money;

	/**
	 * Creates a command with a certain amount of money to be sent.
	 * 
	 * @param money
	 *            The amound of money to be sent.
	 */
	public ReceiveMoneyCommand(Integer money) {
		this.money = money;
	}

	/**
	 * Tells the client it has received some money.
	 */
	@Override
	public void execute(TowerClient executeOn) {
		executeOn.addMoney(money);
	}

}
