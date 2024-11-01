package pojo;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Employee {

    @ColumnName("id")
    private int id;
    @ColumnName("name")
    private String name;
    @ColumnName("age")
    private int age;
    @ColumnName("email")
    private String email;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public String getEmail() {
        return this.email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "Employee(id=" + this.getId() + ", name=" + this.getName() + ", age=" + this.getAge() + ", email=" + this.getEmail() + ")";
    }
}
