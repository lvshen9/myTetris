package config;

import org.dom4j.Element;



public class ButtonConfig {
	private final int buttonW;
	
	private final int buttonH;
	
	private final int startX;
	
	private final int startY;
	
	private final int userConfigX;
	
	private final int userConfigY;
	
	public ButtonConfig(Element button){
		this.buttonW=Integer.parseInt(button.attributeValue("w"));
		this.buttonH=Integer.parseInt(button.attributeValue("h"));
		this.startX=Integer.parseInt(button.element("start").attributeValue("x"));
		this.startY=Integer.parseInt(button.element("start").attributeValue("y"));
		this.userConfigX=Integer.parseInt(button.element("userConfig").attributeValue("x"));
		this.userConfigY=Integer.parseInt(button.element("userConfig").attributeValue("y"));
		
	}

	/**
	 * @return buttonW
	 */
	public int getButtonW() {
		return buttonW;
	}

	/**
	 * @return buttonH
	 */
	public int getButtonH() {
		return buttonH;
	}

	/**
	 * @return startX
	 */
	public int getStartX() {
		return startX;
	}

	/**
	 * @return startY
	 */
	public int getStartY() {
		return startY;
	}

	/**
	 * @return userConfigX
	 */
	public int getUserConfigX() {
		return userConfigX;
	}

	/**
	 * @return userConfigY
	 */
	public int getUserConfigY() {
		return userConfigY;
	}
	
}
