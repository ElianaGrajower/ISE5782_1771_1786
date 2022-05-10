package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

public class Scene {
   public String name;
  public  Color background=Color.BLACK;
   public AmbientLight ambientLight=new AmbientLight();
   public Geometries geometries=new Geometries();

    /**
     * private default constructor for scene by the builder scene
     * @param builder scene
     */
    private Scene(SceneBuilder builder){
        name = builder.name;
        background = builder.background;
        ambientLight = builder.ambientLight;
        geometries = builder.geometries;
    }

    public Color getBackground() {
        return background;
    }

    public AmbientLight getAmbienLight() {
        return ambientLight;
    }

    /** was i supposed to add this??*/
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }


    /**build class for builder method*/
    public static class SceneBuilder {

        private final String name;
        private Color background = Color.BLACK;
        private AmbientLight ambientLight = new AmbientLight();
        private Geometries geometries = new Geometries();

        public SceneBuilder(String name){//constructor
            this.name = name;
        }

        //chaining methods
        public SceneBuilder setBackground(Color background) {
            this.background = background;
            return this;
        }

         public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
           this.ambientLight = ambientLight;
             return this;
        }

         public SceneBuilder setGeometries(Geometries geometries) {
           this.geometries = geometries;
           return this;
        }

          // build
         public Scene build(){
            Scene scene = new Scene(this);
            //....
             return scene;
                           }
    }

}
