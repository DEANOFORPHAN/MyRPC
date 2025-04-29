package edu.hit.version3.common.service;

import edu.hit.version3.common.po.User;

/**
 * @ClassName UserService
 * @Description TODO
 * @Date 2025/4/28 14:22
 **/
public interface UserService {
    // 查询用户信息
    User getUserById(Integer id);
    // 增加新用户
    Integer addUser(User user);
}
