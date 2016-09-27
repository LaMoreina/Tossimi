package MaybeFinalProj;


import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


/*
 * To do: plug in menu and shooting code
 * get sprites working!!!!!!
 * edit images
 * MOVEMENT LOGIC!
 * */

public class Game extends Applet implements Runnable, KeyListener {
	
	SushiBall sushiBall = new SushiBall(200, 0);
	Sushi sushi = new Sushi(sushiBall);
	
	SushiBall otherSushiBall = new SushiBall(200, -40);
	Sushi otherSushi = new Sushi(otherSushiBall, 4);
	
	SeeSaw seesaw = new SeeSaw(350, 565);
	WallBlocks cb_left = new WallBlocks(0, 0, 600, 35);
	WallBlocks cb_right = new WallBlocks(687, 0, 600, 35);
	
	public int gameScore = 0;
	
	public final int WIDTH = 800;
	public final int HEIGHT = 600;
	
	public boolean isPlayPressed = false;
	public boolean gameOver = false;
	
//----beginning menu code--------
	public static enum STATE {
		MENU,
		GAME, 
		GAMEOVER
	};
	public static STATE state = STATE.GAME; //MENU
	private Menu menu = new Menu();
//----end menu code---------------

	//sprite code
	String pose[]= {"rt_run", "left_run", "left_face", "rt_face"}; 
	CatNinja kitten = new CatNinja();
	
	
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image backgroundImage1 = toolkit.getImage("../src/images/sushi_cat1.png");
	Image backgroundImage2 = toolkit.getImage("../src/MaybeFinalProj/rice_background.jpg");

	
	//keyListener variables
	boolean isUpPressed = false;
	boolean isDownPressed = false;	
	boolean isLeftPressed = false;
	boolean isRightPressed = false;

	 
	AudioClip bounce, goal, startAndEnd;
	
	public void init(){
		setSize(WIDTH, HEIGHT);
		
		//sound files:
		bounce = getAudioClip (getCodeBase(), "music/bullet_whizzing.wav");
		goal = getAudioClip (getCodeBase(), "music/ball_bounce.au");
		startAndEnd = getAudioClip (getCodeBase(), "music/ball_bounce.au");
		
		LookUpSin.fillTable();
		LookUpCos.fillTable();
		
		requestFocus(); //what does this do?
		addKeyListener(this);
		
		this.addMouseListener(new MouseInput());//for game menu
		
	}
	
	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}
	
	
	@Override
	public void run() {

		if (state == STATE.GAME) {
			try {
				new SoundClip();
			} catch (IOException | UnsupportedAudioFileException
					| LineUnavailableException e1) {
				e1.printStackTrace();
			}

			while (!gameOver) {

				// ---------------------------------------------------
				kitten.anim.update(550);

				if (!sushiBall.isBallOnScreen()) {
					otherSushiBall.moveWithGravitas();
					otherSushi.move();
				}

				if (sushiBall.isBallOnScreen()) {
					sushiBall.moveWithGravitas();
					sushi.move();
				}

				if (seesaw.ballCollision(sushiBall)) {
					System.out.println("They have collided");
					// sushiBall.setDx(5);
					sushiBall.moveWithBounce();
					// sushi.setEnergyLoss(1.15);
					bounce.play();

					// sushi.stopMoving();
				}

				if (seesaw.ballCollision(otherSushiBall)) {
					System.out.println("They have collided");
					otherSushiBall.moveWithBounce();
					bounce.play();
				}

				// -----------move fulcrum----------------------

				if (isLeftPressed) {
					seesaw.moveFulcrumLeft();
				}
				if (isRightPressed) {
					seesaw.moveFulcrumRight();
				}

				// -------------update score------------------
				if (cb_left.didBallCollide(sushiBall)) {
					gameScore += cb_left.updateScore(sushiBall, sushi);
				}
				if (cb_right.didBallCollide(sushiBall)) {
					gameScore += cb_right.updateScore(sushiBall, sushi);
				}
				// --------------------------------------------

				repaint();

				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} // end of if state = game

			if (gameScore < -3) {
				gameOver = true;
				// startAndEnd.play();
				state = STATE.GAMEOVER;
			}
		} else if (state == STATE.MENU){
			
		}
	}

	public void update(Graphics g) {
		super.update(g); 
	}

	public void paint(Graphics g) {

		if (state == STATE.GAME) {
			g.drawImage(backgroundImage2, 0, 0, this.getWidth(),
					this.getHeight(), null);

			g.setColor(Color.RED);
			seesaw.draw(g);

			g.setColor(Color.GREEN);
			sushiBall.draw(g);
			sushi.draw(g);

			otherSushiBall.draw(g);
			otherSushi.draw(g);

			cb_left.draw(g);
			cb_right.draw(g);

			// this is for the score
			String s = Integer.toString(gameScore);
			Font f = new Font("Serif", Font.BOLD, 32);
			g.setFont(f);
			g.drawString("Score: " + s, this.getWidth() / 2 - 35, 31);
			g.setColor(Color.BLACK);
			g.drawString("Score: " + s, this.getWidth() / 2 - 35, 30);

			// draw the sprite
			g.drawImage(kitten.anim.getImage(), seesaw.getX() + 135, 550, null);
		}
		else {
			//Toolkit toolkit = Toolkit.getDefaultToolkit();
			//Image backgroundImage = toolkit.getImage("../src/images/sushi_cat1.png");
			//g.drawImage(backgroundImage, 0, 0, null);
			//menu.render(g);
		}

	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if (state == STATE.GAME) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				isUpPressed = true;
				break;
			case KeyEvent.VK_DOWN:
				isDownPressed = true;
				break;
			case KeyEvent.VK_LEFT:
				isLeftPressed = true;
				break;
			case KeyEvent.VK_RIGHT:
				isRightPressed = true;
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		switch(e.getKeyCode()) {
		
		case KeyEvent.VK_UP:
			isUpPressed = false;
			break;
		case KeyEvent.VK_DOWN:
			isDownPressed = false;
			break;
		case KeyEvent.VK_LEFT:
			isLeftPressed = false;
			break;
		case KeyEvent.VK_RIGHT:
			isRightPressed = false;
			break;
		}
	}
}


/*
 * 
 * while(true) {
//---------------------------------------------------			
			if(!seesaw.isEmpty()) {
				otherSushiBall.moveWithGravitas();
				otherSushi.move();
			}
			if ((sushiBall.isBallOnScreen) && (seesaw.isEmpty())) {
				sushiBall.moveWithGravitas();
				sushi.move();
			} else {
				sushi.getRandomImage(); //not working!
			}
			
			if (seesaw.ballCollision(sushiBall)) {
				System.out.println("They have collided");
				//sushiBall.setDx(5); 
				sushiBall.moveWithBounce();
				//sushi.setEnergyLoss(1.15); 
				bounce.play();
				
				//sushi.stopMoving();
			}
//---------------------------------------------------
 * 
 * 
 * 			if (otherSushiBall.isBallOnScreen()) {
				
				if (!seesaw.isEmpty()) { //if seesaw has sushi on it
					otherSushiBall.moveWithGravitas();
					otherSushi.move();
					
					if (seesaw.ballCollision(otherSushiBall)) {
						//sushiBall.setY(565);
						sushiBall.moveWithBounce();
						sushi.move();
						bounce.play();
						seesaw.setEmpty(true);
					}
				}
		
				if(seesaw.isEmpty()) {  //if seesaw does not have sushi on it
					
					if (seesaw.ballCollision(otherSushiBall)) {
						System.out.println("They have collided");
						//otherSushiBall.setDx(5); 
						otherSushiBall.moveWithBounce();
						//otherSushi.setEnergyLoss(1.15); 
						bounce.play();
						
						//sushi.stopMoving();
					}
				}
			}
			
			
			if (sushiBall.isBallOnScreen()) {
				
				if (seesaw.isEmpty()) {
					sushiBall.moveWithGravitas();
					sushi.move();
					
					if (seesaw.ballCollision(sushiBall)) {
						System.out.println("They have collided");
						//sushiBall.setDx(5); 
						sushiBall.moveWithBounce();
						//sushi.setEnergyLoss(1.15); 
						bounce.play();
						
						//sushi.stopMoving();
					}
				}
				
				if (!seesaw.isEmpty()) {
					sushiBall.moveWithGravitas();
					sushi.move();
					
					if (seesaw.ballCollision(sushiBall)) {
						System.out.println("They have collided");
						//sushiBall.setDx(5); 
						otherSushiBall.moveWithBounce();
						//sushi.setEnergyLoss(1.15); 
						bounce.play();
						seesaw.setEmpty(true);
						
						//sushi.stopMoving();
					}
				}
			} 
 * 
 * 
 * */
