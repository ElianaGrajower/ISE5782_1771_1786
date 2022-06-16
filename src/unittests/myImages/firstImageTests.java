package unittests.myImages;

import lighting.AmbientLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;
import static java.awt.Color.*;

/**
 * a first image
 * @author Mikhal Levi and Eliana Grajower
 */
public class firstImageTests {
    private Scene scene = new Scene.SceneBuilder("Test scene").build();

    /**
     * testing an image
     */
    @Test
    public void two() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150).setVPDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));

       scene.geometries.add(
                new Triangle(new Point(-150, -150, -5), new Point(150, -150, -5), new Point(75, 75, -5)) //
                        .setEmission(new Color(76,176, 184)) .setMaterial(new Material().setkS(0.8).setnShininess(60)), //
                new Triangle(new Point(-150, -150, -5), new Point(-80, 80, -5), new Point(75, 75, -5)) //
                        .setEmission(new Color(198, 136, 182)).setMaterial(new Material().setkS(0.8).setnShininess(60)), //
                new Sphere(new Point(-40, 0, -11), 10d) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKD(0.5).setkS(0.5).setnShininess(30)),
        new Sphere(new Point(30,25,40),12d).setEmission(new Color(147,122,29)).setMaterial(new Material().setKD(0.5).setkS(0.5).setnShininess(100)),
       new Tube(new Ray(new Point(-10, -20, -1),new Vector(1,3,5)),7 ).setEmission(new Color(227, 29, 162)).setMaterial(new Material().setKD(0.9).setkS(0.9).setnShininess(100))
       ); //
scene.setBackground(new Color(blue));
        scene.lights.add( //
                new SpotLight(new Color(700, 400, 400), new Point(40, 40, 115), new Vector(-1, -1, -4)) //
                        .setkL(4E-4).setkQ(2E-5));
       scene.lights.add( new SpotLight(new Color(700, 400, 400), new Point (20, 10, 155), new Vector(-1, -11, -4)) //
                .setkL(4E-4).setkQ(2E-5));

        camera.setImageWriter(new ImageWriter("triangle", 400, 400)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

}
