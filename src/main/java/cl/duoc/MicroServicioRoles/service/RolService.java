package cl.duoc.MicroServicioRoles.service;

import java.util.List;

import cl.duoc.MicroServicioRoles.entity.Rol;

public interface RolService {
    List<Rol> obtenerRoles();
    Rol obtenerPorId(int id);
    Rol guardarRol(Rol rol);
    void eliminarRol(int id);
}
