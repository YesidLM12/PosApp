package com.enterprise.posapp.usuarios.repository;

import com.enterprise.posapp.usuarios.model.entity.Roles;

public interface RolRepository {
    void save (Roles rol);

    Roles findByRol (String rol);
}
