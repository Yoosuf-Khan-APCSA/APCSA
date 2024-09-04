/**
 *	USMap.java
 *	Display US cities and change radius based on its size
 *
 *	@author	Yoosuf Khan
 *	@since September 4, 2024
 */


public class USMap{
	 /**
	  *	runs program.
	  * @param fileName		name of the file to open
	  * @return 			the Scanner object to the file
	  */
       public static void main(String[] args) {
           StdDraw.setPenRadius(0.05);
           StdDraw.setPenColor(StdDraw.BLUE);
           StdDraw.point(0.5, 0.5);
           StdDraw.setPenColor(StdDraw.MAGENTA);
           StdDraw.line(0.2, 0.2, 0.8, 0.2);
           java.util.Scanner input = FileUtils.openToRead("cities.txt");
           
           double longitude[]=new double[276];
           double latitude[]=new double[276];
           for(int i=0; i<275; ){
				if(input.hasNextLine()){
					
					longitude[i]=input.nextDouble();
					latitude[i]=input.nextDouble();
					input.nextLine();
					System.out.println(longitude[i]+" "+latitude[i]);
				}
			}
			input.close();
			for(int i=0; i<275; ){
				System.out.println(i+" "+longitude[i]+" "+latitude[i]);
			}
				
       }
   }
		  
		  
