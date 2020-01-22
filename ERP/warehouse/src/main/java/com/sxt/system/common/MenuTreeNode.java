package com.sxt.system.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author song
 * @data 2020/1/18
 */
@Data
public class MenuTreeNode {
    private Integer id;
    private Integer pid;

    private String title;
    private String href;
    private String icon;
    private Boolean spread;
    private String target;
    private String typecode;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<MenuTreeNode> child=new ArrayList<>();

    /**
     * 构造左边的树
     * @param id
     * @param pid
     * @param title
     * @param href
     * @param icon
     * @param spread
     * @param target
     */
    public MenuTreeNode(Integer id, Integer pid, String title, String href, String icon, Boolean spread, String target,String typecode) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.href = href;
        this.icon = icon;
        this.spread = spread;
        this.target = target;
        this.typecode = typecode;
    }
    public static class MenuTreeNodeBuilder{
        public static List<MenuTreeNode> build(List<MenuTreeNode> treeNodes,Integer topId){
            List<MenuTreeNode> nodes=new ArrayList<>();
            for (MenuTreeNode n1 : treeNodes) {
                if(n1.getPid().equals(topId)){
                    nodes.add(n1);
                }
                for (MenuTreeNode n2 : treeNodes) {
                    if(n2.getPid().equals(n1.getId())){
                      n1.getChild().add(n2);
                    }
                }
            }
            return nodes;
        }
    }
}
