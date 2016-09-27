package MaybeFinalProj;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	private boolean isPlayPressed = false;
	
	public boolean isPlayPressed() {
		return isPlayPressed;
	}

	public void setPlayPressed(boolean isPlayPressed) {
		this.isPlayPressed = isPlayPressed;
	}


	@Override
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		
		if ((mx >= 100) && (mx <= 100 + 80)) {
			
			//pressed play button
			if ((my >= 190) && (my <=240)) {
				isPlayPressed = true;
				Game.state = Game.STATE.GAME;
			}
			
			//pressed help button
			if ((my >= 260) && (my <=310)) {
				System.out.println("you clicked on the help button");
			}
			
			//pressed the quit button
			if ((my >330) &&(my <= 380)) {
				System.out.println("you clicked on the quit button");
				System.exit(1);
			}
		}		
	}
	
	

	@Override
	public void mouseClicked(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
