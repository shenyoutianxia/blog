package com.company.project.auth.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

public class Blog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String appreciation;

    private String commentabled;

    private String content;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "first_picture")
    private String firstPicture;

    private String address;

    private String recommend;

    @Column(name = "share_statement")
    private String shareStatement;

    private String title;

    @Column(name = "update_time")
    private Date updateTime;

    private String views;

    private Long likes;

    private Long love;


    //@ManyToOne
    //@TableField(exist = false)
    private Type type;

    //@ManyToMany(cascade = {CascadeType.PERSIST})
   // @TableField(exist = false)
    private Tag tags;

    //@ManyToOne
   // @TableField(exist = false)
    private User user;

    //@OneToMany(mappedBy = "blog")
   // @TableField(exist = false)
    private List<Comment> comments;

    private static final long serialVersionUID = 1L;

    public Blog() {
    }
    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return appreciation
     */
    public String getAppreciation() {
        return appreciation;
    }

    /**
     * @param appreciation
     */
    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    /**
     * @return commentabled
     */
    public String getCommentabled() {
        return commentabled;
    }

    /**
     * @param commentabled
     */
    public void setCommentabled(String commentabled) {
        this.commentabled = commentabled;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return first_picture
     */
    public String getFirstPicture() {
        return firstPicture;
    }

    /**
     * @param firstPicture
     */
    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return recommend
     */
    public String getRecommend() {
        return recommend;
    }

    /**
     * @param recommend
     */
    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    /**
     * @return share_statement
     */
    public String getShareStatement() {
        return shareStatement;
    }

    /**
     * @param shareStatement
     */
    public void setShareStatement(String shareStatement) {
        this.shareStatement = shareStatement;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return views
     */
    public String getViews() {
        return views;
    }

    /**
     * @param views
     */
    public void setViews(String views) {
        this.views = views;
    }

    /**
     * @return likes
     */
    public Long getLikes() {
        return likes;
    }

    /**
     * @param likes
     */
    public void setLikes(Long likes) {
        this.likes = likes;
    }

    /**
     * @return love
     */
    public Long getLove() {
        return love;
    }

    /**
     * @param love
     */
    public void setLove(Long love) {
        this.love = love;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Tag getTags() {
        return tags;
    }

    public void setTags(Tag tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", appreciation=").append(appreciation);
        sb.append(", commentabled=").append(commentabled);
        sb.append(", content=").append(content);
        sb.append(", createTime=").append(createTime);
        sb.append(", firstPicture=").append(firstPicture);
        sb.append(", address=").append(address);
        sb.append(", recommend=").append(recommend);
        sb.append(", shareStatement=").append(shareStatement);
        sb.append(", title=").append(title);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", views=").append(views);
        sb.append(", likes=").append(likes);
        sb.append(", love=").append(love);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}