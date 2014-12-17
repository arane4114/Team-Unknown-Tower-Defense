package network;

/**
 * Sent by a client when it wants to send money to another user.
 * 
 * @author Abhishek
 *
 */
public class SendMoneyCommand extends Command<TowerServer> {

	private String senderName;
	private Integer money;

	/**
	 * Public constructor for a Send Money Command.
	 * 
	 * @param money
	 *            The amount of money to be sent.
	 * @param senderName
	 *            The user name of the sender.
	 */
	public SendMoneyCommand(Integer money, String senderName) {
		this.senderName = senderName;
		this.money = money;
	}

	/**
	 * Tells the server to process this command.
	 */
	@Override
	public void execute(TowerServer executeOn) {
		executeOn.sendMoney(senderName, money);
	}

}
