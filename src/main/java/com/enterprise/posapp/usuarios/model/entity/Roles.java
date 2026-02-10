package com.enterprise.posapp.usuarios.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Roles {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, length = 50)
	private String codigo;

	@Column(nullable = false)
	private String nombre;

	private String descripcion;

	@Column(nullable = false)
	private boolean activo;

	private LocalDateTime created_at;

	@OneToMany(mappedBy = "rol")
	private List<Usuarios> usuarios;
}
