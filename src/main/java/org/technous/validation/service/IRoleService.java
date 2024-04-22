package org.technous.validation.service;


import org.technous.validation.exception.UsernameNotFoundException;
import org.technous.validation.model.Role;
import org.technous.validation.model.User;

import java.util.List;

public interface IRoleService {
    List<Role> getRoles();
    Role createRole(Role theRole);

    void deleteRole(Long id);
    Role findByName(String name);

    User removeUserFromRole(Long userId, Long roleId) throws UsernameNotFoundException;
    User assignRoleToUser(Long userId, Long roleId);
    Role removeAllUsersFromRole(Long roleId);
}
