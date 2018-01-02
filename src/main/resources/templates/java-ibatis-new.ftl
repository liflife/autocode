<?xml version="1.0" encoding="utf-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${entry}">

	<resultMap type="${packagename}.domain.${entry}.${entry?cap_first}" id="${entry}Result">
		<#list attributesEntry as attribute>
		<result property="${attribute.name}" column="${attribute.dbname}" javaType="${attribute.type}"/>
		</#list>
	</resultMap>
	<select id="queryAll${entry?cap_first}" resultMap="${entry}Result">
		select * from ${entry}
 	</select>

	<select id="query${entry?cap_first}ById" parameterType="int" resultMap="${entry}Result">
		select * from ${entry} where ${attributesEntry[0].name}=${r"#{"}${attributesEntry[0].name}${r"}"}
 	</select>

	<insert id="insert${entry?cap_first}" parameterType="user">
	 <![CDATA[
		insert into
		${entry}(
	<#list attributesEntry as attribute>
		${attribute.name},
	</#list>
		) values(
		<#list attributesEntry as attribute>
		${r"#{"}${attribute.name}${r"}"},
		</#list>
		)
		]]>
 	</insert>

	<update id="update${entry?cap_first}" parameterType="user">
		update ${entry} set
	<#list attributesEntry as attribute>
		${attribute.name}=${r"#{"}${attribute.name}${r"}"},
	</#list>
		where id=${r"#{"}${attributesEntry[0].name}${r"}"}
 	</update>

	<delete id="delete${entry?cap_first}" parameterType="int">
		delete from ${entry} where id=${r"#{"}${attributesEntry[0].name}${r"}"}
	</delete>

</mapper>
