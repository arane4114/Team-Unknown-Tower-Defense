package view;

import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Allows a user to select the tower they want to use.
 * 
 * @author Abhishek Rane
 * @author Bryce Hammod
 * @author Sean Gallardo
 */
public class TowerSelectionPanel extends JPanel {

	private String currentString = "Tower One";

	private static String towerOne = "Tower One";
	private static String towerTwo = "Tower Two";
	private static String towerThree = "Tower Three";

	/**
	 * Creates the buttons select a tower.
	 */
	public TowerSelectionPanel() {

		this.setPreferredSize(new Dimension(250, 250));

		ButtonGroup group = new ButtonGroup();
		JPanel buttonPanel = new JPanel(new GridLayout(0, 3));

		JRadioButton towerOneButton = new JRadioButton(towerOne);
		towerOneButton.setActionCommand(towerOne);
		towerOneButton.setSelected(true);
		towerOneButton.addActionListener(new buttonListener());
		group.add(towerOneButton);
		buttonPanel.add(towerOneButton);

		JRadioButton towerTwoButton = new JRadioButton(towerTwo);
		towerTwoButton.setActionCommand(towerTwo);
		towerTwoButton.addActionListener(new buttonListener());
		group.add(towerTwoButton);
		buttonPanel.add(towerTwoButton);

		JRadioButton towerThreeButton = new JRadioButton(towerThree);
		towerThreeButton.setActionCommand(towerThree);
		towerThreeButton.addActionListener(new buttonListener());
		group.add(towerThreeButton);
		buttonPanel.add(towerThreeButton);
	}

	private class buttonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			currentString = e.getActionCommand();
		}
	}
}
