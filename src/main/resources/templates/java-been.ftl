package ${packagename}.domain.${entry};

import java.util.List;

/**
 * 
 * @author <#if author??>${author}</#if>
 *
 */
public class ${entry?cap_first}{
	<#list attributesEntry as attribute>
	private  ${attribute.type} ${attribute.name};
	</#list>
	
	
	<#list attributesEntry as attribute>
	public  ${attribute.type} get${attribute.name?cap_first}(){
		return ${attribute.name};
	};
	public void set${attribute.name?cap_first} (${attribute.type} ${attribute.name}) {
		this.${attribute.name} = ${attribute.name};
	}
	</#list>
}
