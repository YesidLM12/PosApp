package com.enterprise.posapp.common.seeds;

import com.enterprise.posapp.usuarios.model.entity.Usuarios;
import com.enterprise.posapp.usuarios.model.enums.Rol;
import com.enterprise.posapp.usuarios.repository.RolRepositoryJpa;
import com.enterprise.posapp.usuarios.repository.UsuarioRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final UsuarioRepositoryJpa usuarioRepository;
    private final RolRepositoryJpa rolRepositoryJpa;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0) {
            Usuarios admin = Usuarios.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .rol(rolRepositoryJpa.findByRol(Rol.ADMINISTRADOR))
                    .created_at(LocalDateTime.now())
                    .activo(true)
                    .build();

            usuarioRepository.save(admin);


            Usuarios mesero = Usuarios.builder()
                    .username("mesero")
                    .password(passwordEncoder.encode("mesero123"))
                    .rol(rolRepositoryJpa.findByRol(Rol.MESERO))
                    .created_at(LocalDateTime.now())
                    .activo(true)
                    .build();

            usuarioRepository.save(mesero);


            Usuarios cocina = Usuarios.builder()
                    .username("cocina")
                    .password(passwordEncoder.encode("cocina123"))
                    .rol(rolRepositoryJpa.findByRol(Rol.COCINA))
                    .created_at(LocalDateTime.now())
                    .activo(true)
                    .build();

            usuarioRepository.save(cocina);

        }
        System.out.println("Seed de usuarios ejecutado");
    }
}
