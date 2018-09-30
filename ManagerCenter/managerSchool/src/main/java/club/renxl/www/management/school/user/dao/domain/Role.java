package club.renxl.www.management.school.user.dao.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Role {
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