package MaybeFinalProj;

import java.awt.Graphics;

public class BlockSaw {
	
	private int x = 200;
	private int y = 550;
	private int height = 50;
	private int width = 100;
	private int dx = 5;
	
	public boolean didCollide(SushiBall s) {
		int sushiRad = s.getRadius();
		double sushiX = s.getX();
		double sushiY = s.getY();
		
		if ((sushiX + sushiRad < x+width) && (sushiX + sushiRad > x) && (sushiY + sushiRad > y)) {
			return true;
		}
		
		return false;
	}
	
	public void draw(Graphics g) {
		g.fillRect(x, y, width, height); 
	}

	public void moveRight() {
		x += dx;	
	}
	public void moveLeft() {
		x -= dx;	
	}
}
