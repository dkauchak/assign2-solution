package ml.utils;

public class Test {
	public static void main(String[] args) {
		double[] t = new double[4];
		t[0] = 1;
		t[1] = 1.0;
		t[2] = Double.parseDouble("1");
		t[3] = Double.parseDouble("1.0");
		
		for( int i = 0; i < t.length; i++ ){
			for( int j = i+1; j < t.length; j++ ){
				System.out.println(t[i] == t[j]);
			}
		}
	}
}
