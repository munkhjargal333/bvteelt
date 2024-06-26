package lights;

public class Light {
	
	// Гишүүн өгөгдөл
	private  boolean isOn;
	
	// Байгуулагч функц
	public Light() {
	 	this(false);
	}
	
	// Гишүүн функц
	/**
	//  * @param isOn 
	//  */
	public Light(boolean isOn) {
		this.isOn = isOn;
	}
	
	// Methods
	
	/**
	 * Returns true if this light is on, false otherwise
	 * @return true if this light is on, false otherwise
	 */
	public boolean isOn() {
		return isOn;
	}
	
	/**
	 * Sets whether this light is on. 
	 * @param isOn - state to change this light to. true turns
	 *               the light on. false turns it off
	 */
	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}
	
	/**
	 * Randomly changes this light to be on or off.
	 */
	public void randomChange() {
		if (Math.random() < .5) {
			this.setOn(true);
		} else {
			this.setOn(false);
		}
	}
	
}