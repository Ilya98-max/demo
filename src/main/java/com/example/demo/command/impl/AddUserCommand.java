package com.example.demo.command.impl;

import com.example.demo.command.Command;
import com.example.demo.entity.User;
import com.example.demo.exception.CommandException;
import com.example.demo.exception.ServiceException;
import com.example.demo.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class AddUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String phoneNumber = request.getParameter("phone_number");
        String lastName = request.getParameter("last_name");
        String password = request.getParameter("password");


        if (phoneNumber == null || lastName == null || password == null ||
                phoneNumber.isEmpty() || lastName.isEmpty() || password.isEmpty()) {
            return "pages/fields.jsp";
        }

        if (phoneNumber.length() != 11 || !phoneNumber.startsWith("+")) {
            return "/add-user-form.html";
        }

        try {
            if (UserServiceImpl.getInstance().getUserByLastName(lastName) != null) {
                return "pages/Users.jsp";
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


        User newUser = new User(phoneNumber, lastName, password);


        boolean added = UserServiceImpl.getInstance().add(newUser);

        if (added) {
            return "pages/successful.jsp";
        } else {
            return "pages/unsuccessful.jsp";
        }
    }
}




