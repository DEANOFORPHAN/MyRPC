package edu.hit.version2.reflection.dynamicProxy.po.impl;

import edu.hit.version2.reflection.dynamicProxy.po.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName UserImpl
 * @Description TODO
 * @Date 2025/4/28 14:10
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserServiceImpl implements UserService {
    private String name;
    private Integer age;
    private Boolean sex;
    private Integer id;

    @Override
    public void doSomething() {

    }
}
