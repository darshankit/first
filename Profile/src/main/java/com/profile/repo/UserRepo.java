package com.profile.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.profile.model.User;
@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

}
