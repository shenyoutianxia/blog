package com.company.project.auth.dao;

import com.company.project.auth.model.Blog;
import com.company.project.auth.model.Tag;
import com.company.project.common.mapper.CrudMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TagMapper extends CrudMapper<Tag> {

    @Insert("insert into tag values(null,#{tagName})")
    public void saveTag(String typeName);

    //遍历所有tags
    @Select("select * from tag")
    public List<Tag> getTags();

    @Select("select * from tag where id=#{id}")
    public Tag getTag(Long id);

    //分页查询使用JpaRepository类中的方法findAll()
    //public Page<Tag> listTag(Pageable pageable);

    @Update("update tag set name = #{tag.name} where id = #{id}")
    public void updateTag(Long id, Tag tag);

    @Delete("delete from tag where id = #{id}")
    public void deleteTag(Long id);

    //在blog_tags表中查询tag的id所对应的blog实体id
    @Select("select blogs_id from blog_tags where tags_id = #{tag_id}")
    public Long selectBlogId(Long tag_id);

    //在主页面显示tag所对应的blog实体发表数量  返回blog数组
    @Select("select * from blog,blog_tags where blog.id=blog_tags.blogs_id and blog_tags.tags_id=#{tag_id}")
    public List<Blog> blog_tagNum(Long tag_id);
}