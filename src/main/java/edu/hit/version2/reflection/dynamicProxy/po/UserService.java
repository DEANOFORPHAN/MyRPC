package edu.hit.version2.reflection.dynamicProxy.po;

/**
 * @ClassName UserService
 * @Description TODO
 * @Date 2025/4/28 14:10
 **/
public interface UserService {
    void doSomething();
    String getName();
    Integer getAge();
    Boolean getSex();
    Integer getId();
    void setName(String name);
    void setAge(Integer age);
    void setSex(Boolean sex);
    void setId(Integer id);
}
