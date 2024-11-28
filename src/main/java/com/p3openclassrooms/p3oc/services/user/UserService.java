package com.p3openclassrooms.p3oc.services.user;

import com.p3openclassrooms.p3oc.models.User;

public interface UserService {
    public User create(User user);
    public User getByEmail(String email);
    public User getById(Long id);
    public User update(Long id, User user);
    public void delete(Long id);

}
