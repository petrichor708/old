package com.lala.yanglao.service.serviceImplement;


import com.lala.yanglao.dao.UserDao;
import com.lala.yanglao.model.User;
import com.lala.yanglao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User checkUser(String username) {
        User user=userDao.selectUsernameAndPassword(username);
        return user;
    }
}
