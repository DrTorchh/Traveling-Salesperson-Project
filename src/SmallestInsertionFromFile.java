import java.io.File;
import java.util.Scanner;

/* *****************************************************************************
 *  YOU DO NOT NEED TO MODIFY THIS FILE
 *
 *  Compilation:  javac SmallestInsertion.java
 *  Execution:    java SmallestInsertion < file.txt
 *  Dependencies: Tour.java Point.java StdIn.java StdDraw.java
 *
 *  Run smallest insertion heuristic for traveling salesperson problem
 *  and plot results.
 *
 *  Modified to Read from File Path
 *
 **************************************************************************** */

public class SmallestInsertionFromFile {

    public static void main(String[] args) {

    	try {
    		// Change path to test
    		String path = "tsp10.txt";
    		
    		File testInput = new File(path);
    		Scanner input = new Scanner(testInput);
    		
            // get dimensions
    		int width = input.nextInt();
    		int height = input.nextInt();
            int border = 20;
            StdDraw.setCanvasSize(width, height + border);
            StdDraw.setXscale(0, width);
            StdDraw.setYscale(-border, height);
	
	        // turn on double buffering
	        StdDraw.enableDoubleBuffering();
	
	        // run smallest insertion heuristic
	        Tour tour = new Tour();
	        while (input.hasNext()) {
	        	
	        	double x = input.nextDouble();
            	double y = input.nextDouble();

                Point p = new Point(x, y);
	            tour.insertSmallest(p);
	
	            // uncomment the 4 lines below to animate
	            StdDraw.clear();
	            tour.draw();
	            StdDraw.textLeft(20, 0, "length = " + tour.length());
	            StdDraw.show();
	            StdDraw.pause(50);
	        }
	
	        // draw to standard draw 
	        tour.draw();
	        StdDraw.show();
	        
	        // print tour to standard output
	        StdOut.println(tour);
	        StdOut.printf("Tour length = %.4f\n", tour.length());
	        StdOut.printf("Number of points = %d\n", tour.size());
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
    }

}
