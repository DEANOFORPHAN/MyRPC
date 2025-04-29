package edu.hit.version3.server.service.impl;

import java.util.UUID;

import edu.hit.version3.common.po.User;
import edu.hit.version3.common.service.UserService;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Date 2025/4/28 14:23
 **/
public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(Integer id) {
        System.out.println("查询ID" + id + "的用户");
        return User.builder()
                .sex(false)
                .name(UUID.randomUUID().toString())
                .id(id)
                .build();
    }

    @Override
    public Integer addUser(User user) {
        System.out.println("DB插入成功");
        return 0;
    }
}
