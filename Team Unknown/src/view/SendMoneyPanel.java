package view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Player;
import network.TowerClient;

public class SendMoneyPanel extends JPanel {
	private JTextField field;
	private Player player;
	private TowerClient client;

	public SendMoneyPanel(Player player, TowerClient client) {
		this.player = player;
		this.client = client;
		this.field = new JTextField("Amount to be sent");
		this.add(field);
		this.field.addActionListener(new moneyListener());
	}

	private class moneyListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
					int amount = Integer.parseInt(field.getText());
					if (amount >0 && player.canBuy(amount) && client.sendMoney(amount)) {
						player.buy(amount);
						System.out.println("Amount");
					}
			} catch (NumberFormatException event) {
			}
		}

	}
}
