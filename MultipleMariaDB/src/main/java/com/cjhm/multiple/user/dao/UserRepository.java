package com.cjhm.multiple.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cjhm.multiple.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
