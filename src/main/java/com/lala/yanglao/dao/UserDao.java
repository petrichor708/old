package com.lala.yanglao.dao;

import com.lala.yanglao.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    User selectUsernameAndPassword(String username);
}
