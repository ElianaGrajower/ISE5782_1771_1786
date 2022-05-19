package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

public class Scene {
   public String name;
   public Color background = Color.BLACK;
   public AmbientLight ambientLight = new AmbientLight();
   public Geometries geometries = new Geometries();
   public List<LightSource> lights = new LinkedList<>();

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
    /**
     * setter that returns a scene for a name
     * @param name
     * @return
     */
    public Scene setName(String name) {
        this.name = name;
        return this;
    }
    /**
     * setter that returns a scene for a background
     * @param background
     * @return
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }
    /**
     * setter that returns a scene for a ambientLight
     * @param ambientLight
     * @return
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }
    /**
     * setter that returns a scene for a geometry
     * @param geometries
     * @return
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * setter that returns a scene for a list of lights
     * @param lights
     * @return
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
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
