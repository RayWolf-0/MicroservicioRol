package cl.duoc.MicroServicioRoles.Assembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;



import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;


import cl.duoc.MicroServicioRoles.controller.RolController;
import cl.duoc.MicroServicioRoles.entity.Rol;


public class RolAssembler implements RepresentationModelAssembler<Rol, EntityModel<Rol>>{

    @Override
    public EntityModel<Rol> toModel(Rol rl){
        return EntityModel.of(
            rl,
            linkTo(methodOn(RolController.class).obtenerPorId(rl.getIdRol())).withRel("Lista-el-rol-buscado"),
            linkTo(methodOn(RolController.class).obtenerRoles()).withRel("Todas-los-rols"),
            linkTo(methodOn(RolController.class).eliminarRol(rl.getIdRol())).withRel("Eliminar-un-rol"),
            linkTo(methodOn(RolController.class).actualizarRol(rl.getIdRol(), rl)).withRel("Actualizar-un-rol")
        );
    }
}
