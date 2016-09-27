package MaybeFinalProj;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

//fix double buffering

public class Sushi {

	private SushiBall ball;
	private double x;
	private double y;
	
	public Sushi(SushiBall b) {
		this.ball = b;
	}

	public Sushi(SushiBall b, int n) {
		this.ball = b;
		this.image = sushiTypes[n];
	}
	
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image image1 = toolkit.getImage("../src/MaybeFinalProj/sushi1.png");
	Image image2 = toolkit.getImage("../src/MaybeFinalProj/sushi2.png");
	Image image3 = toolkit.getImage("../src/MaybeFinalProj/sushi3.png");
	Image image4 = toolkit.getImage("../src/MaybeFinalProj/sushi4.png");
	Image image5 = toolkit.getImage("../src/MaybeFinalProj/sushi5.png");

	Image[] sushiTypes = {image1, image2, image3, image4, image5};
	
	//sneaky instance variables 
	private int type;
	private Image image = image1;
	
	
//------------getters and setters------------------------
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public double getY() {
		return y;
	}


	public Image getRandomImage() {
		Random rand = new Random();			
		int n = rand.nextInt(sushiTypes.length);
		type = n; 
		//System.out.println(n + "=n");
		return sushiTypes[n]; 
	}

	public void move() {
		x = ball.getX() - ball.getRadius();
		y = ball.getY() - ball.getRadius();
	}
	
	public void draw (Graphics g) {

		if(ball.isBallOnScreen){
			g.drawImage(image, (int)x, (int)y, null); 
		} else {
			image = getRandomImage(); //not working!!!!!!
		}
	}
}

