package club.renxl.www.management.school.user.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import club.renxl.www.management.school.user.dao.PermissionMapper;
import club.renxl.www.management.school.user.dao.domain.Permission;
import club.renxl.www.management.school.user.dao.domain.PermissionExample;
import club.renxl.www.management.school.user.dao.domain.PermissionExample.Criteria;
import club.renxl.www.management.school.user.service.IMenu;
import club.renxl.www.response.BaseResponse;

/**
 * 菜单 管理
 * @author renxl
 * @date 2018/09/26
 * @version 1.0.0
 */
@Service
public class IMenuImpl implements IMenu {
	/**
	 * 父菜单
	 */
	private static final String MENU = "1";
	
	
	/**
	 * 子菜单
	 */
	private static final String MENU_SON = "2";

	/**
	 * 资源状态:0启用
	 */
	private static final String USING = "1";
	
	
	@Autowired
	private PermissionMapper permissionMapper;
	
	 
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see club.renxl.www.management.school.user.service.IMenu#lookTree()
	 */
	@Override
	public BaseResponse lookTree() {
		List<Permission> trees =  getMenuTree();
		trees = fromTreesToStructTree(trees);
		return BaseResponse.success(trees);
	}
	
	/**
	 * 将数据库的菜单通过递归以及层次遍历处理形成真正的树结构
	 * @param trees
	 * @return
	 */
	private List<Permission> fromTreesToStructTree(List<Permission> trees) {
		if(trees == null || trees.isEmpty()) {
			return new LinkedList<Permission>();
		}
		// 最终的树结构
		List<Permission> rootTree  = new LinkedList<Permission>(); 
		// 剩下的待处理元素
		List<Permission> otherElements  = new LinkedList<Permission>(); 
		for (Permission permission : trees) {
			if(permission.getPid() == 0) {
				rootTree.add(permission);
			}else {
				otherElements.add(permission);
			}
		}
		// 转换成树结构
	    getSonByFatherWithAllData(rootTree,rootTree,otherElements);

		
		return rootTree;
	}
	
	/**
	 * 
	 * @param rootTree 最终树结构
	 * @param currentRoots 当前层节点
	 * @param otherElements 剩余待处理数据
	 */
	private void getSonByFatherWithAllData(List<Permission> rootTree, List<Permission> currentRoots,
			List<Permission> newDatas) {

		// 总剩余未分配数据
				List<Permission> currentNewDatas = new LinkedList<Permission>();
				
				// 当前所有子节点节点（下次迭代根节点集合）
				List<Permission> newCurrentRoot = new LinkedList<Permission>();
				// step1: 1当前层每个元素寻找子元素，获取当前层所有的子元素
				for(Permission PermissionRootEveryOne : currentRoots) {
					List<Permission> children = new LinkedList<Permission>();
					for(Permission Permission : newDatas) {
						if(PermissionRootEveryOne.getId().intValue() ==  Permission.getPid().intValue()) {
							children.add(Permission);
							newCurrentRoot.add(Permission);
						} 
					}
					
					// step2: 给当前层每个元素绑定子元素
					// 将当前的子节点加入的所在父节点
					// 在root中获取到PermissionRootEveryOne并且添加改
					if(!children.isEmpty()) {
						//PermissionRootEveryOne.setChildren(children);
						boolean setSuccess = false;
						for(Permission isAddChildPermission:rootTree) {
							// 子元素找到要待添加树的节点
							if(setSuccess) {
								break;
							}
							if(isAddChildPermission.getId().intValue() == PermissionRootEveryOne.getId().intValue()) {
								isAddChildPermission.setChildren(children);
								setSuccess = true;
								break;
							}else {
								// 找到PermissionRootEveryOne，并设置子节点children
								setChildWithAllTreeAndChild(isAddChildPermission,children,PermissionRootEveryOne,setSuccess);
							}
						}
					}
				}
				
				// step3:  获取新的所有的未处理数据
				// newDatas 1，2，3，4，5
				for(Permission Permission : newDatas) {
					// currentRoots 1，2，3
					// 4属于1，2，3的子节点，被处理，则剩下5需要处理
					// 获取5作为未处理的数据,即新的newdatas
					if(!newCurrentRoot.contains(Permission)) {
						currentNewDatas.add(Permission);
					}
				}
				if(currentNewDatas == null || currentNewDatas.isEmpty()) {
					return;
				}
				
				// 递归处理树
				getSonByFatherWithAllData(rootTree, newCurrentRoot, currentNewDatas);
		
	}
	
	/**
	 * 添加子节点
	 * @param isAddChildPermission
	 * @param children
	 * @param permissionRootEveryOne
	 * @param setSuccess
	 */
	private void setChildWithAllTreeAndChild(Permission isAddChildPermission, List<Permission> children,
			Permission permissionRootEveryOne, boolean setSuccess) {
		List<Permission> sons = isAddChildPermission.getChildren();
		if(sons == null || sons.isEmpty()) {
			// 属于中序遍历逻辑
			// 某一个根节点开始递归到子节点都没找到要添加子节点的元素
			//  	     1
			//		 2       3
			//    4     5  6   7
			//                8 
			// 递归逻辑，假设1包含23  2包含45  5包含67   7包含8
			// 现在要给7添加一个节点9
			// 递归按照左子树逻辑
			// 1-2-4(没找到) 
			// 1-2-5
			// 1-3-6
			// 1-3-7找到
			// 添加元素 新树如下
			// 	     	 1
			//		 2       3
			//    4     5  6   7
			//                8  9 
			
			return;
		}
		for(Permission isAddChild:sons) {
			// isAddChild 子元素找到了节点;递归中断上层for循环，也可抛出一个异常;直接中断递归程序
			if(setSuccess) {
				break;
			}
			if(isAddChild.getId().intValue() == permissionRootEveryOne.getId().intValue()) {
				isAddChild.setChildren(children);
				setSuccess = true;
				break;
			}else {
				setChildWithAllTreeAndChild(isAddChild,children,permissionRootEveryOne, setSuccess);
			}
			
		}
		
		
	}
	
	/**
	 * 获取所有的结构数据;返回的为非结构体（树状）数据
	 * @return
	 */
	private List<Permission> getMenuTree() {
		PermissionExample example = new PermissionExample();
		example.setOrderByClause(" seq ");
		Criteria createCriteria = example.createCriteria();
		createCriteria.andTypeBetween(MENU, MENU_SON);
		createCriteria.andStatusEqualTo(USING);
		List<Permission> results = permissionMapper.selectByExample(example );
		return results;
	}

	

}
