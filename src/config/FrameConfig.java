package config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class FrameConfig {
	
	private final String title;
	
	private final int windowUp;
	
	private final int width;
	
	private final int height;
	
	private final int padding;
	
	private final int border;
	
	private final int sizeRol;
	
	private final int loseIdx;
	

	/**
	 * 图层属性
	 */
	private List<LayConfig> laysConfig;
	/**
	 * 按钮属性
	 */
	private final ButtonConfig buttonConfig;
	
	public FrameConfig(Element frame){
		//获取窗口宽度
		this.width=Integer.parseInt(frame.attributeValue("width"));
		//获取窗口高度
		this.height=Integer.parseInt(frame.attributeValue("height"));
		//获取边框粗细
		this.border=Integer.parseInt(frame.attributeValue("border"));
		//获取边框内边距
		this.padding=Integer.parseInt(frame.attributeValue("padding"));
		//获取标题
		this.title=frame.attributeValue("title");
		//图块尺寸获取窗口拔高
		this.windowUp=Integer.parseInt(frame.attributeValue("windowUp"));
		//左位移偏移量
		this.sizeRol=Integer.parseInt(frame.attributeValue("sizeRol"));
		//游戏失败图片
		this.loseIdx=Integer.parseInt(frame.attributeValue("loseIdx"));
		
		//获取窗体属性
		@SuppressWarnings("unchecked")
		List<Element> lays=frame.elements("lay");
		laysConfig=new ArrayList<LayConfig>();
		for (Element lay : lays){
			LayConfig lc=new LayConfig(
					lay.attributeValue("className"),
					Integer.parseInt(lay.attributeValue("x")),
					Integer.parseInt(lay.attributeValue("y")),
					Integer.parseInt(lay.attributeValue("w")),
					Integer.parseInt(lay.attributeValue("h"))
					);
			laysConfig.add(lc);
		}
		//初始化按钮属性
		buttonConfig=new ButtonConfig(frame.element("button"));
	}

	/**
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return windowUp
	 */
	public int getWindowUp() {
		return windowUp;
	}

	/**
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return padding
	 */
	public int getPadding() {
		return padding;
	}

	/**
	 * @return border
	 */
	public int getBorder() {
		return border;
	}

	/**
	 * @return laysConfig
	 */
	public List<LayConfig> getLaysConfig() {
		return laysConfig;
	}

	/**
	 * @return sizeRol
	 */
	public int getSizeRol() {
		return sizeRol;
	}

	/**
	 * @return loseIdx
	 */
	public int getLoseIdx() {
		return loseIdx;
	}

	/**
	 * @return buttonConfig
	 */
	public ButtonConfig getButtonConfig() {
		return buttonConfig;
	}
	
}
