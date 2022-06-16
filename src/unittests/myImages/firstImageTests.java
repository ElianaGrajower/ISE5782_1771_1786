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
    @Test
    public void two() {
       // scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150).setVPDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));

       scene.geometries.add( //
//                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
//                        new Point(-1500, -1500, -2000)).setEmission(new Color(76,176, 184)) //
//                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0.3)),
//            new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
///                       new Point(1500, 1500, -2000)).setEmission(new Color(198, 136, 182))
//                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0.3)),
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
//        scene.geometries.add(
//                new Tube(new Ray(new Point(0,0,0),new Vector(1,3,5)),7 ).
//                        setEmission(new Color(12, 34, 87)).setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0.3)),
//                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
//                        new Point(-1500, -1500, -2000)).setEmission(new Color(76,176, 184)) //
//                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0.3)),
//        new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
//                        new Point(1500, 1500, -2000)).setEmission(new Color(198, 136, 182))
//                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0.3)),
//                 new Sphere(new Point(0,0,50),25d).setMaterial(new Material().setKD(0.5).setkS(0.5).setnShininess(30))
//        , new Sphere(new Point(30,-50,40),15d).setEmission(new Color(147,122,29)).setMaterial(new Material().setKD(0.5).setkS(0.5).setnShininess(100)),
//                new Sphere(new Point(0,-40,150),7d).setEmission(new Color(black)).setMaterial(new Material().setKD(0.5).setkS(0.5).setnShininess(100))
//       );
//        scene.lights.add( //
//                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2))
//                        .setkL(0.0004).setkQ(0.0000006));
//             scene.lights.add(  new SpotLight((new Color(700, 400, 400)), new Point(2, 30, 300), new Vector(40, -1, -4)) //
//            .setkL(4E-4).setkQ(2E-5));

        camera.setImageWriter(new ImageWriter("triangle", 400, 400)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

}
