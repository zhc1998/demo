/**
 * Copyright (C), 2019-2019, 金科
 * FileName: CommentsNoteUtil
 * Author:  黄斌
 * Date:     2019/8/22 14:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.util;

import com.jk.model.Comments;
import com.jk.model.Comments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 斌
 * @create 2019/8/22
 * @since 1.0.0
 */
public class CommentsNoteUtil {
    /**
     * 获取父节点菜单
     * @param CommentssList 所有树菜单集合
     * @return
     */
    public final static List<Comments> getFatherNode(List<Comments> CommentssList){
        List<Comments> newCommentss = new ArrayList<Comments>();
        for (Comments mt : CommentssList) {
            if (mt.getPid() ==null || "".equals(mt.getPid()) || mt.getPid()==0 ) {//如果pId为空，则该节点为父节点
                //递归获取父节点下的子节点
                mt.setNodes(getChildrenNode(mt.getId().toString(), CommentssList));
                newCommentss.add(mt);
            }
        }
        return newCommentss;
    }

    /**
     * 递归获取子节点下的子节点
     * @param pId 父节点的ID
     * @param CommentssList 所有菜单树集合
     * @return
     */
    private final static List<Comments> getChildrenNode(String pId, List<Comments> CommentssList){
        List<Comments> newCommentss = new ArrayList<Comments>();
        for (Comments mt : CommentssList) {
            if (mt.getPid()==null || mt.getPid()==0) continue;
            if(mt.getPid() == Integer.valueOf(pId) ){
                //递归获取子节点下的子节点，即设置树控件中的children
                mt.setNodes(getChildrenNode(mt.getId().toString(), CommentssList));
                //设置树控件attributes属性的数据
                Map<String, Object> map = new HashMap<String, Object>();
                mt.setAttributes(map);
                newCommentss.add(mt);
            }
        }
        return newCommentss;
    }
}
