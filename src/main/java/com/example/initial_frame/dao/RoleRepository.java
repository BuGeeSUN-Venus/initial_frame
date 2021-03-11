package com.example.initial_frame.dao;

import com.example.initial_frame.bean.login.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    @Query(value="SELECT r.id,r.name,r.description from  role r left join user_role ur  on r.id = ur.role_id where  ur.user_id =?1 ",nativeQuery=true)
    List<Role> getUserRolesByUid(Integer id);
    @Query(value="SELECT r.id,r.name,r.description from  role r left join role_resource rr  on r.id = rr.role_id where  rr.resource_id =?1 ",nativeQuery=true)
    List<Role> getRolesByResId(Integer id);
}
