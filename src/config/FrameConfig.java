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
	 * ͼ������
	 */
	private List<LayConfig> laysConfig;
	/**
	 * ��ť����
	 */
	private final ButtonConfig buttonConfig;
	
	public FrameConfig(Element frame){
		//��ȡ���ڿ��
		this.width=Integer.parseInt(frame.attributeValue("width"));
		//��ȡ���ڸ߶�
		this.height=Integer.parseInt(frame.attributeValue("height"));
		//��ȡ�߿��ϸ
		this.border=Integer.parseInt(frame.attributeValue("border"));
		//��ȡ�߿��ڱ߾�
		this.padding=Integer.parseInt(frame.attributeValue("padding"));
		//��ȡ����
		this.title=frame.attributeValue("title");
		//ͼ��ߴ��ȡ���ڰθ�
		this.windowUp=Integer.parseInt(frame.attributeValue("windowUp"));
		//��λ��ƫ����
		this.sizeRol=Integer.parseInt(frame.attributeValue("sizeRol"));
		//��Ϸʧ��ͼƬ
		this.loseIdx=Integer.parseInt(frame.attributeValue("loseIdx"));
		
		//��ȡ��������
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
		//��ʼ����ť����
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
