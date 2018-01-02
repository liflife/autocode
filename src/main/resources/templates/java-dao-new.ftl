package ${packagename}.dao.${entry};

import java.util.List;
import ${packagename}.domain.${entry}.${entry?cap_first};
import org.springframework.stereotype.Repository;

/**
 * 
 * @author  <#if author??>${author}</#if>
 *
 */
@Repository
public interface ${entry?cap_first}Dao {

	public void insert${entry?cap_first}(${entry?cap_first} ${entry}) ;

	public void update${entry?cap_first}(${entry?cap_first} ${entry}) ;

	public void delete${entry?cap_first}(Integer ${entry}Id) ;

	public ${entry?cap_first} query${entry?cap_first}ById(Integer ${entry}Id);

	public List<${entry?cap_first}> queryAll${entry?cap_first}() ;








}
