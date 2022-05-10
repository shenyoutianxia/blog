package com.company.project.auth.service;
import com.company.project.auth.model.Blog;
import com.company.project.auth.model.Tag;
import com.company.project.common.service.Service;

import java.util.List;


/**
 * Created with CodeGenerator
 * Description:
 * @author  LErry.li
 * Date: 2022年1月21日
 * Time: 下午1:38:22
 */
public interface TagService extends Service<Tag> {

    public void saveTag(String tagName);

    public List<Tag> getTags();

    public Tag getTag(Long id);

    //分页查询采用 Mybatis-Plus插件
    //  public Page<Tag> listTag(Pageable pageable);

    public void updateTag(Long id, Tag tag);

    public void deleteTag(Long id);

    //在blog_ags表中查询tag的id所对应的blog实体id
    public Long selectBlogId(Long tag_id);

    //在主页面显示tag所对应的blog实体发表数量  返回blog数组
    public List<Blog> blog_tagNum(Long tag_id);
}
