package MaybeFinalProj;

import java.awt.Image;
import java.awt.Toolkit;

public class CatNinja extends Sprite {

	String pose[]= {"rt_run_", "left_run_", "left_face_", "rt_face_"};
	static Animation anim = new Animation();
	Image[] images = new Image[5];
	
	public CatNinja() {
		super(anim);
		//rt_face_
		
		for(int i = 0; i < images.length; i++){
			int j = 1;
			images[i] = Toolkit.getDefaultToolkit().getImage("../src/sprites/"+ pose[j] + i +".png");
			//images[i] = Toolkit.getDefaultToolkit().getImage("../src/sprites/rt_face_"+ i +".png");
			anim.addFrame(images[i], 10000);
		}
	}

	//static String[] pose = {"up","dn","lt","rt"};
	
	
}
