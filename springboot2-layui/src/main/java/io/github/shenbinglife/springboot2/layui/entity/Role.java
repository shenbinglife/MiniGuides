package io.github.shenbinglife.springboot2.layui.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Role  extends BaseEntity{
    private String name;
    private String description;
    private String type;

    @ManyToMany
    @JoinTable(name = "role_user_relation",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
