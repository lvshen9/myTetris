package config;

import org.dom4j.Element;

public class DataConfig {
	
	private final int maxRow;
	
	private final DataInterfaceConfig dataA;
	private final DataInterfaceConfig dataB;
	
	public DataConfig(Element data){
		this.maxRow=Integer.parseInt(data.attributeValue("maxRow"));
		this.dataA=new DataInterfaceConfig(data.element("dataA"));
		this.dataB=new DataInterfaceConfig(data.element("dataB"));
		
	}

	/**
	 * @return dataA
	 */
	public DataInterfaceConfig getDataA() {
		return dataA;
	}

	/**
	 * @return dataB
	 */
	public DataInterfaceConfig getDataB() {
		return dataB;
	}

	/**
	 * @return maxRow
	 */
	public int getMaxRow() {
		return maxRow;
	}
	
	
}
