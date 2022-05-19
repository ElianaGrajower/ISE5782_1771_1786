package lighting;

import primitives.Color;

/**
 * @author Mikhal Levi & Eliana Grajower
 */
abstract class Light {
    private Color intensity;

    /**
     * constructor
     * @param intensity
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * getter
     * @return
     */
    public Color getIntensity() {
        return intensity;
    }

    
}
