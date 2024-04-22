package org.technous.validation.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.technous.validation.exception.UsernameNotFoundException;
import org.technous.validation.model.Role;
import org.technous.validation.model.User;
import org.technous.validation.repository.RoleRepo;
import org.technous.validation.repository.UserRepository;
import org.technous.validation.service.IRoleService;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class RoleService implements IRoleService {
    private final RoleRepo roleRepo;
    private final UserRepository userRepository;

    @Override
    public List<Role> getRoles() {
        return roleRepo.findAll();
    }

    @Override
    public Role createRole(Role theRole) {
        String roleName = "ROLE_" + theRole.getName().toUpperCase();
        Role role = new Role(roleName);
        if (roleRepo.existsByName(roleName)) {
//            throw new RoleAlreadyExistException(theRole.getName()+" role already exists");
        }
        return roleRepo.save(role);
    }

    @Override
    public void deleteRole(Long id) {

    }

    @Override
    public Role findByName(String name) {
        return roleRepo.findByName(name).get();
    }

    @Override
    public User removeUserFromRole(Long userId, Long roleId) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        Optional<Role> role = roleRepo.findById(roleId);
        if (role.isPresent() && role.get().getUsers().contains(user.get())) {
            role.get().removeUserFromRole(user.get());
            roleRepo.save(role.get());
            return user.get();
        }
        throw new UsernameNotFoundException("User not found");
    }

    @Override
    public User assignRoleToUser(Long userId, Long roleId) {
        return null;
    }

    @Override
    public Role removeAllUsersFromRole(Long roleId) {
        return null;
    }
}
