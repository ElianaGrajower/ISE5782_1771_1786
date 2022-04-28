package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

public class Scene {
   public String name;
  public  Color background=Color.BLACK;
   public AmbientLight ambientLight=new AmbientLight();
   public Geometries geometries=new Geometries();
   public Scene(String myname)
   {
       name=myname;
       Geometries model3D =new Geometries();
   }

}
