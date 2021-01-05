package com.github.kylepeng93.mapper;

import com.github.kylepeng93.pojo.Blog;
import org.apache.ibatis.annotations.Param;

public interface BlogMapper {
    Blog selectBlog(@Param("id") Integer id);

    int insertBlog(Blog blog);
}