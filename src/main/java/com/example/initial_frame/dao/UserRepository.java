package com.example.initial_frame.dao;

import com.example.initial_frame.bean.login.Role;
import com.example.initial_frame.bean.login.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value="SELECT * FROM user WHERE user_name=?1 ",nativeQuery=true)
    User loadUserByUserName(String username);
    @Query(value="SELECT r.id,r.name,r.description from  role r left join user_role ur  on r.id = ur.role_id where  ur.user_id =?1 ",nativeQuery=true)
    List<Role> getUserRolesByUid(Integer id);
}
