package lights;

import org.junit.Assert;
import org.junit.Test;
import java.awt.Color;

public class ColoredLightTest {

    @Test
    public void testInstruction() {
        Color initialColor = new Color(255, 0, 0); // Red
        ColoredLight cl = new ColoredLight(initialColor);

        Assert.assertFalse(cl.isOn());

        Assert.assertEquals(cl.getColor(), initialColor);
    }

    @Test
    public void testSetColor() {
        Color initialColor = new Color(255, 0, 0); // Red
        ColoredLight cl = new ColoredLight(initialColor);

        Color newColor = new Color(0, 255, 0); // Green
        cl.setColor(newColor);

        Assert.assertEquals(newColor, cl.getColor());
    }

    @Test 
    public void testRandomChange() {
        Color initialColor = new Color(255, 0, 0); // Red
        ColoredLight cl = new ColoredLight(initialColor);

        cl.randomChange();
        Assert.assertFalse(cl.getColor() == initialColor);
    }

}