package cl.duoc.MicroServicioRoles.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Entidad que representa un rol")
public class Rol {

    @Id
    @Column(name = "ID_ROL")
    @Schema(description = "id del rol")
    private int idRol;

    @Column(name = "NOMBRE_ROL", nullable = false, unique = true)
    @Schema(description = "nombre del rol")
    private String nombreRol;
}