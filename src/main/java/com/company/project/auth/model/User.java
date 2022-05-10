package com.company.project.auth.model;



import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;
    private String description;
    //@Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    //@Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    //@OneToMany(mappedBy = "user")
    //@TableField(exist = false)
    private List<Blog> blogs;

//    @Column(name = "Host")
//    private String host;
//
//    @Column(name = "User")
//    private String user;
//
//    @Column(name = "Select_priv")
//    private String selectPriv;
//
//    @Column(name = "Insert_priv")
//    private String insertPriv;
//
//    @Column(name = "Update_priv")
//    private String updatePriv;
//
//    @Column(name = "Delete_priv")
//    private String deletePriv;
//
//    @Column(name = "Create_priv")
//    private String createPriv;
//
//    @Column(name = "Drop_priv")
//    private String dropPriv;
//
//    @Column(name = "Reload_priv")
//    private String reloadPriv;
//
//    @Column(name = "Shutdown_priv")
//    private String shutdownPriv;
//
//    @Column(name = "Process_priv")
//    private String processPriv;
//
//    @Column(name = "File_priv")
//    private String filePriv;
//
//    @Column(name = "Grant_priv")
//    private String grantPriv;
//
//    @Column(name = "References_priv")
//    private String referencesPriv;
//
//    @Column(name = "Index_priv")
//    private String indexPriv;
//
//    @Column(name = "Alter_priv")
//    private String alterPriv;
//
//    @Column(name = "Show_db_priv")
//    private String showDbPriv;
//
//    @Column(name = "Super_priv")
//    private String superPriv;
//
//    @Column(name = "Create_tmp_table_priv")
//    private String createTmpTablePriv;
//
//    @Column(name = "Lock_tables_priv")
//    private String lockTablesPriv;
//
//    @Column(name = "Execute_priv")
//    private String executePriv;
//
//    @Column(name = "Repl_slave_priv")
//    private String replSlavePriv;
//
//    @Column(name = "Repl_client_priv")
//    private String replClientPriv;
//
//    @Column(name = "Create_view_priv")
//    private String createViewPriv;
//
//    @Column(name = "Show_view_priv")
//    private String showViewPriv;
//
//    @Column(name = "Create_routine_priv")
//    private String createRoutinePriv;
//
//    @Column(name = "Alter_routine_priv")
//    private String alterRoutinePriv;
//
//    @Column(name = "Create_user_priv")
//    private String createUserPriv;
//
//    @Column(name = "Event_priv")
//    private String eventPriv;
//
//    @Column(name = "Trigger_priv")
//    private String triggerPriv;
//
//    @Column(name = "Create_tablespace_priv")
//    private String createTablespacePriv;
//
//    @Column(name = "ssl_type")
//    private String sslType;
//
//    @Column(name = "max_questions")
//    private Integer maxQuestions;
//
//    @Column(name = "max_updates")
//    private Integer maxUpdates;
//
//    @Column(name = "max_connections")
//    private Integer maxConnections;
//
//    @Column(name = "max_user_connections")
//    private Integer maxUserConnections;
//
//    private String plugin;
//
//    @Column(name = "ssl_cipher")
//    private byte[] sslCipher;
//
//    @Column(name = "x509_issuer")
//    private byte[] x509Issuer;
//
//    @Column(name = "x509_subject")
//    private byte[] x509Subject;
//
//    @Column(name = "authentication_string")
//    private String authenticationString;

    private static final long serialVersionUID = 1L;

    public User() {
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    /**
//     * @return Host
//     */
//    public String getHost() {
//        return host;
//    }
//
//    /**
//     * @param host
//     */
//    public void setHost(String host) {
//        this.host = host;
//    }
//
//    /**
//     * @return User
//     */
//    public String getUser() {
//        return user;
//    }
//
//    /**
//     * @param user
//     */
//    public void setUser(String user) {
//        this.user = user;
//    }

    /**
     * @return Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return Select_priv
     */
//    public String getSelectPriv() {
//        return selectPriv;
//    }
//
//    /**
//     * @param selectPriv
//     */
//    public void setSelectPriv(String selectPriv) {
//        this.selectPriv = selectPriv;
//    }
//
//    /**
//     * @return Insert_priv
//     */
//    public String getInsertPriv() {
//        return insertPriv;
//    }
//
//    /**
//     * @param insertPriv
//     */
//    public void setInsertPriv(String insertPriv) {
//        this.insertPriv = insertPriv;
//    }
//
//    /**
//     * @return Update_priv
//     */
//    public String getUpdatePriv() {
//        return updatePriv;
//    }
//
//    /**
//     * @param updatePriv
//     */
//    public void setUpdatePriv(String updatePriv) {
//        this.updatePriv = updatePriv;
//    }
//
//    /**
//     * @return Delete_priv
//     */
//    public String getDeletePriv() {
//        return deletePriv;
//    }
//
//    /**
//     * @param deletePriv
//     */
//    public void setDeletePriv(String deletePriv) {
//        this.deletePriv = deletePriv;
//    }
//
//    /**
//     * @return Create_priv
//     */
//    public String getCreatePriv() {
//        return createPriv;
//    }
//
//    /**
//     * @param createPriv
//     */
//    public void setCreatePriv(String createPriv) {
//        this.createPriv = createPriv;
//    }
//
//    /**
//     * @return Drop_priv
//     */
//    public String getDropPriv() {
//        return dropPriv;
//    }
//
//    /**
//     * @param dropPriv
//     */
//    public void setDropPriv(String dropPriv) {
//        this.dropPriv = dropPriv;
//    }
//
//    /**
//     * @return Reload_priv
//     */
//    public String getReloadPriv() {
//        return reloadPriv;
//    }
//
//    /**
//     * @param reloadPriv
//     */
//    public void setReloadPriv(String reloadPriv) {
//        this.reloadPriv = reloadPriv;
//    }
//
//    /**
//     * @return Shutdown_priv
//     */
//    public String getShutdownPriv() {
//        return shutdownPriv;
//    }
//
//    /**
//     * @param shutdownPriv
//     */
//    public void setShutdownPriv(String shutdownPriv) {
//        this.shutdownPriv = shutdownPriv;
//    }
//
//    /**
//     * @return Process_priv
//     */
//    public String getProcessPriv() {
//        return processPriv;
//    }
//
//    /**
//     * @param processPriv
//     */
//    public void setProcessPriv(String processPriv) {
//        this.processPriv = processPriv;
//    }
//
//    /**
//     * @return File_priv
//     */
//    public String getFilePriv() {
//        return filePriv;
//    }
//
//    /**
//     * @param filePriv
//     */
//    public void setFilePriv(String filePriv) {
//        this.filePriv = filePriv;
//    }
//
//    /**
//     * @return Grant_priv
//     */
//    public String getGrantPriv() {
//        return grantPriv;
//    }
//
//    /**
//     * @param grantPriv
//     */
//    public void setGrantPriv(String grantPriv) {
//        this.grantPriv = grantPriv;
//    }
//
//    /**
//     * @return References_priv
//     */
//    public String getReferencesPriv() {
//        return referencesPriv;
//    }
//
//    /**
//     * @param referencesPriv
//     */
//    public void setReferencesPriv(String referencesPriv) {
//        this.referencesPriv = referencesPriv;
//    }
//
//    /**
//     * @return Index_priv
//     */
//    public String getIndexPriv() {
//        return indexPriv;
//    }
//
//    /**
//     * @param indexPriv
//     */
//    public void setIndexPriv(String indexPriv) {
//        this.indexPriv = indexPriv;
//    }
//
//    /**
//     * @return Alter_priv
//     */
//    public String getAlterPriv() {
//        return alterPriv;
//    }
//
//    /**
//     * @param alterPriv
//     */
//    public void setAlterPriv(String alterPriv) {
//        this.alterPriv = alterPriv;
//    }
//
//    /**
//     * @return Show_db_priv
//     */
//    public String getShowDbPriv() {
//        return showDbPriv;
//    }
//
//    /**
//     * @param showDbPriv
//     */
//    public void setShowDbPriv(String showDbPriv) {
//        this.showDbPriv = showDbPriv;
//    }
//
//    /**
//     * @return Super_priv
//     */
//    public String getSuperPriv() {
//        return superPriv;
//    }
//
//    /**
//     * @param superPriv
//     */
//    public void setSuperPriv(String superPriv) {
//        this.superPriv = superPriv;
//    }
//
//    /**
//     * @return Create_tmp_table_priv
//     */
//    public String getCreateTmpTablePriv() {
//        return createTmpTablePriv;
//    }
//
//    /**
//     * @param createTmpTablePriv
//     */
//    public void setCreateTmpTablePriv(String createTmpTablePriv) {
//        this.createTmpTablePriv = createTmpTablePriv;
//    }
//
//    /**
//     * @return Lock_tables_priv
//     */
//    public String getLockTablesPriv() {
//        return lockTablesPriv;
//    }
//
//    /**
//     * @param lockTablesPriv
//     */
//    public void setLockTablesPriv(String lockTablesPriv) {
//        this.lockTablesPriv = lockTablesPriv;
//    }
//
//    /**
//     * @return Execute_priv
//     */
//    public String getExecutePriv() {
//        return executePriv;
//    }
//
//    /**
//     * @param executePriv
//     */
//    public void setExecutePriv(String executePriv) {
//        this.executePriv = executePriv;
//    }
//
//    /**
//     * @return Repl_slave_priv
//     */
//    public String getReplSlavePriv() {
//        return replSlavePriv;
//    }
//
//    /**
//     * @param replSlavePriv
//     */
//    public void setReplSlavePriv(String replSlavePriv) {
//        this.replSlavePriv = replSlavePriv;
//    }
//
//    /**
//     * @return Repl_client_priv
//     */
//    public String getReplClientPriv() {
//        return replClientPriv;
//    }
//
//    /**
//     * @param replClientPriv
//     */
//    public void setReplClientPriv(String replClientPriv) {
//        this.replClientPriv = replClientPriv;
//    }
//
//    /**
//     * @return Create_view_priv
//     */
//    public String getCreateViewPriv() {
//        return createViewPriv;
//    }
//
//    /**
//     * @param createViewPriv
//     */
//    public void setCreateViewPriv(String createViewPriv) {
//        this.createViewPriv = createViewPriv;
//    }
//
//    /**
//     * @return Show_view_priv
//     */
//    public String getShowViewPriv() {
//        return showViewPriv;
//    }
//
//    /**
//     * @param showViewPriv
//     */
//    public void setShowViewPriv(String showViewPriv) {
//        this.showViewPriv = showViewPriv;
//    }
//
//    /**
//     * @return Create_routine_priv
//     */
//    public String getCreateRoutinePriv() {
//        return createRoutinePriv;
//    }
//
//    /**
//     * @param createRoutinePriv
//     */
//    public void setCreateRoutinePriv(String createRoutinePriv) {
//        this.createRoutinePriv = createRoutinePriv;
//    }
//
//    /**
//     * @return Alter_routine_priv
//     */
//    public String getAlterRoutinePriv() {
//        return alterRoutinePriv;
//    }
//
//    /**
//     * @param alterRoutinePriv
//     */
//    public void setAlterRoutinePriv(String alterRoutinePriv) {
//        this.alterRoutinePriv = alterRoutinePriv;
//    }
//
//    /**
//     * @return Create_user_priv
//     */
//    public String getCreateUserPriv() {
//        return createUserPriv;
//    }
//
//    /**
//     * @param createUserPriv
//     */
//    public void setCreateUserPriv(String createUserPriv) {
//        this.createUserPriv = createUserPriv;
//    }
//
//    /**
//     * @return Event_priv
//     */
//    public String getEventPriv() {
//        return eventPriv;
//    }
//
//    /**
//     * @param eventPriv
//     */
//    public void setEventPriv(String eventPriv) {
//        this.eventPriv = eventPriv;
//    }
//
//    /**
//     * @return Trigger_priv
//     */
//    public String getTriggerPriv() {
//        return triggerPriv;
//    }
//
//    /**
//     * @param triggerPriv
//     */
//    public void setTriggerPriv(String triggerPriv) {
//        this.triggerPriv = triggerPriv;
//    }
//
//    /**
//     * @return Create_tablespace_priv
//     */
//    public String getCreateTablespacePriv() {
//        return createTablespacePriv;
//    }
//
//    /**
//     * @param createTablespacePriv
//     */
//    public void setCreateTablespacePriv(String createTablespacePriv) {
//        this.createTablespacePriv = createTablespacePriv;
//    }
//
//    /**
//     * @return ssl_type
//     */
//    public String getSslType() {
//        return sslType;
//    }
//
//    /**
//     * @param sslType
//     */
//    public void setSslType(String sslType) {
//        this.sslType = sslType;
//    }
//
//    /**
//     * @return max_questions
//     */
//    public Integer getMaxQuestions() {
//        return maxQuestions;
//    }
//
//    /**
//     * @param maxQuestions
//     */
//    public void setMaxQuestions(Integer maxQuestions) {
//        this.maxQuestions = maxQuestions;
//    }
//
//    /**
//     * @return max_updates
//     */
//    public Integer getMaxUpdates() {
//        return maxUpdates;
//    }
//
//    /**
//     * @param maxUpdates
//     */
//    public void setMaxUpdates(Integer maxUpdates) {
//        this.maxUpdates = maxUpdates;
//    }
//
//    /**
//     * @return max_connections
//     */
//    public Integer getMaxConnections() {
//        return maxConnections;
//    }
//
//    /**
//     * @param maxConnections
//     */
//    public void setMaxConnections(Integer maxConnections) {
//        this.maxConnections = maxConnections;
//    }
//
//    /**
//     * @return max_user_connections
//     */
//    public Integer getMaxUserConnections() {
//        return maxUserConnections;
//    }
//
//    /**
//     * @param maxUserConnections
//     */
//    public void setMaxUserConnections(Integer maxUserConnections) {
//        this.maxUserConnections = maxUserConnections;
//    }
//
//    /**
//     * @return plugin
//     */
//    public String getPlugin() {
//        return plugin;
//    }
//
//    /**
//     * @param plugin
//     */
//    public void setPlugin(String plugin) {
//        this.plugin = plugin;
//    }
//
//    /**
//     * @return ssl_cipher
//     */
//    public byte[] getSslCipher() {
//        return sslCipher;
//    }
//
//    /**
//     * @param sslCipher
//     */
//    public void setSslCipher(byte[] sslCipher) {
//        this.sslCipher = sslCipher;
//    }
//
//    /**
//     * @return x509_issuer
//     */
//    public byte[] getX509Issuer() {
//        return x509Issuer;
//    }
//
//    /**
//     * @param x509Issuer
//     */
//    public void setX509Issuer(byte[] x509Issuer) {
//        this.x509Issuer = x509Issuer;
//    }
//
//    /**
//     * @return x509_subject
//     */
//    public byte[] getX509Subject() {
//        return x509Subject;
//    }
//
//    /**
//     * @param x509Subject
//     */
//    public void setX509Subject(byte[] x509Subject) {
//        this.x509Subject = x509Subject;
//    }
//
//    /**
//     * @return authentication_string
//     */
//    public String getAuthenticationString() {
//        return authenticationString;
//    }
//
//    /**
//     * @param authenticationString
//     */
//    public void setAuthenticationString(String authenticationString) {
//        this.authenticationString = authenticationString;
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", blogs=" + blogs +
                '}';
    }


//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
////        sb.append(", host=").append(host);
////        sb.append(", user=").append(user);
////        sb.append(", password=").append(password);
////        sb.append(", selectPriv=").append(selectPriv);
////        sb.append(", insertPriv=").append(insertPriv);
////        sb.append(", updatePriv=").append(updatePriv);
////        sb.append(", deletePriv=").append(deletePriv);
////        sb.append(", createPriv=").append(createPriv);
////        sb.append(", dropPriv=").append(dropPriv);
////        sb.append(", reloadPriv=").append(reloadPriv);
////        sb.append(", shutdownPriv=").append(shutdownPriv);
////        sb.append(", processPriv=").append(processPriv);
////        sb.append(", filePriv=").append(filePriv);
////        sb.append(", grantPriv=").append(grantPriv);
////        sb.append(", referencesPriv=").append(referencesPriv);
////        sb.append(", indexPriv=").append(indexPriv);
////        sb.append(", alterPriv=").append(alterPriv);
////        sb.append(", showDbPriv=").append(showDbPriv);
////        sb.append(", superPriv=").append(superPriv);
////        sb.append(", createTmpTablePriv=").append(createTmpTablePriv);
////        sb.append(", lockTablesPriv=").append(lockTablesPriv);
////        sb.append(", executePriv=").append(executePriv);
////        sb.append(", replSlavePriv=").append(replSlavePriv);
////        sb.append(", replClientPriv=").append(replClientPriv);
////        sb.append(", createViewPriv=").append(createViewPriv);
////        sb.append(", showViewPriv=").append(showViewPriv);
////        sb.append(", createRoutinePriv=").append(createRoutinePriv);
////        sb.append(", alterRoutinePriv=").append(alterRoutinePriv);
////        sb.append(", createUserPriv=").append(createUserPriv);
////        sb.append(", eventPriv=").append(eventPriv);
////        sb.append(", triggerPriv=").append(triggerPriv);
////        sb.append(", createTablespacePriv=").append(createTablespacePriv);
////        sb.append(", sslType=").append(sslType);
////        sb.append(", maxQuestions=").append(maxQuestions);
////        sb.append(", maxUpdates=").append(maxUpdates);
////        sb.append(", maxConnections=").append(maxConnections);
////        sb.append(", maxUserConnections=").append(maxUserConnections);
////        sb.append(", plugin=").append(plugin);
////        sb.append(", sslCipher=").append(sslCipher);
////        sb.append(", x509Issuer=").append(x509Issuer);
////        sb.append(", x509Subject=").append(x509Subject);
////        sb.append(", authenticationString=").append(authenticationString);
//        sb.append(", serialVersionUID=").append(serialVersionUID);
//        sb.append("]");
//        return sb.toString();
//    }
}