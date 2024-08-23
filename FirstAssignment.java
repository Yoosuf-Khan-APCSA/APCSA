/**
 *	FirstAssignment.java
 *	Display a brief description of your summer vacation on the screen.
 *
 *	To compile Linux:	javac -cp .:mvAcm.jar FirstAssignment.java
 *	To execute Linux:	java -cp .:mvAcm.jar FirstAssignment
 *
 *	To compile MS Powershell:	javac -cp ".;mvAcm.jar" FirstAssignment.java
 *	To execute MS Powershell:	java -cp ".;mvAcm.jar" FirstAssignment
 *
 *	@author	Your name
 *	@since	Today's date
 */
import java.awt.Font;

import acm.program.GraphicsProgram;
import acm.graphics.GLabel;

public class FirstAssignment extends GraphicsProgram {
    
    public void run() {
    	//	The font to be used
    	Font f = new Font("Serif", Font.BOLD, 18);
    	
    	//	Line 1
    	GLabel s1 = new GLabel("What I did on my summer vacation ...", 10, 20);
    	s1.setFont(f);
    	add(s1);
    	GLabel s2 = new GLabel("I traveled with my family to india to see my relatives. We travelled on Singapore Airlines.", 10, 40);
    	s2.setFont(f);
    	add(s2);
    	GLabel s3 = new GLabel("We travelled to my grandmothers flat. She lives in a city in South India. She usually lives", 10, 60);
    	s3.setFont(f);
    	add(s3);
    	GLabel s4 = new GLabel("With just my grandfather, However, this year my uncle visited with his family to her flat.", 10, 80);
    	s4.setFont(f);
    	add(s4);
    	GLabel s5 = new GLabel("He brought with him my two cousins. They have some fancy laptops. My older cousin gave me", 10, 100);
    	s5.setFont(f);
    	add(s5);
    	GLabel s6 = new GLabel("His lenovo earphones. In India we mostly stayed home. There was a milk shake store that we", 10, 120);
    	s6.setFont(f);
    	add(s6);
    	GLabel s7 = new GLabel("went to called the daily meet. They had 120 different options for flavours of milkshakes.", 10, 140);
    	s7.setFont(f);
    	add(s7);
    	GLabel s8 = new GLabel("Near the end of our trip to india we went to a rural village in a different indian state ", 10, 160);
    	s8.setFont(f);
    	add(s8);
    	GLabel s9 = new GLabel("in the mountains. There was a train there that we rode on. I loved putting my head out of", 10, 180);
    	s9.setFont(f);
    	add(s9);
    	GLabel s10 = new GLabel("The windows. I liked feeling the wind in my hair. We even recorded some videos outside of", 10, 200);
    	s10.setFont(f);
    	add(s10);
    	GLabel s11 = new GLabel("the window. ", 10, 220);
    	s11.setFont(f);
    	add(s11);
    	GLabel s12 = new GLabel("", 10, 240);
    	s12.setFont(f);
    	add(s12);
    	GLabel s13 = new GLabel("", 10, 260);
    	s13.setFont(f);
    	add(s13);
    	GLabel s14 = new GLabel("", 10, 280);
    	s14.setFont(f);
    	add(s14);
    	GLabel s15 = new GLabel("", 10, 300);
    	s15.setFont(f);
    	add(s15);
    	GLabel s16 = new GLabel("", 10, 320);
    	s16.setFont(f);
    	add(s16);
    	    	
    	//	Continue adding lines until you have 12 to 15 lines
    	
    }
    
}
