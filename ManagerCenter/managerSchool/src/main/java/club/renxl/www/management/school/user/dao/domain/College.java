package club.renxl.www.management.school.user.dao.domain;

import lombok.Data;

@Data
public class College {
	//  非数据库字段
	private Area area;
	//////////////////////////////////////数据库字段///////////////////////////////////////////
	
	/**  */
    private Integer coid;

    /**  */
    private String name;

    /**  */
    private Integer provinceid;



}