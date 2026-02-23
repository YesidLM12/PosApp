package com.enterprise.posapp.usuarios.model.entity;

import com.enterprise.posapp.ordenes.model.entity.Orden;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "usuarios")
public class Usuarios {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, columnDefinition = "true")
	private boolean activo;

	@Column(nullable = false)
	private LocalDateTime created_at;

	@ManyToOne
	@JoinTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private Roles rol;

	@OneToMany(mappedBy = "usuario")
	private List<Orden> ordenes;
}
