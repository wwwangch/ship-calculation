package com.iscas.biz.mp.test.model;


import java.util.List;

/**
 * @author admin
 */
@SuppressWarnings("unused")
public class Parent {
    private Integer id;

    private String name;

    private List<Child> childs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public List<Child> getChilds() {
        return childs;
    }

    public void setChilds(List<Child> childs) {
        this.childs = childs;
    }
}