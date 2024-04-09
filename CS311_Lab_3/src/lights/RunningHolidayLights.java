package lights;

import java.util.ArrayList;
import java.util.List;

public class RunningHolidayLights implements HolidayLights {
	
	private int length;
	private List<Light> lights;
	/**
	 * Creates new running holiday lights.
	 * @param length - length of this set of lights.
	 */
	public RunningHolidayLights(int length) {
		this.length = length;
		this.lights = new ArrayList<>();
		for ( int i=0; i< length; i++){
			lights.add(new Light());
		}
	}
	
	public List<Light> next() {
		for (int i = 0; i < length; i++){
			Light light = lights.get(i);
			if (!light.isOn()){
				light.setOn(true);
				return lights;
			}else{
				light.setOn(false);
			}
		}
		return lights;
	}
	
	/**
	 * Returns the length of this
	 * @return length of this
	 */
	public int getLength() {
		return length;
	}
						
}