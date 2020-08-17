package io.github.shenbinglife.springboot2.layui.entity;

import io.github.shenbinglife.springboot2.layui.model.TreeNode;
import sun.reflect.generics.tree.Tree;

import javax.persistence.*;
import java.util.List;

@Entity
public class Org extends BaseEntity {
    @Column(name = "parent_id")
    private Long parentId;
    private String name;
    private String type;
    private String description;

    @ManyToMany
    @JoinTable(name = "org_user_relation",
            joinColumns = {@JoinColumn(name = "org_id")},
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public TreeNode<Long> toTreeNode(Long parentId) {
        TreeNode<Long> tree = new TreeNode<>();
        tree.setId(getId());
        tree.setParentId(parentId);
        tree.setName(name);
        tree.addAttr("desc", description);
        return tree;
    }
}
