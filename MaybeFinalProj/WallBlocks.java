package MaybeFinalProj;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class WallBlocks {

	private int x;
	private int y;
	private int height;
	private int width;
	
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image image1 = toolkit.getImage("../src/MaybeFinalProj/sidebars.png");
	
	public WallBlocks(int x, int y, int height, int width) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	//edit this
	public int updateScore(SushiBall b, Sushi sush) {
		
		double yValue = b.getY();
		int sushType = sush.getType();
		
		//This can be reduced to fewer lines with || operator,
		//but I think this is more readable.
		if ((yValue > 120) && (sushType == 0)){
			return 5;
		}
		else if ((yValue > 240) && (sushType == 1)) {
			return 5;
		}
		else if ((yValue > 360) && (sushType == 2)) {
			return 5;
		}
		else if ((yValue > 480) && (sushType == 3)) {
			return 5;
		}
		else if ((yValue > 600) && (sushType == 4)) {
			return 5;
		}
		else {
			return 0;
		}
	}
	
	public boolean didBallCollide(SushiBall b) {
		
		double ballY = b.getY();
		double ballX = b.getX(); 
		int ballRadius = b.getRadius();
		
		if (x < 50) {
			if (ballX - ballRadius < x + width) { 
				//System.out.println("True on left side."); 
				return true;
			}
		}
		else if (x > 100) { 
			if (ballX + ballRadius > x) { 
				//System.out.println("True on right side."); 
				return true;
			}
		}
		return false;
	}
	
	public void draw (Graphics g) {
		g.fillRect(x, y, width, height); 
		//g.fillRect(800-35, 0, x, y);
		g.drawImage(image1, x, y, null);
	}

	
}
