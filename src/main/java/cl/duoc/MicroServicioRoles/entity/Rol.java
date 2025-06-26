package cl.duoc.MicroServicioRoles.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ROLES")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rol {

    @Id
    @Column(name = "ID_ROL")
    private int idRol;

    @Column(name = "NOMBRE_ROL", nullable = false, unique = true)
    private String nombreRol;
}