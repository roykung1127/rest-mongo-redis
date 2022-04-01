package com.kvinod.repository;

import java.util.List;

import com.kvinod.entity.User;

public interface UserCustomDAL {
    public User FindByCityAndPhone(String city, String phone );
    public List<User> FindByCityAndLastname(String city, String lastname);
    public List<User> useIn();
}
