package club.renxl.www.management.school.user.dao.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Role {
	/**
	 *	LAY_CHECKED为layui专属 标记权限是否选中角色0选择，1没有选中
	 */
    @JsonProperty("LAY_CHECKED")
	private Boolean LAY_CHECKED;
    
	//////////////////////////////////////////////////////////////数据库字段///////////////////////////////////////////
    /**  */
    private Integer id;

    /**  */
    private String roleName;

    /**  */
    private Long createUserId;

    /**  */
    private String createUserName;

    /**  */
    private Integer modifyUserId;

    /**  */
    private String modifyUserName;

    /**  */
    private Date createDate;

    /**  */
    private Date modifyDate;

    /** 0启用1禁用 */
    private String status;

    /**  */
    private String isDeleted;
    
    /** 角色包含的权限集合 */
    private List<Permission> permissions;
    
    /** 角色关联的权限集合 */
    private List<RolePermission> rolePermissions;
 
}