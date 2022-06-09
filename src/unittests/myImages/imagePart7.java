package unittests.myImages;

import geometries.Cylinder;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import static java.awt.Color.*;

public class imagePart7 {
    private Scene scene = new Scene.SceneBuilder("House scene").build();
    @Test
    public void housePicture(){
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(25, 25).setVPDistance(1000);
        Point A = new Point(2.11146,-4.56733,0);
        Point B = new Point(1.33949,-8.42337,0);
        Point C = new Point(1.1069,-1.29055,5.64588);
        Point D = new Point(2.01504,0.95004,3.26697);
        Point E = new Point(-5.22038,-4.48057,-0.17177);
        Point F = new Point(-6.48055,-8.42337,0);
        Point G = new Point(-5.19457,0.96882,2.9306);
        Point H = new Point(-6.45236,-0.9308,4.45919);
        Point I = new Point(-2,4,8.77547);
        Point Jb = new Point(-25,-3,-7);
        Point Kb = new Point(25,-3,-7);
        Point Jg = new Point(-25,-3,-7);
        Point Kg = new Point(25,-3,-7);
        Point L = new Point(25,-25,0);
        Point M = new Point(-25,-25,0);
        Point N = new Point(-25,25,0);
        Point O = new Point(25,25,0);
        Color grass = new Color(39,167,29);
        Color sky = new Color(103,239,250);




        scene.geometries.add(

                //building
                new Triangle(A,B,C).setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(A,C,D).setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(A,D,E).setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(D,E,G).setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(C,D,G).setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(C,G,H).setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(G,H,E).setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(F,H,E).setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(A,B,E).setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(F,E,B).setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),

                //roof
                new Triangle(H,G,I).setEmission(new Color(RED))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(C,D,I).setEmission(new Color(RED))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(H,C,I).setEmission(new Color(RED))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),
                new Triangle(D,G,I).setEmission(new Color(RED))
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0)),

                //grass
                new Triangle(M,Kg,L).setEmission(grass)
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0.3)),
                new Triangle(Kg,Jg,M).setEmission(grass)
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0.3)),

                //sky
                new Triangle(Jb,N,Kb).setEmission(sky)
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0.3)),
                new Triangle(N,O,Kb).setEmission(sky)
                        .setMaterial(new Material().setKD(0.4).setkS(0.3).setnShininess(100).setKt(0.3)),

                //sun
                new Sphere(new Point(6.59,8.44,0),2).setEmission(new Color(YELLOW))
                        .setMaterial(new Material().setKD(0.2).setkS(0.2).setnShininess(30).setKt(0.6))


                );
        scene.lights.add(new SpotLight(new Color(YELLOW/*800,500,200*/), new Point(6.59,8.44, 25),
                new Vector(-7.46,-11.58,0)));

//        scene.lights.add(new DirectionalLight(new Color(800,500,0), new Vector(5.59,7.44,10)));
               // .setkL(4E-4).setkQ(2E-5));
//        new SpotLight(new Color(800, 500, 0), new Point(6.59,8.44,0),
//                new Vector(-9.95,-11.61,0)));//.setkL(0.0004).setkQ(0.0000006));


        camera.setImageWriter(new ImageWriter("House image", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

}
