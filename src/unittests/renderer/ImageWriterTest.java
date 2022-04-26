package unittests.renderer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import primitives.Color;
import renderer.ImageWriter;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    @Test
    void testWriteToImage() {
        Color yellow = new Color(255d,255d,0d);
        Color red = new Color(255d,0d,0d);
        ImageWriter imageWriter = new ImageWriter("yellowSubmarine",800,500);
        //print red tiles on a yellow background
        for (int i = 0; i < 800; i++) {
            for (int j = 0; j < 500; j++) {
                if(i % 50 == 0 || j % 50 == 0) {
                    imageWriter.writePixel(i, j, red);
                }
                else {
                    imageWriter.writePixel(i,j,yellow);
                }
            }
        }
        imageWriter.writeToImage();
    }
}