package edu.hit.version2.Server.service;

import java.util.Random;
import java.util.UUID;

// import edu.hit.version2.Client.po.User;
import edu.hit.version2.common.po.User;
import edu.hit.version2.common.service.UserService;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Date 2025/4/28 11:52
 **/
public class UserServiceImpl implements UserService {
    @Override
    public Integer addUser(User user) {
        System.out.println("插入数据库成功");
        return 1;
    }

    @Override
    public User getUser(Integer id) {
        return User.builder()
                .id(id)
                .sex(new Random().nextBoolean())
                .name(UUID.randomUUID().toString())
                .build();
    }
}
