package view;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class PlayerInfoPanel extends JPanel implements Observer{

	public PlayerInfoPanel(){
		
		this.setPreferredSize(new Dimension(250, 250));
		
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}


