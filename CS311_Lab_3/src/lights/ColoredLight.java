package lights;

import java.awt.Color;

public class ColoredLight extends Light {

    private Color color;

    /**
     * Creates a new colored light.
     *
     * @param color - color of this light.
     */
    public ColoredLight(Color color) {
        super();
        this.color = color;
    }

    /**
     * Returns the color of this light.
     *
     * @return color of this light.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Changes the color of this light to be c.
     */
    public void setColor(Color c) {
        color = c;
    }

    /**
     * Randomly changes this light to be on or off and its color.
     */
    @Override
    public void randomChange() {
        super.randomChange();
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);
        color = new Color(red, green, blue);
    }

}
