package com.example.initial_frame.service;

import com.example.initial_frame.bean.login.Resources;
import com.example.initial_frame.bean.login.Role;
import com.example.initial_frame.bean.login.User;
import com.example.initial_frame.dao.ResourcesRepository;
import com.example.initial_frame.dao.RoleRepository;
import com.example.initial_frame.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ResourcesRepository resourcesRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.loadUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("账户不存在!");
        }
        // 我的数据库用户密码没加密，这里手动设置
        String encodePassword = passwordEncoder.encode(user.getPassword());
        System.out.println("加密后的密码：" + encodePassword);
        user.setPassword(encodePassword);
        List<Role> userRoles = roleRepository.getUserRolesByUid(user.getId());
        user.setUserRoles(userRoles);
        return user;
    }



    /**
    * @Description TODO-SDC 获取全部资源（实际生产中可以放在Redis中）
    * @Author  SunDC
    * @Date   2021/3/10 上午10:44
    * @Param
    * @Return
    * @Exception
    *
    */
    public List<Resources> getAllResources(){

        List<Resources> resourcesAll = resourcesRepository.getAll();
        resourcesAll.forEach(resources -> {
            List<Role> roles = roleRepository.getRolesByResId(resources.getId());
            resources.setRoles(roles);
        });

        return resourcesAll;
    }


}
