package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.Map;

/**
 * This panel is used to show the information of a cell. It also handles
 * upgrading a tower.
 * 
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 *
 */
public class CellInfoPanel extends JPanel {
	private JTextArea text;
	private Map map;
	private JButton upgrade;
	private Point currentPoint;

	/**
	 * Updates the current panel
	 * 
	 * @param point
	 */
	public void setPoint(Point point) {
		if (map.isValid(point)) {
			this.currentPoint = point;
			if (map.isTower(point)) {
				text.setText(" " + map.getTower(point));
				this.upgrade.setEnabled(true);
			} else if (map.isPath(point)) {
				text.setText(" Enemes can go here!");
			} else {
				this.upgrade.setEnabled(false);
				text.setText(" Nothing to see here!");
			}
		}
	}

	/**
	 * Creates a cell info panel with a {@link Map} object that contains the
	 * points it is to provide information for.
	 * 
	 * @param map
	 *            {@link Map} object to request additional information.
	 */
	public CellInfoPanel(Map map) {
		this.map = map;
		this.upgrade = new JButton("Upgrade!");
		this.setPreferredSize(new Dimension(250, 275));
		this.text = new JTextArea();
		this.text.setPreferredSize(new Dimension(250, 175));
		this.text.setFont(new Font("Courier", Font.PLAIN, 15));
		this.upgrade.addActionListener(new upgradeListener());
		this.add(text, BorderLayout.NORTH);
		this.add(upgrade, BorderLayout.SOUTH);
		this.upgrade.setEnabled(false);
	}

	private class upgradeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (map.isTower(currentPoint)
					&& map.getPlayer().canBuy(
							map.getTower(currentPoint).getUpgradeCost())) {
				map.getPlayer()
						.buy(map.getTower(currentPoint).getUpgradeCost());
				map.getTower(currentPoint).levelUp();
				text.setText(" " + map.getTower(currentPoint));
			}
		}
	}

}
