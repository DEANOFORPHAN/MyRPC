package edu.hit.version3.server.service.impl;

import edu.hit.version3.common.po.Blog;
import edu.hit.version3.common.service.BlogService;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName BlogServiceImpl
 * @Description TODO
 * @Date 2025/4/28 14:23
 **/
public class BlogServiceImpl implements BlogService {
    @Override
    public Blog getBlogById(Integer id) {
        System.out.println("查询ID" + id + "的博客");
        return Blog.builder()
                .title("手写自己的RPC框架")
                .useId(1)
                .id(1)
        .build();
    }
}
