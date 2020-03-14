package com.cloud.service;

import com.cloud.dto.User;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

    /**
     * Check if user exists by username.
     * @param username username.
     * @return user info from local json file.
     */
    public User validate(String username){
        //TODO check from json file
        return new User();
    }

    /**
     * Check the validity of user information.
     * @param user user info.
     * @return user info
     */
    public boolean authc(User user){
        //TODO check from json file
        return true;
    }
}