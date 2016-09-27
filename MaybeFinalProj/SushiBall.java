package MaybeFinalProj;

import java.awt.Graphics;
import java.util.Random;


public class SushiBall {

	public SushiBall(double x, double y) {
		this.x = x;
		this.y = y;
	}

	private int radius = 20;
	private double x;
	private double y;
	
	//vid tutorial
	private double gravity = 15;
	private double energyLoss = .7;
	private double xFriction = .9;
	private double dt = .2;
	private double dx = 0;
	private double dy = 5;
	
	Random rand = new Random();
	
	//my attempt at motion from vectors
	private double intialVelocity = 20;
	private double angle = (Math.PI *45)/180;
	private double vector_dy = intialVelocity * LookUpSin.getSinA((int)angle);
	private double vector_dx = intialVelocity * LookUpCos.getCosA((int)angle);

	//game physics variables
	private double ay = 13;
	private double ax = 1;

//-----------------------------------
	boolean isBallOnScreen = true;
//-----------------------------------
	
//-------getters and setters---
	public boolean isBallOnScreen() {
		return isBallOnScreen;
	}

	public void setBallOnScreen(boolean isBallOnScreen) {
		this.isBallOnScreen = isBallOnScreen;
	}
	
	public void setEnergyLoss(double energyLoss) {
		this.energyLoss = energyLoss; 
	}
	
	public int getRadius() {
		return radius;
	}


	public void setRadius(int radius) {
		this.radius = radius;
	}


	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(double y) {
		this.y = y;
	}
	
	public double getDx() {
		return dx;
	}


	public void setDx(double dx) {
		this.dx = dx;
	}


	public double getDy() {
		return dy;
	}


	public void setDy(double dy) {
		this.dy = dy;
	}
//----------
	public void stopMoving() {
		dy = 0;
		dx = 0;
	}
	
	//move but without energy loss
	public void moveWithBounce() {
		if(x + dx > 800-radius-1) { //to the right of the screen
			x = 800 - radius - 1;
			dx = -dx;
		}
		else if (x + dx < radius) { //to the left of the screen
			x = radius;
			dx = -dx;
		}
		else {
			x += dx;
		}
		
		if (y == 600-radius-1) {
			dx *= xFriction;
			if (Math.abs(dx) < .8) {
				dx = 0;
			}
		}
		
		if (y > 600 - radius -1) { //ball at bottom of screen change here for seesaw variables
			y = 600 - 2*radius -1;
			dy *= 1.15; 
			dy = -dy;
		}
		else {
			//velocity formula
			dy += gravity*dt*2;
			//position formula
			y += dy*dt + .5*gravity*dt*dt;
			
		}
	}
	
	public void moveWithGravitas() {
		if(x + dx > 800-radius-1) { //to the right of the screen
			//x = 800 - radius - 1;
			//dx = -dx;
			isBallOnScreen = false;
		}
		else if (x + dx < radius) { //to the left of the screen
			//x = radius;
			//dx = -dx;
			isBallOnScreen = false;
		}
		else {
			x += dx;
		}
		
		if (y > 600 - radius -1) { //ball at bottom of screen
			y = 600 - 2*radius -1;
			dy *= energyLoss; //restitution? 
			dy = -dy;
		}
		else {
			//velocity formula
			dy += gravity*dt;
			//position formula
			y += dy*dt + .5*gravity*dt*dt;
			
		}
	}
	
	
	public void draw(Graphics g) { 
		//from physics attempts:
		//g.fillOval(Math.abs(800-(int)x), (int)y, radius*2, radius*2); //works better when x = 800
		//g.fillOval((int)x, Math.abs(800-(int)y), radius*2, radius*2);
		
		if (isBallOnScreen) {
			g.fillOval((int)x, (int)y, radius*2, radius*2);
			//System.out.println("ball is still on the screen");
		} else {
			//should i generate random sushi image here??  doesn't seem right
			int  n = rand.nextInt(400) + 200;
			this.x = n;
			System.out.println(x + "=x");
			this.y = 1;
			this.gravity = 15;
			this.energyLoss = .7;
			this.dx = 0;
			this.dy = 5;
			this.isBallOnScreen = true;
		}
	}
	
	
	
	
	
//-----this is a section of abandoned code to save for an explanation-----
	
	
	public void move(){
		
		accelerate();
		//applyGravitas();
		//getVectorVelocities();
		
		//move position by velocity
		x += vector_dx;
		y += vector_dy;
	}
	
	public void getVectorVelocities(){
		vector_dx = intialVelocity * LookUpCos.getCosA((int)angle);
		vector_dy = intialVelocity * LookUpSin.getSinA((int)angle);
	}
	
	//method from Murphy's pseudo code
	public void accelerate() {
		vector_dy += ay;
		vector_dx += ax;
	}
	
	public void applyGravitas(){
		vector_dy += gravity;
	}
	
}



/*
 * from older move method: 	public void move(){
		//first attempt:
		//x -=dx;
		//y = dy;
		//dy *= gravity;
		
		/*
		 * second attempt from physics equations and my head: 
		dx = intialVelocity * LookUpSin.getSinA((int)theta);
		dy = intialVelocity * LookUpSin.getSinA((int)theta) - gravity;
		
		x += dx;
		y += dy;
*/

