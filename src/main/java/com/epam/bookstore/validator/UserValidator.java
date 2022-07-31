package com.epam.bookstore.validator;

import com.epam.bookstore.entity.User;

public class UserValidator {

    public static boolean isAdmin(Integer roleId) {
        return roleId != null && roleId == 2;
    }

    public static boolean isDataFilled(User user) {
        return user.getFirstName() != null &&
                user.getMobile() != null &&
                user.getEmail() != null &&
                user.getPassword() != null;
    }

}
