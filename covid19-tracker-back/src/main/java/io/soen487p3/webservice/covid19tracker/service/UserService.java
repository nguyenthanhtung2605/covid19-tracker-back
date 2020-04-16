package io.soen487p3.webservice.covid19tracker.service;

import io.soen487p3.webservice.covid19tracker.model.User;

public interface UserService{
    public void saveUser(User user);

    public boolean isUserAlreadyPresent(User user);
}
