package com.rengu.entity;

import javax.persistence.*;

/**
 * Created by hanchangming on 2017/5/9.
 */
@Entity
@Table(name = "Student", schema = "TestDatabase")
public class StudentEntity {
    private String no;
    private String name;
    private String sex;
    private Integer age;

    @Id
    @Column(name = "no", nullable = false, length = 7)
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "sex", nullable = true, length = 2)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "age", nullable = true)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentEntity that = (StudentEntity) o;

        if (no != null ? !no.equals(that.no) : that.no != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = no != null ? no.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }
}
