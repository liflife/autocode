package com.autocode.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.autocode.domain.TableField;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class AutoCodeUtil {
	public final static  String STRING="String";
	public final static  String INTEGER="Integer";
	public final static  String BIGDECIMAL="BigDecimal";
	public final static  String INT="int";
	static Properties properties;
	public static Configuration configuration;
	public static String path="templates/";
	public static String outpath="outFiles/";
	public static String outpath1="outFiles/";
	public static String jspPath="jsp";
	
	public static void main(String[] args) throws Exception {
		properties=new Properties();
		String name = AutoCodeUtil.class.getPackage().getName().replaceAll("\\.", "/");	
		File file = new File(name);
		
		String str=file.getAbsolutePath();
		
		String nam=str.substring(0,str.indexOf("\\com\\autocode\\util"));
		properties.load(new FileInputStream(new File(nam+"/src/autocode.properties")));
		

		/**
		loadTemplateConfiguration(path);
		String name="user";
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("packagename", getProperties("packagename"));
		root.put("author", getProperties("author"));
		root.put("entry", name);
		
		List attributesEntry=new ArrayList();
		Attribute attribute=new Attribute();
		attribute.setType(AutoCodeUtil.STRING);
		attribute.setName("username");
		attributesEntry.add(attribute);
		Attribute attribute1=new Attribute();
		attribute1.setType(AutoCodeUtil.STRING);
		attribute1.setName("password");
		attributesEntry.add(attribute1);
		
		
		
		root.put("attributesEntry", attributesEntry);
		createEntry(root);
		createAction(root);
		createService(root);
		createDao(root);
		createIbatis(root);
		createSpring(root);
		createStruts(root);
		**/
		
	}
	public static void autoCode(String name,List<TableField> entry) throws Exception{
		loadTemplateConfiguration(path);
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("packagename",  getProperties("packagename"));
		root.put("author", getProperties("author"));
		root.put("entry", name);
		List attributesEntry=new ArrayList();
		for (int i = 0; i < entry.size(); i++) {
			TableField tableField = entry.get(i);
			Attribute attribute=new Attribute();
			String type = tableField.getType();
			if(type.startsWith("varchar")){
				attribute.setType(AutoCodeUtil.STRING);
			}else if(type.startsWith("int")){
				attribute.setType(AutoCodeUtil.INTEGER);
			}else if(type.startsWith("bigint")){
				attribute.setType(AutoCodeUtil.INTEGER);
			}else if(type.startsWith("bigint")){
				attribute.setType(AutoCodeUtil.INTEGER);
			}else if(type.startsWith("decimal")){
				attribute.setType(AutoCodeUtil.BIGDECIMAL);
			}else{
				attribute.setType(AutoCodeUtil.STRING);
			}
			String field=tableField.getField();
//			if (field.indexOf("_")!=-1){
				String[] split = field.split("_");
				StringBuffer sb=new StringBuffer();
				for (int j=0;j<split.length;j++){
					if(j==0){
						sb.append(split[j]);
					}else{
						sb.append(split[j].substring(0,1).toUpperCase()+split[j].substring(1));
					}

				}
				attribute.setDbname(field);
				attribute.setName(sb.toString());
				attributesEntry.add(attribute);
//			}

		}
		root.put("attributesEntry", attributesEntry);
		createEntry(root);
		createController(root);
		createService(root);
		createDao(root);
		createIbatis(root);
//		createSpring(root);
//		createStruts(root);
//		createJsp(root);
	}
	
	public static void createEntry(Map<String, Object> root) throws Exception{		
		String entry=(String) root.get("entry");
		String packagename=(String) root.get("packagename");
		String createpath=outpath+"/src/"+packagename.replaceAll("\\.", "/")+"/domain/model/";
		createFolder(createpath);
		autoCreateJava("java-been.ftl", root, createpath+capFirst(entry)+".java");
	}

	public static void createController(Map<String, Object> root) throws Exception{
		String entry=(String) root.get("entry");
		String packagename=(String) root.get("packagename");
		String createpath=outpath+"/src/"+packagename.replaceAll("\\.", "//")+"/action/"+entry+"/";
		createFolder(createpath);
		//Action
		List attributes=new ArrayList();
		Attribute attribute0=new Attribute();
		attribute0.setType(entry+"Service");
		attribute0.setName(entry+"Service");
		attributes.add(attribute0);
		Attribute attribute1=new Attribute();
		attribute1.setType(entry);
		attribute1.setName(entry);
		attributes.add(attribute1);
		Attribute attribute2=new Attribute();
		attribute2.setType("List<"+entry.substring(0,1).toUpperCase()+entry.substring(1)+">");
		attribute2.setName(entry+"List");
		attributes.add(attribute2);

		root.put("attributesAction", attributes);
		autoCreateJava("java-controller-new.ftl", root, createpath+capFirst(entry)+"Action.java");

	}

	public static void createAction(Map<String, Object> root) throws Exception{
		String entry=(String) root.get("entry");
		String packagename=(String) root.get("packagename");
		String createpath=outpath+"/src/"+packagename.replaceAll("\\.", "//")+"/action/"+entry+"/";
		createFolder(createpath);
		//Action
		List attributes=new ArrayList();
			Attribute attribute0=new Attribute();
			attribute0.setType(entry+"Service");
			attribute0.setName(entry+"Service");
			attributes.add(attribute0);
			Attribute attribute1=new Attribute();
			attribute1.setType(entry);
			attribute1.setName(entry);
			attributes.add(attribute1);
			Attribute attribute2=new Attribute();
			attribute2.setType("List<"+entry.substring(0,1).toUpperCase()+entry.substring(1)+">");
			attribute2.setName(entry+"List");
			attributes.add(attribute2);
		
		root.put("attributesAction", attributes);
		autoCreateJava("java-action.ftl", root, createpath+capFirst(entry)+"Action.java");
		
	}
	public static void createService(Map<String, Object> root) throws Exception{
		String entry=(String) root.get("entry");
		String packagename=(String) root.get("packagename");
		String createpath=outpath+"/src/"+packagename.replaceAll("\\.", "/")+"/service/"+entry+"/";
		createFolder(createpath);
		//service
		autoCreateJava("java-service-new.ftl", root, createpath+capFirst(entry)+"Service.java");
		
		List attributes=new ArrayList();
		Attribute attribute=new Attribute();
		attribute.setType(entry+"Dao");
		attribute.setName(entry+"Dao");
		attributes.add(attribute);
		root.put("attributesService", attributes);
		createpath=outpath+"/src/"+packagename.replaceAll("\\.", "/")+"/service/"+entry+"/impl/";
		createFolder(createpath);
		autoCreateJava("java-serviceimpl-new.ftl", root, createpath+capFirst(entry)+"ServiceImpl.java");
	
	}
	public static void createDao(Map<String, Object> root) throws Exception{
		String entry=(String) root.get("entry");
		String packagename=(String) root.get("packagename");
		String createpath=outpath+"/src/"+packagename.replaceAll("\\.", "/")+"/dao/"+entry+"/";
		createFolder(createpath);
		//dao
		
		autoCreateJava("java-dao-new.ftl", root, createpath+capFirst(entry)+"Dao.java");
//		List attributes=new ArrayList();
//		root.put("attributes", attributes);
//		createpath=outpath+"/src/"+packagename.replaceAll("\\.", "/")+"/dao/"+entry+"/impl/";
//		createFolder(createpath);
//		autoCreateJava("java-daoimpl.ftl", root,createpath+ capFirst(entry)+"DaoImpl.java");
//
	}
	public static void createJsp(Map<String, Object> root) throws Exception{
		String entry=(String) root.get("entry");
		createFolder(jspPath+entry+"/");
		autoCreateJava("java-jsplist.ftl", root,jspPath+entry+"/"+entry+"List.jsp");
		autoCreateJava("java-jspadd.ftl", root,jspPath+entry+"/"+entry+"Add.jsp");
		autoCreateJava("java-jspupdate.ftl", root, jspPath+entry+"/"+entry+"Update.jsp");
	
	}
	public static void createIbatis(Map<String, Object> root) throws Exception{
		String entry=(String) root.get("entry");
		createFolder(outpath1+"/src/");
		autoCreateJava("java-ibatis-new.ftl", root, outpath1+"/src/"+entry+"Dao.xml");
	}
	public static void createSpring(Map<String, Object> root) throws Exception{
		String entry=(String) root.get("entry");
		createFolder(outpath1+"/src/");
		autoCreateJava("java-spring.ftl", root, outpath1+"/src/"+entry+"spring.xml");
	
	}
	public static void createSpringBeans(Map<String, Object> root) throws Exception{
		String entry=(String) root.get("entry");
		createFolder(outpath1+"/src/");
		autoCreateJava("java-springBeans.ftl", root, outpath1+"/src/Beans.xml");
	
	}

	public static void createStruts(Map<String, Object> root) throws Exception{
		String entry=(String) root.get("entry");
		createFolder(outpath1+"/src/");
		autoCreateJava("java-struts.ftl", root, outpath1+"/src/"+entry+"-struts.xml");
	
	}
	public static void createJava(Map<String, Object> root,String ftl,String ftlname,String outfile ) throws Exception{
		/* �?般在应用的整个生命周期中你仅�?要执行一下代码一�?*/
		/* 创建�?个合适的configuration */
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(ftl));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		Template temp = cfg.getTemplate(ftlname);
		temp.setEncoding("UTF-8");
		/*创建�?个数据模型Create a data model */

		/* 合并数据模型和模�?*/
		Writer out = new OutputStreamWriter(new FileOutputStream(new File(outfile+".java")));
		temp.process(root, out);
		out.flush();
		
	} 
	public static Configuration loadTemplateConfiguration(String path) throws IOException{
		String basePath = AutoCodeUtil.class.getResource("/").toString().substring(6);
//		String basePath = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
		String filePath=basePath+path;
		System.out.printf("filePath:"+filePath);
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(filePath));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		configuration=cfg;
		
		try {
			properties=new Properties();
			properties.load(new FileInputStream(new File(basePath+"/autocode.properties")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cfg;
	}
	public static void autoCreateJava(String ftlname,Map<String, Object> root,String outfile) throws Exception{
		Template temp = configuration.getTemplate(ftlname);
		temp.setEncoding("UTF-8");
		Writer out = new OutputStreamWriter(new FileOutputStream(new File(outfile)));
		temp.process(root, out);
		out.flush();
	}
	public static void save(String filename){
		
	}
	public static String capFirst(String str){
		str=str.substring(0,1).toUpperCase()+str.substring(1);
		return str;
		
	}
	public static String getProperties(String name){
		String str="";
		if(properties!=null){
			str= (String) properties.get(name);
		}else{
			System.out.println("加载资源文件失败！");
			System.exit(1);
			//throw new IllegalArgumentException("��Դ�ļ�δ���أ�");
			
		}
		return str;
	}
	public static void createFolder(String path){
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		
		
	}
}
