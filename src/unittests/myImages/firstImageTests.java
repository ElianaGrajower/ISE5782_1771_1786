package unittests.myImages;

import lighting.SpotLight;
import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;
import static java.awt.Color.*;

public class firstImageTests {
    private Scene scene = new Scene.SceneBuilder("Test scene").build();
    @Test
    public void two() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150).setVPDistance(1000);

        scene.geometries.add( //
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(-1500, -1500, -2000)).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0.3)),
        new Triangle(new Point(15, -15, -15), new Point(-15, 15, -15),
                new Point(-15, -15, -20)).setEmission(new Color(white)) //
                .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0.3)),
                 new Sphere(new Point(0,0,50),25d).setEmission(new Color(RED)).setMaterial(new Material().setKD(0.5).setkS(0.5).setnShininess(100))
        , new Sphere(new Point(30,-50,40),15d).setEmission(new Color(GREEN)).setMaterial(new Material().setKD(0.5).setkS(0.5).setnShininess(100)),
                new Sphere(new Point(0,-40,150),7d).setEmission(new Color(black)).setMaterial(new Material().setKD(0.5).setkS(0.5).setnShininess(100)));
        scene.lights.add( //
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                        .setkL(0.0004).setkQ(0.0000006));

        camera.setImageWriter(new ImageWriter("triangle", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

}
