package club.renxl.www.management.school.user.dao.domain;

import lombok.Data;

@Data
public class RolePermission {
    /**  */
    private Integer id;

    /**  */
    private Integer roleId;

    /**  */
    private Integer permissionId;
}