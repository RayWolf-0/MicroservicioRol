package cl.duoc.MicroServicioRoles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.MicroServicioRoles.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

}
