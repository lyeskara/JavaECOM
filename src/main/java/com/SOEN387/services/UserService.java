package com.SOEN387.services;

import com.SOEN387.DAOs.UserDAO;
import com.SOEN387.models.User;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

   
}




