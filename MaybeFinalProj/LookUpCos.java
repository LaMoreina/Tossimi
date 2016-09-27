package MaybeFinalProj;

public class LookUpCos {
	
	public static float[] cosA = new float[360]; 
	
	public static void fillTable() {
		
		for (int i = 0; i<360; i++) {
			cosA[i] = (float) (15.0 * Math.cos(Math.toRadians(i))); 
			//input manually and not initialize with for loop?
		}
	}
	
	public static float getCosA(int x) {
		
		x = x%360;
		return cosA[x];
	}
}


