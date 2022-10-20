package ua.goit.hw6.model.dao;

import java.util.Objects;

public class DeveloperDao {
    private Long id;
    private String name;
    private String username;
    private Integer salary;

    public DeveloperDao() {
    }

    public DeveloperDao(String name, String username, Integer salary) {
        this.name = name;
        this.username = username;
        this.salary = salary;
    }

    public DeveloperDao(Long id, String name, String username, Integer salary) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeveloperDao that = (DeveloperDao) o;
        return id.equals(that.id) && salary.equals(that.salary) && Objects.equals(name, that.name) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, salary);
    }
}
