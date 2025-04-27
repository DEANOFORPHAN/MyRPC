package edu.hit.version1.Server.po.service.impl;

import java.util.Random;
import java.util.UUID;

import edu.hit.version1.Server.po.User;
import edu.hit.version1.Server.po.service.UserService;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Date 2025/4/27 20:19
 **/
public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(Integer id) {
        User user = User.builder()
                .id(id)
                .name(UUID.randomUUID().toString())
                .sex(new Random().nextBoolean())
                .build();
        System.out.println("查找了ID为" + id + "的用户");

        return user;
    }
}
