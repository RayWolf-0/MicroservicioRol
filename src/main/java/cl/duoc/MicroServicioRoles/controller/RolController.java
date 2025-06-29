package cl.duoc.MicroServicioRoles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.MicroServicioRoles.Assembler.RolAssembler;
import cl.duoc.MicroServicioRoles.entity.Rol;
import cl.duoc.MicroServicioRoles.service.RolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/roles")
@Tag(name = "Rol", description = "Endpoints para trabajar los Roles")
public class RolController {

    @Autowired
    private RolService rolService;
    @Autowired
    private RolAssembler asembler;

    //endpoint para listar los roles
    @GetMapping
    @Operation(summary = "Rol", description = "Operación que lista el Rol")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se listó correctamente el Rol",
            content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Rol.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro nada en el Rol",
                content = @Content(mediaType = "application/json",
                schema = @Schema(type = "string", example = "no se encuentran Datos")))
    })
    public ResponseEntity<?> obtenerRoles() {
        try {
            List<Rol> roles = rolService.obtenerRoles();
            return ResponseEntity.ok(asembler.toCollectionModel(roles));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al obtener roles: " + e.getMessage());
        }
    }

    //endpoint para buscar un rol
    @GetMapping("/{id}")
    @Operation(summary = "Endpoint que busca un Rol", description = "Operación que busca y lista un Rol")
    @Parameters(value = {
        @Parameter(name = "idRol", description = "Id del Rol que se va a buscar", in = ParameterIn.PATH, required = true)
    })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se lista correctamente El Rol",
            content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Rol.class))),
        @ApiResponse(responseCode = "404", description = "No se encuentran Rol",
                content = @Content(mediaType = "application/json",
                schema = @Schema(type = "string", example = "No se encuentran Rols")))
    })
    public ResponseEntity<?> obtenerPorId(@PathVariable int id) {
        try {
            Rol rol = rolService.obtenerPorId(id);
            return rol != null
                    ? ResponseEntity.ok(asembler.toModel(rol))
                    : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al buscar el rol: " + e.getMessage());
        }
    }

    //endpoint para guardar un rol
    @PostMapping
    @Operation(summary = "Endpoint que registra un Rol", description = "Endpoint que registra un Rol", 
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto rol que se va a registrar", required = true,
    content = @Content(schema = @Schema(implementation = Rol.class))
    ))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Indica que se registro correctamente el Rol", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Rol.class))),
        @ApiResponse(responseCode = "500", description = "Indica que no se logro registrar el Rol",
        content = @Content(schema = @Schema(type = "String", example = "No se puede registar El Rol")))
    })
    public ResponseEntity<?> crearRol(@RequestBody Rol rol) {
        try {
            Rol nuevoRol = rolService.guardarRol(rol);
            return ResponseEntity.ok(nuevoRol);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al crear el rol: " + e.getMessage());
        }
    }

    //endpoint que edita un rol
    @PutMapping("/{id}")
    @Operation(summary = "Endpoint que edita un Rol", description = "Endpoint que edita un Rol", 
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto Rol que se va a editar", required = true,
    content = @Content(schema = @Schema(implementation = Rol.class))
    ))
    @Parameters(value = {
        @Parameter(name = "idRol", description = "Id del Rol que se va a editar", in = ParameterIn.PATH, required = true)
    })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Indica que se registro correctamente el Rol", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Rol.class))),
        @ApiResponse(responseCode = "500", description = "Rol no esta registrado",
        content = @Content(schema = @Schema(type = "String", example = "Rol no esta registrado")))
    })
    public ResponseEntity<?> actualizarRol(@PathVariable int id, @RequestBody Rol rolActualizado) {
        try {
            Rol rolExistente = rolService.obtenerPorId(id);
            if (rolExistente != null) {
                rolActualizado.setIdRol(id);
                Rol actualizado = rolService.guardarRol(rolActualizado);
                return ResponseEntity.ok(asembler.toModel(rolActualizado));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al actualizar el rol: " + e.getMessage());
        }
    }

    //endpoint que elimina un rol
    @DeleteMapping("/{id}")
    @Operation(summary = "Endpoint que busca y elimina un rol", description = "Operación que busca y elimina un rol")
    @Parameters(value = {
        @Parameter(name = "idrol", description = "Id del rol que se va a eliminar", in = ParameterIn.PATH, required = true)
    })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se elimina rol",
        content = @Content(mediaType = "application/json",
        schema = @Schema(type = "string", example = "Se elimina rol"))),
        @ApiResponse(responseCode = "404", description = "rol no esta registrado",
                content = @Content(mediaType = "application/json",
                schema = @Schema(type = "string", example = "rol no esta registrado")))
    })
    public ResponseEntity<?> eliminarRol(@PathVariable int id) {
        try {
            rolService.eliminarRol(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al eliminar el rol: " + e.getMessage());
        }
    }
}
