package com.example.demo.command;

import com.example.demo.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
@FunctionalInterface
public interface Command {

    String execute (HttpServletRequest request) throws CommandException;
    default void refresh (){}
}
