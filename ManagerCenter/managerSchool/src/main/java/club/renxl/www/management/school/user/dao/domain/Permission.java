package club.renxl.www.management.school.user.dao.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Permission {
	 /**  */
    private Integer id;

    /** 名称 */
    private String name;

    /** 编码 */
    private String code;

    /** 类型0资源1菜单2按钮 */
    private String type;

    /** 路径 */
    private String path;

    /** 页面路径 */
    private String page;

    /** 父级id,顶级为0 */
    private Integer pid;

    /** 状态 1启用 0禁用 */
    private String status;

    /** 顺序 */
    private Integer seq;

    /** 描述 */
    private String description;

    /** 创建时间 */
    private Date createTime;

    /**
     * 树结构;子孙菜单
     */
    private List<Permission> children;
	/**
	 * 初始化操作参数
	 */
	public void init() {
		createTime = new Date();
		name = name != null ? name : "操作资源";
		description = description != null ? description:"desc";
		seq = seq != null ? seq :0 ;
		status = status != null ? status : "0";
		pid = pid != null ? pid :-1;
		type = type != null ? type:"0";
		code = code != null ?code :"-1";
	}
}