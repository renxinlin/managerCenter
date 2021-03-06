package club.renxl.www.management.school.user.dao.domain;

import java.util.Date;

public class Artitle {
    /**  */
    private Long id;

    /** 发布编号 */
    private String code;

    /** 公告标题 */
    private String title;

    /** 副标题 */
    private String subheading;

    /** 关键字 */
    private String keywords;

    /** 主题，摘要 */
    private String theme;

    /** 作者id */
    private Long authorId;

    /** 作者名称 */
    private String authorName;

    /** 图片地址 */
    private String image;

    /** 图片链接跳转地址|封面图片 */
    private String coverImg;

    /** 评论总数,暂未用到 */
    private Integer commentNum;

    /** 阅读量 */
    private Integer readNum;

    /** 点赞总量 */
    private Integer zanNum;

    /** 创建时间 */
    private Date createDate;

    /** 最后更新时间 */
    private Date updateDate;

    /** 推荐模板，网页生成推荐模板json */
    private String recommendTemplate;

    /** 推荐内容，回调推荐模板作为表单生成推荐正文json */
    private String recommendContent;

    /** 页脚内容 */
    private String footerContent;

    /** 置顶 */
    private String top;

    /** 是否删除 */
    private String del;

    /** 备注字段 */
    private String field1;

    /** 备注字段 */
    private String field2;

    /** 备注字段 */
    private String field3;

    /** 备注字段 */
    private String field4;

    /** 备注字段 */
    private String field5;

    /** 备注字段：暂定为审核，当前不用 */
    private Integer field6;

    /** 备注字段:排序字段 */
    private Integer field7;

    /** 标签名称 */
    private String label;

    /** 类别名称 */
    private String type;

    /** 类别id */
    private Integer typeId;

    /** 公告正文 */
    private String bodys;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.id
     *
     * @return the value of school_artitle.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.id
     *
     * @param id the value for school_artitle.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.code
     *
     * @return the value of school_artitle.code
     *
     * @mbggenerated
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.code
     *
     * @param code the value for school_artitle.code
     *
     * @mbggenerated
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.title
     *
     * @return the value of school_artitle.title
     *
     * @mbggenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.title
     *
     * @param title the value for school_artitle.title
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.subheading
     *
     * @return the value of school_artitle.subheading
     *
     * @mbggenerated
     */
    public String getSubheading() {
        return subheading;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.subheading
     *
     * @param subheading the value for school_artitle.subheading
     *
     * @mbggenerated
     */
    public void setSubheading(String subheading) {
        this.subheading = subheading == null ? null : subheading.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.keywords
     *
     * @return the value of school_artitle.keywords
     *
     * @mbggenerated
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.keywords
     *
     * @param keywords the value for school_artitle.keywords
     *
     * @mbggenerated
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.theme
     *
     * @return the value of school_artitle.theme
     *
     * @mbggenerated
     */
    public String getTheme() {
        return theme;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.theme
     *
     * @param theme the value for school_artitle.theme
     *
     * @mbggenerated
     */
    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.author_id
     *
     * @return the value of school_artitle.author_id
     *
     * @mbggenerated
     */
    public Long getAuthorId() {
        return authorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.author_id
     *
     * @param authorId the value for school_artitle.author_id
     *
     * @mbggenerated
     */
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.author_name
     *
     * @return the value of school_artitle.author_name
     *
     * @mbggenerated
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.author_name
     *
     * @param authorName the value for school_artitle.author_name
     *
     * @mbggenerated
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName == null ? null : authorName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.image
     *
     * @return the value of school_artitle.image
     *
     * @mbggenerated
     */
    public String getImage() {
        return image;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.image
     *
     * @param image the value for school_artitle.image
     *
     * @mbggenerated
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.cover_img
     *
     * @return the value of school_artitle.cover_img
     *
     * @mbggenerated
     */
    public String getCoverImg() {
        return coverImg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.cover_img
     *
     * @param coverImg the value for school_artitle.cover_img
     *
     * @mbggenerated
     */
    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg == null ? null : coverImg.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.comment_num
     *
     * @return the value of school_artitle.comment_num
     *
     * @mbggenerated
     */
    public Integer getCommentNum() {
        return commentNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.comment_num
     *
     * @param commentNum the value for school_artitle.comment_num
     *
     * @mbggenerated
     */
    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.read_num
     *
     * @return the value of school_artitle.read_num
     *
     * @mbggenerated
     */
    public Integer getReadNum() {
        return readNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.read_num
     *
     * @param readNum the value for school_artitle.read_num
     *
     * @mbggenerated
     */
    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.zan_num
     *
     * @return the value of school_artitle.zan_num
     *
     * @mbggenerated
     */
    public Integer getZanNum() {
        return zanNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.zan_num
     *
     * @param zanNum the value for school_artitle.zan_num
     *
     * @mbggenerated
     */
    public void setZanNum(Integer zanNum) {
        this.zanNum = zanNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.create_date
     *
     * @return the value of school_artitle.create_date
     *
     * @mbggenerated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.create_date
     *
     * @param createDate the value for school_artitle.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.update_date
     *
     * @return the value of school_artitle.update_date
     *
     * @mbggenerated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.update_date
     *
     * @param updateDate the value for school_artitle.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.recommend_template
     *
     * @return the value of school_artitle.recommend_template
     *
     * @mbggenerated
     */
    public String getRecommendTemplate() {
        return recommendTemplate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.recommend_template
     *
     * @param recommendTemplate the value for school_artitle.recommend_template
     *
     * @mbggenerated
     */
    public void setRecommendTemplate(String recommendTemplate) {
        this.recommendTemplate = recommendTemplate == null ? null : recommendTemplate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.recommend_content
     *
     * @return the value of school_artitle.recommend_content
     *
     * @mbggenerated
     */
    public String getRecommendContent() {
        return recommendContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.recommend_content
     *
     * @param recommendContent the value for school_artitle.recommend_content
     *
     * @mbggenerated
     */
    public void setRecommendContent(String recommendContent) {
        this.recommendContent = recommendContent == null ? null : recommendContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.footer_content
     *
     * @return the value of school_artitle.footer_content
     *
     * @mbggenerated
     */
    public String getFooterContent() {
        return footerContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.footer_content
     *
     * @param footerContent the value for school_artitle.footer_content
     *
     * @mbggenerated
     */
    public void setFooterContent(String footerContent) {
        this.footerContent = footerContent == null ? null : footerContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.top
     *
     * @return the value of school_artitle.top
     *
     * @mbggenerated
     */
    public String getTop() {
        return top;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.top
     *
     * @param top the value for school_artitle.top
     *
     * @mbggenerated
     */
    public void setTop(String top) {
        this.top = top == null ? null : top.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.del
     *
     * @return the value of school_artitle.del
     *
     * @mbggenerated
     */
    public String getDel() {
        return del;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.del
     *
     * @param del the value for school_artitle.del
     *
     * @mbggenerated
     */
    public void setDel(String del) {
        this.del = del == null ? null : del.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.field1
     *
     * @return the value of school_artitle.field1
     *
     * @mbggenerated
     */
    public String getField1() {
        return field1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.field1
     *
     * @param field1 the value for school_artitle.field1
     *
     * @mbggenerated
     */
    public void setField1(String field1) {
        this.field1 = field1 == null ? null : field1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.field2
     *
     * @return the value of school_artitle.field2
     *
     * @mbggenerated
     */
    public String getField2() {
        return field2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.field2
     *
     * @param field2 the value for school_artitle.field2
     *
     * @mbggenerated
     */
    public void setField2(String field2) {
        this.field2 = field2 == null ? null : field2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.field3
     *
     * @return the value of school_artitle.field3
     *
     * @mbggenerated
     */
    public String getField3() {
        return field3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.field3
     *
     * @param field3 the value for school_artitle.field3
     *
     * @mbggenerated
     */
    public void setField3(String field3) {
        this.field3 = field3 == null ? null : field3.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.field4
     *
     * @return the value of school_artitle.field4
     *
     * @mbggenerated
     */
    public String getField4() {
        return field4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.field4
     *
     * @param field4 the value for school_artitle.field4
     *
     * @mbggenerated
     */
    public void setField4(String field4) {
        this.field4 = field4 == null ? null : field4.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.field5
     *
     * @return the value of school_artitle.field5
     *
     * @mbggenerated
     */
    public String getField5() {
        return field5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.field5
     *
     * @param field5 the value for school_artitle.field5
     *
     * @mbggenerated
     */
    public void setField5(String field5) {
        this.field5 = field5 == null ? null : field5.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.field6
     *
     * @return the value of school_artitle.field6
     *
     * @mbggenerated
     */
    public Integer getField6() {
        return field6;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.field6
     *
     * @param field6 the value for school_artitle.field6
     *
     * @mbggenerated
     */
    public void setField6(Integer field6) {
        this.field6 = field6;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.field7
     *
     * @return the value of school_artitle.field7
     *
     * @mbggenerated
     */
    public Integer getField7() {
        return field7;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.field7
     *
     * @param field7 the value for school_artitle.field7
     *
     * @mbggenerated
     */
    public void setField7(Integer field7) {
        this.field7 = field7;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.label
     *
     * @return the value of school_artitle.label
     *
     * @mbggenerated
     */
    public String getLabel() {
        return label;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.label
     *
     * @param label the value for school_artitle.label
     *
     * @mbggenerated
     */
    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.type
     *
     * @return the value of school_artitle.type
     *
     * @mbggenerated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.type
     *
     * @param type the value for school_artitle.type
     *
     * @mbggenerated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.type_id
     *
     * @return the value of school_artitle.type_id
     *
     * @mbggenerated
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.type_id
     *
     * @param typeId the value for school_artitle.type_id
     *
     * @mbggenerated
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column school_artitle.bodys
     *
     * @return the value of school_artitle.bodys
     *
     * @mbggenerated
     */
    public String getBodys() {
        return bodys;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column school_artitle.bodys
     *
     * @param bodys the value for school_artitle.bodys
     *
     * @mbggenerated
     */
    public void setBodys(String bodys) {
        this.bodys = bodys == null ? null : bodys.trim();
    }
}