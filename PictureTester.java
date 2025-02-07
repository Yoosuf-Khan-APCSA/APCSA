/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  /** Method to test testKeepOnlyBlue */
  public static void testKeepOnlyBlue()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.keepOnlyBlue();
    beach.explore();
  }
  /** Method to test test negate */
  public static void testNegate()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.negate();
    beach.explore();
  }
  /** Method to test test grayscale */
  public static void testGrayscale()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.grayscale();
    beach.explore();
  }
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("images/caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("images/temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("images/640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("images/swan.jpg");
    swan.explore();
    swan.edgeDetection(20);
    swan.explore();
  }
  /** Method to test pixelate */
  public static void testPixelate()
  {
    Picture swan = new Picture("images/swan.jpg");
    swan.explore();
    swan.pixelate(20);
    swan.explore();
  }
  /** Method to test blur */
  public static void testBlur()
  {
    Picture swan = new Picture("images/swan.jpg");
    swan.explore();
    swan.blur(11).explore();
    
  }
  /** Method to test swapLeftRight */
  public static void testSwapLeftRight()
  {
    Picture swan = new Picture("images/swan.jpg");
    swan.explore();
    swan.swapLeftRight().explore();
    
  }

  /** Method to test stairStep */
  public static void testStairStep()
  {
    Picture swan = new Picture("images/swan.jpg");
    swan.explore();
    swan.stairStep(1,1).explore();
    
  }
  /** Method to test liquify */
  public static void testLiquify()
  {
    Picture swan = new Picture("images/swan.jpg");
    swan.explore();
    swan.liquify(100).explore();
    
  }
  /** Method to test liquify */
  public static void testWavy()
  {
    Picture swan = new Picture("images/swan.jpg");
    swan.explore();
    swan.wavy(30).explore();
    
  }
  /** Method to test enhance */
  public static void testEnhance()
  {
    Picture swan = new Picture("images/water.jpg");
    swan.explore();
    swan.enhance(100).explore();
    
  }
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    //testZeroBlue();
    //testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    //testNegate();
    //testGrayscale();
    //testBlur();
    //testPixelate();
    //testEnhance();
    //testSwapLeftRight();
    //testStairStep();
    //testLiquify();
    testWavy();
    //testFixUnderwater();
    //testMirrorVertical();
    //testMirrorTemple();
    //testMirrorArms();
    //testMirrorGull();
    //testMirrorDiagonal();
    //testCollage();
    //testCopy();
    //testEdgeDetection();
    //testEdgeDetection2();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
  }
}