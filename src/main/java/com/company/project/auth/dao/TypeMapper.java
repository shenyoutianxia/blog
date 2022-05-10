package com.company.project.auth.dao;

import com.company.project.auth.model.Blog;
import com.company.project.auth.model.Type;
import com.company.project.common.mapper.CrudMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TypeMapper extends CrudMapper<Type> {

    @Insert("insert into t_type values(null,#{typeName})")
    public void saveType(String typeName);

    //遍历所有types
    @Select("select * from t_type")
    public List<Type> getTypes();

    @Select("select * from t_type where id=#{id}")
    public Type getType(Long id);

    //分页查询使用JpaRepository类中的方法findAll()
    //public Page<Type> listType(Pageable pageable);

    @Update("update t_type set name = #{type.name} where id = #{id}")
    public void updateType(Long id, Type type);

    @Delete("delete from t_type where id = #{id}")
    public void deleteType(Long id);

    //在blog_type表中查询type的id所对应的blog实体id
    @Select("select blog_id from blog_type where type_id = #{type_id}")
    public Long selectBlogId(Long type_id);


    //在主页面显示type所对应的blog实体发表数量  返回blog数组
    @Select("select * from blog,blog_type where blog.id=blog_type.blog_id and blog_type.type_id=#{type_id}")
    public List<Blog> blog_typeNum(Long type_id);
}