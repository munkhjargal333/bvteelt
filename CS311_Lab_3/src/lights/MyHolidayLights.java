package lights;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Color;

public class MyHolidayLights implements HolidayLights {
    
    private int length;
    private List<Light> lights;

    public MyHolidayLights(int length) {
        this.length = length;
        this.lights = new ArrayList<>();
        Random rand = new Random();
        // ongo oruulah
        for (int i = 0; i < length; i++) {
            int red = rand.nextInt(256);
            int green = rand.nextInt(256);
            int blue = rand.nextInt(256);
            lights.add(new ColoredLight(new Color(red, green, blue)));
        }
    }

    //random change
    public List<Light> next() {
        Random rand = new Random();
        int index = rand.nextInt(length);
        Light light = lights.get(index);
        light.setOn(!light.isOn());
        return lights;
    }

    // 2r talaas ni asaah
    public List<Light> sequence() {
        int i = 0;
        int pointer = 1;
        while (i < length) {
            Light light1 = lights.get(i % length);
            Light light2 = lights.get((length - (i % length) - 1) % length);
            boolean isOddIteration = (i / length) % 2 != 0;

            if (i / length == pointer) {
                isOddIteration = true;
                pointer += 2; 
            }

            if (!light1.isOn() && !light2.isOn() && !isOddIteration) {
                light1.setOn(true);
                light2.setOn(true);
                return lights;
            }

            if (light1.isOn() && light2.isOn() && isOddIteration) {
                light1.setOn(false);
                light2.setOn(false);
                return lights;
            }

            i++;
        }
        return lights;
    }

    public int getLength() {
        return length;
    }
}
