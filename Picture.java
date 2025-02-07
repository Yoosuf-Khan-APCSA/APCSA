
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }

public 	Picture swapLeftRight()	 {
  Pixel[][] pixels = this.getPixels2D();
	Picture result = new Picture(pixels.length, pixels[0].length);
	Pixel[][] resultPixels = result.getPixels2D();

  for(int i=0; i<pixels[0].length;i++){
    for(int j=0; j<pixels.length;j++){
      resultPixels[j][(i+pixels[0].length/2)%pixels[0].length].setColor(pixels[j][i].getColor());
    }
  }
  return result;
}
 /* <Description here>
 * @param shiftCount The number of pixels to shift to the right
 * @param steps The number of steps
 * @return The picture with pixels shifted in stair steps
 */
 public Picture stairStep(int shiftCount, int steps){
  Pixel[][] pixels = this.getPixels2D();
	Picture result = new Picture(pixels.length, pixels[0].length);
	Pixel[][] resultPixels = result.getPixels2D();
  for(int i=0; i<pixels[0].length;i++){
    for(int j=0; j<pixels.length;j++){
      resultPixels[j][(i+(j/steps)*shiftCount)%pixels[0].length].setColor(pixels[j][i].getColor());
    }
  }
  return result;
 }
 /* <Description here>
 * @param maxFactor Max height (shift) of curve in pixels
 * @return Liquified picture
 */
 public Picture liquify(int maxHeight) {
  Pixel[][] pixels = this.getPixels2D();
	Picture result = new Picture(pixels.length, pixels[0].length);
	Pixel[][] resultPixels = result.getPixels2D();
  for(int i=0; i<pixels[0].length;i++){
    for(int j=0; j<pixels.length;j++){
      double exponent = Math.pow(j - pixels.length / 2.0, 2) / (2.0 * Math.pow(100, 2));
      int rightShift = (int)(maxHeight * Math.exp(- exponent));
      resultPixels[j][(i+rightShift)%pixels[0].length].setColor(pixels[j][i].getColor());
    }
  }
  return result;
 }
 /* <Description here>
 * @param amplitude The maximum shift of pixels
 * @return Wavy picture
 */
 public Picture wavy(int amplitude){
  Pixel[][] pixels = this.getPixels2D();
	Picture result = new Picture(pixels.length, pixels[0].length);
	Pixel[][] resultPixels = result.getPixels2D();
  for(int i=0; i<pixels[0].length;i++){
    for(int j=0; j<pixels.length;j++){
      int rightShift = (int)(amplitude*Math.sin(2*Math.PI*.01*j+2))%pixels[0].length;
      if (rightShift<0) rightShift+=pixels[0].length;
      resultPixels[j][(i+rightShift)%pixels[0].length].setColor(pixels[j][i].getColor());
    }
  }
  return result;
 }
  /** To average pixel area of size x size.
	* @param size Side length of square area to pixelate.
  * @param Pixels[][] array of pixels
  * @return Color of average
	*/
  public Color avgPixels(Pixel[][] pixels, int size,int x,int y) {
    int pixelAmt=0;
		int blueAvg=0,redAvg=0,greenAvg=0;
    for(int k = -size/2; k<size/2+1&&(y+k)<pixels.length-2;k++){
					for(int l = -size/2; l<size/2+1&&(x+l)<pixels[0].length-2;l++){
            
						if((k+y)<pixels.length-1&&(l+x)<pixels[0].length-1&&(k+y)>=0&&(l+x)>=0){
              try {
                  blueAvg+=pixels[y+k][x+l].getBlue();
                  blueAvg-=pixels[y+k][x+l].getBlue();
                }
                catch(Exception e) {
                  System.err.println((y+k)+" "+(x+l));
                  //System.err.println((k+y)<pixels[0].length-1);
                }
							blueAvg+=pixels[y+k][x+l].getBlue();
							redAvg+=pixels[y+k][x+l].getRed();
							greenAvg+=pixels[y+k][x+l].getGreen();
							pixelAmt++;
						}
					}
          //System.out.println("x:"+x+"y:"+y+"k:"+k+"Leng"+(pixels[0].length-1));
				}
        if(pixelAmt>0){
					blueAvg=blueAvg/pixelAmt;
					greenAvg=greenAvg/pixelAmt;
					redAvg=redAvg/pixelAmt;
        }
      return new Color(redAvg,greenAvg,blueAvg);
  }



  /** To pixelate by dividing area into size x size.
	* @param size Side length of square area to pixelate.
	*/
	public void pixelate(int size) {
		Pixel[][] pixels = this.getPixels2D();
		int pixelAmt=0;
		int blueAvg=0,redAvg=0,greenAvg=0;
		for(int i = 0; i<(pixels[0].length/size);i++){
			for(int j = 0; j<(pixels.length/size);j++){
				for(int k = 0; k<size;k++){
					for(int l = 0; l<size;l++){
						if((i*size+k)<pixels[0].length-1&&(j*size+l)<pixels.length-1){
							blueAvg+=pixels[j*size+l][i*size+k].getBlue();
							redAvg+=pixels[j*size+l][i*size+k].getRed();
							greenAvg+=pixels[j*size+l][i*size+k].getGreen();
							pixelAmt++;
						}
					}
				}
				if(pixelAmt>0){
					blueAvg=blueAvg/pixelAmt;
					greenAvg=greenAvg/pixelAmt;
					redAvg=redAvg/pixelAmt;
					for(int k = 0; k<size;k++){
						for(int l = 0; l<size;l++){
							if(i*size+k<pixels[0].length&&j*size+l<pixels.length){
								pixels[j*size+l][i*size+k].setGreen(greenAvg);
								pixels[j*size+l][i*size+k].setRed(redAvg);
								pixels[j*size+l][i*size+k].setBlue(blueAvg);
							}
						}
					}
				}
				pixelAmt=0;
				blueAvg=0;
				greenAvg=0;
				redAvg=0;
			}
		}
	}
	/** Method that blurs the picture
	* @param size Blur size, greater is more blur
	* @return Blurred picture
	*/
	public Picture blur(int size)
	{
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();
		int pixelAmt=0;
		for(int i = 0; i<(pixels[0].length);i++){
			for(int j = 0; j<(pixels.length);j++){
				if((i)<pixels[0].length-1&&(j)<pixels.length-1&&(i)>=0&&(j)>=0){
					resultPixels[j][i].setColor(avgPixels(pixels,size,i,j));
				}
			}
		}
		return result;
	}
	/** Method that enhances a picture by getting average Color around
	* a pixel then applies the following formula:
	*
	* pixelColor <- 2 * currentValue - averageValue
	*
	* size is the area to sample for blur.
	*
	* @param size Larger means more area to average around pixel
	* and longer compute time.
	* @return enhanced picture
	*/
	public Picture enhance(int size)
	{
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();
		int pixelAmt=0;
    Color avgColor;
		for(int i = 0; i<(pixels[0].length);i++){
			for(int j = 0; j<(pixels.length);j++){
				if((i)<pixels[0].length-1&&(j)<pixels.length-1&&(i)>=0&&(j)>=0){
          avgColor=avgPixels(pixels,size,i,j);
					resultPixels[j][i].setRed(2*pixels[j][i].getRed()-avgColor.getRed());
          resultPixels[j][i].setGreen(2*pixels[j][i].getGreen()-avgColor.getGreen());
          resultPixels[j][i].setBlue(2*pixels[j][i].getBlue()-avgColor.getBlue());
				}
			}
		}
		return result;
	}
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  /** Method to keep only the blue */
  public void keepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    }
  }
  
  /** Method to invert colors */
  public void negate()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(255-pixelObj.getRed());
        pixelObj.setGreen(255-pixelObj.getGreen());
        pixelObj.setBlue(255-pixelObj.getBlue());
      }
    }
  }
  /** Method to grayscale colors */
  public void grayscale()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
		  int avg =( pixelObj.getRed()+pixelObj.getBlue()+pixelObj.getGreen())/3;
        pixelObj.setRed(avg);
        pixelObj.setGreen(avg);
        pixelObj.setBlue(avg);
      }
    }
  }
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("images/flower1.jpg");
    Picture flower2 = new Picture("images/flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("images/collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
