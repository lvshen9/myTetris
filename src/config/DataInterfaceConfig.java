package config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

public class DataInterfaceConfig {
	private final String className;
	
	private final Map<String,String> param; 
	
	public DataInterfaceConfig(Element dataInterfaceConfig){
		this.className=dataInterfaceConfig.attributeValue("className");
		
		this.param=new HashMap<String,String>();
		
		@SuppressWarnings("unchecked")
		List<Element> params=dataInterfaceConfig.elements("param");
		
		for (Element e : params) {
			this.param.put(	e.attributeValue("key"), e.attributeValue("value"));
		}
	}

	/**
	 * @return className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @return param
	 */
	public Map<String, String> getParam() {
		return param;
	}
	
}
