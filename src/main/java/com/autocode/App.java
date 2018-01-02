package com.autocode;

import com.autocode.domain.TableField;
import com.autocode.service.DataBaseAcessService;
import com.autocode.util.AutoCodeUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws Exception{
        ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
        DataBaseAcessService dataBaseAcessService = (DataBaseAcessService) ac.getBean("dataBaseAcessService");
        List<String> findAll = dataBaseAcessService.findAllMysql();
        for (int i = 0; i < findAll.size(); i++) {
            String name = findAll.get(i);
            System.out.println("name="+name);
          //  if("order".equals(name)){
                List<TableField> findDataById = dataBaseAcessService.findDataByIdMysql(name);
                AutoCodeUtil.autoCode(name,findDataById);
                System.err.println(findDataById);
                System.out.println("OK");
         //   }

        }
//        Map<String, Object> root = new HashMap<String, Object>();
//        root.put("packagename",  AutoCodeUtil.getProperties("packagename"));
//        root.put("author", AutoCodeUtil.getProperties("author"));
//        root.put("entrys",findAll);
//        AutoCodeUtil.createSpring(root);
    }
}
