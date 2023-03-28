package com.jwt.Authentiaction.project.Login.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.Authentiaction.project.Login.entity.Role;


public interface RoleRepo  extends JpaRepository<Role, Integer>{

}
