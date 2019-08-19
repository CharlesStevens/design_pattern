package bridge;

public class Caller {
	 
    public static void main(String[] args) {
        Shape tri = new Triangle(new GreenColor());
        tri.applyColor();
        
         
        Shape pent = new Pentagon(new GreenColor());
        pent.applyColor();
    }
 
}
