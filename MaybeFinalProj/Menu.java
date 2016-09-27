package MaybeFinalProj;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

//with so much help from realTutsGML for this feature!

public class Menu {
	

	public Rectangle playButton = new Rectangle(100, 190, 80, 50);
	public Rectangle helpButton = new Rectangle(100, 260, 80, 50);
	public Rectangle quitButton = new Rectangle(100, 330, 80, 50);
	 
	//in order to get the image to fit the screen, I could not keep it
	//a part of the menu class.  I tried passing in the Game as a parameter, but :(
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image backgroundImage = toolkit.getImage("../src/images/sushi_cat1.png");
	 
	public void render(Graphics g) {
		
		g.drawImage(backgroundImage, 0, 0, null);
		
		Graphics2D g2d = (Graphics2D)g;
		
		Font f0 = new Font("arial", Font.BOLD, 50);
		g.setFont(f0);
		g.setColor(Color.BLACK); 
		g.drawString("Tossimi", 300, 400 ); //Game.WIDTH/2, Game.HEIGHT/3
		
		Font f1 = new Font("arial", Font.BOLD, 30);
		g.setFont(f1);
		
		g.drawString("Play", playButton.x, playButton.y + 30);
		g2d.draw(playButton); 
		
		g.drawString("Help", helpButton.x, helpButton.y + 30);
		g2d.draw(helpButton); 
		
		g.drawString("Quit", quitButton.x, quitButton.y + 30);
		g2d.draw(quitButton); 
	}
}
