package unittests.renderer;

import geometries.Sphere;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

/**
 * testing the depth
 */
public class Depth {




    public class DepthOfField {

        private Scene scene = new Scene.SceneBuilder("Test scene").build();

        @Test
        public void depth() {
            Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                    .setVPSize(150, 150).setVPDistance(1000);

            scene.geometries.add( //
                    new Sphere( new Point(-20, -10, -50),50) //
                            .setEmission(new Color(java.awt.Color.BLUE)) //
                            .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(new Double3(0.3))),
                    new Sphere( new Point(-20, -10, -50),30) //
                            .setEmission(new Color(java.awt.Color.RED)) //
                            .setMaterial(new Material().setKD(0.5).setkS(0.5).setnShininess(100)),
                    new Sphere( new Point(30, 70, -200),30) //
                            .setEmission(new Color(java.awt.Color.BLUE)) //
                            .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(new Double3(0.3))),
                    new Sphere( new Point(30, 70, -200),10) //
                            .setEmission(new Color(java.awt.Color.RED)) //
                            .setMaterial(new Material().setKD(0.5).setkS(0.5).setnShininess(100)));
            scene.getLights().add( //
                    new SpotLight(new Color(1000, 600, 0),new Point(-100, -100, 550) , new Vector(-1, -1, -2)) //
                            .setkQ(0.0004).setkQ(0.0000006));

            ImageWriter imageWriter=new ImageWriter("depth", 500, 500);
            camera.setImageWriter(imageWriter) //
                    //.setCamera(camera) //
                    .setRayTracer(new RayTracerBasic(scene));//.setMultithreading(5);
            camera.renderImage();
            camera.writeToImage();
        }

    }
}
