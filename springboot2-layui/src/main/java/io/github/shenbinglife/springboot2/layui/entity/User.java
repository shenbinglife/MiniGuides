package io.github.shenbinglife.springboot2.layui.entity;

import javax.persistence.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.List;

@Entity
@Table(indexes = {@Index(columnList = "name"), @Index(columnList = "age")})
public class User extends BaseEntity{

    private Integer age;
    private String account;
    private String name;
    @Column(name = "mobile_phone")
    private String mobilePhone;

    /**
     * mappedBy的那一端，就意味着放弃了对关系的维护，也不能增删改查中间表的关系
     */
    @ManyToMany(mappedBy = "users")
    private List<Org> orgs;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public List<Org> getOrgs() {
        return orgs;
    }

    public void setOrgs(List<Org> orgs) {
        this.orgs = orgs;
    }
}
