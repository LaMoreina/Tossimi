package MaybeFinalProj;

import java.awt.Color;
import java.awt.Graphics;
/*rotate in object space and then translate into world space...definitely know for final*/
//not sure if x and y instance vars are necessary
//put collision detection on edge of board so it doesn't go off the screen

public class SeeSaw {

	private int x = 0; 
	private int dx = 7;
	private int y = 565; 
	private double angle = 0;
	private boolean isEmpty = true;
	

	private double cos = Math.cos(angle);
    private double sin = Math.sin(angle);
	
    // Initial Coordinates of fulcrum
 	private int[] fulX = {-20, 20, 0, -20};
 	private int[] fulY = {20, 20, 0, 20};
 	
 	//Initial coordinates of board
 	private int[] boardX = {-135, 135, 135, -135}; //draw this in object space not world space
 	private int[] boardY = {0, 0, -20, -20};
 	
	private int boardMin = -135;
 	private int boardMax = 135;
 	
 	
	public SeeSaw(int x, int y) {
		this.x = x;
		this.y = y;
	} 
	
//-----------getters and setters----------------
 	public int getBoardMax() {
		return boardMax;
	}

	public void setBoardMax(int boardMax) {
		this.boardMax = boardMax;
	}
	
	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
//---------end of getters and setters--------------"
	
	public void moveFulcrumLeft() {
		if (x > 250) {
			x -= dx; 
		}
	}
	
	public void moveFulcrumRight() {
		if (x < 550) {
			x += dx;
		}
	}
	
	
	public void rotateLeft(double theta) {
		if (angle < .18) {
			angle += theta;
			System.out.println("angle=" + angle); 
			sin = Math.sin(angle);
			cos = Math.cos(angle);	
		}
	}

	public void rotateRight(double theta) {
		if (angle > -.18) {
			angle -= theta;
			System.out.println("angle=" + angle); 
			sin = Math.sin(angle);
			cos = Math.cos(angle);
		}
	}
	
	public void move() {
		x += dx;
	}
//-----rudimentary collision detection method---------
	
	public boolean ballCollision(SushiBall sushi) {
		
		boolean ycoll = false;
		boolean xcoll = false;
		
		double ballY = sushi.getY();
		double ballX = sushi.getX(); 
		int ballRadius = sushi.getRadius();
		double boardMiddle = 0;
		
		if ((ballX > boardMin+x) && (ballX < boardMax+x)) { 
			xcoll = true;
		}
		
		if ((ballY + ballRadius) >= 558)  {			
			ycoll = true;
		}
		
		if (ycoll && xcoll) {
			
			/*
			sushi.setX(boardMax);
			sushi.setDy(0);
			sushi.setDx(0);
			*/
			
			
			isEmpty = false;
			
			//once collision, determine the new dx and new angle
			boardMiddle = (boardMin + boardMax)/2;
			int val = Math.abs((int)(boardMiddle+x-ballX));
			
			if (ballX > boardMiddle+x) {
				rotateRight(15*Math.PI/180);
				//set new dx value 
				sushi.setDx(-5);
			}
			else if (ballX < boardMiddle+x) {
				rotateLeft(15*Math.PI/180);
				sushi.setDx(5);
			} 
			return true;
		}
		return false; 
	}
	
//-----end collision detection method---------		
	
	
	public void draw (Graphics g) {
		
		int[] pointX = new int[boardX.length];
		int[] pointY = new int[boardY.length];
		
		int[] fulPointX = new int[fulX.length];
		int[] fulPointY = new int[fulY.length];
        
		g.setColor(Color.BLUE); 
		
		for(int i = 0; i < boardX.length; i++) {
			pointX[i] = (int) (boardX[i]*cos + boardY[i]*sin) + x;
			pointY[i] = (int) (boardY[i]*cos - boardX[i]*sin) + y;
		}
		
		for(int i = 0; i < fulX.length; i++) {
			fulPointX[i] = fulX[i] + x;
			fulPointY[i] = fulY[i] + y;
		}
		
		g.drawPolygon(pointX, pointY, pointX.length);
		g.drawPolyline(fulPointX, fulPointY, fulPointX.length);
		
	}
}
