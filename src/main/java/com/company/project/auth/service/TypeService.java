package com.company.project.auth.service;
import com.company.project.auth.model.Blog;
import com.company.project.auth.model.Type;
import com.company.project.common.service.Service;

import java.util.List;


/**
 * Created with CodeGenerator
 * Description:
 * @author  LErry.li
 * Date: 2022年1月21日
 * Time: 下午1:38:22
 */
public interface TypeService extends Service<Type> {

    public void saveType(String typeName);

    public List<Type> getTypes();

    public Type getType(Long id);

    //分页查询采用 Mybatis-Plus插件
    //  public Page<Type> listType(Pageable pageable);

    public void updateType(Long id,Type type);

    public void deleteType(Long id);

    //在blog_type表中查询type的id所对应的blog实体id
    public Long selectBlogId(Long type_id);

    //在主页面显示type所对应的blog实体发表数量  返回blog数组
    public List<Blog> blog_typeNum(Long type_id);
}
