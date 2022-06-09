package unittests.myImages;

import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import geometries.Tube;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.LightSource;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import static java.awt.Color.*;
import static java.awt.Color.black;
import static java.awt.Transparency.TRANSLUCENT;

public class houseAndSun {

private Scene scene = new Scene.SceneBuilder("Test scene").setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15))).build();
        @Test
        public void Two() {

            Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                    .setVPSize(150, 150).setVPDistance(1000); //

            scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));
                    scene.geometries.add(
                        new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                              new Point(-1500, -1500, -2000)).setEmission(new Color(231,203,224)), //

                        new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                              new Point(1500, 1500, -2000)).setEmission(new Color(231,203,224)),

                    new Triangle(new Point(37.5, -52, 0), new Point(-37.5, 22.5, 0),
                            new Point(-37.5, -52, 0)).
            setEmission(new Color(138,87,12)),
                    new Triangle(new Point(37.5, -52, 0), new Point(-37.5, 22.5, 0),
                            new Point(37.5, 25.5, 0)).
                            setEmission(new Color(138,87,12)),
                    new Triangle(new Point(-37.5, 22.5, 0),new Point(39, 22.5, 0),new Point(0,75,4)).setEmission(new Color(221,34,9)).setMaterial(new Material().setnShininess(50))
                            ,new Sphere(new Point(34,62,40),13).setEmission(new Color(247,170,17)).setMaterial(new Material().setKD(0.5).setkS(0.5).setnShininess(100))
            ,new Tube(new Ray(new Point(0,0,0),new Vector(1,3,5)),7 )
            .setEmission(new Color(227, 29, 162)).setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0.3))
                    );



            scene.lights.add( //
                    new SpotLight(new Color(WHITE),new Point(40, 40, 115), new Vector(-1, -1, -4)) //
                            .setkL(0.0004).setkQ(0.0000006));

            ImageWriter imageWriter = new ImageWriter("house", 1000, 1000);
           camera.setImageWriter(imageWriter) //
                    .setRayTracer(new RayTracerBasic(scene)) //
                    .renderImage() //
                    .writeToImage();
        }

    }

