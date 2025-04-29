package edu.hit.version2.common.service;

import edu.hit.version2.common.po.User;

/**
 * @ClassName UserService
 * @Description TODO
 * @Date 2025/4/28 11:47
 **/
public interface UserService {

    Integer addUser(User user);


    User getUser(Integer id);
}
