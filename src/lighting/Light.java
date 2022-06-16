package lighting;

import primitives.Color;

/**
 * a class of light
 * @author Mikhal Levi & Eliana Grajower
 */
abstract class Light {
    private Color intensity;

    /**
     * constructor
     * @param intensity color
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * getter
     * @return intensity color
     */
    public Color getIntensity() {
        return intensity;
    }

    
}
