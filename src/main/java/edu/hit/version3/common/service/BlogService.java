package edu.hit.version3.common.service;

import edu.hit.version3.common.po.Blog;

/**
 * @ClassName BlogService
 * @Description TODO
 * @Date 2025/4/28 14:22
 **/
public interface BlogService {
    // 查询博客信息
    Blog getBlogById(Integer id);

}
