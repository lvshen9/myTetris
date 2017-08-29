package config;

public class LayConfig {
	private String className;
	
	private int x;
	private int y;
	private int w;
	private int h;
	
	
	
	public LayConfig() {
		super();
	}
	public LayConfig(String className, int x, int y, int w, int h) {
		super();
		this.className = className;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	/**
	 * @return className
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * @return x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @return y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @return w
	 */
	public int getW() {
		return w;
	}
	/**
	 * @return h
	 */
	public int getH() {
		return h;
	}
	
	
}
