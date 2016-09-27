package MaybeFinalProj;


public class LookUpSin {

	public static float[] sinA = new float[360]; 
	
	
	public static void fillTable() {
		
		for (int i = 0; i<360; i++) {
			sinA[i] = (float) (15.0 * Math.sin(Math.toRadians(i))); 
			//input manually and not initialize with for loop?
		}
	}
	
	public static float getSinA(int x) {
		
		x = x%360;
		return sinA[x];
	}
}


/*if(x <= 90) {
			System.out.println(x);
			return sinA[x];
		}
		else if (x <= 180) {
			System.out.println(x);
			System.out.println("sinA[90-x] = " + sinA[x-90]); 
			return sinA[x-90];
		}
		else if (x <= 270) {
			System.out.println(x);
			return -1*sinA[x-180];
		}
		else{
			System.out.println(x); 
			return -1* sinA[x-270];
		}		*/