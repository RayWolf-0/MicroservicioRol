package cl.duoc.MicroServicioRoles.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.duoc.MicroServicioRoles.entity.Rol;
import cl.duoc.MicroServicioRoles.repository.RolRepository;

@Service
@Transactional
public class RolServiceImpl implements RolService{

    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<Rol> obtenerRoles() {
        return rolRepository.findAll();
    }

    @Override
    public Rol obtenerPorId(int id) {
        return rolRepository.findById(id).orElse(null);
    }

    @Override
    public Rol guardarRol(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public void eliminarRol(int id) {
        rolRepository.deleteById(id);
    }
}
