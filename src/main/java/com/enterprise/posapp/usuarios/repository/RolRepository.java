package com.enterprise.posapp.usuarios.repository;

import com.enterprise.posapp.usuarios.model.entity.Roles;
import com.enterprise.posapp.usuarios.model.enums.Rol;

public interface RolRepository {
    void save (Roles rol);

    Roles findByRol (Rol rol);
}
