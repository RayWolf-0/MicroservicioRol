package cl.duoc.MicroServicioRoles.TestService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cl.duoc.MicroServicioRoles.entity.Rol;
import cl.duoc.MicroServicioRoles.repository.RolRepository;
import cl.duoc.MicroServicioRoles.service.RolServiceImpl;

public class TestRolService {


    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private RolServiceImpl rolServiceImpl;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarTodo(){

        List<Rol> lista = new ArrayList<>();

        Rol Rol1 = new Rol();
        Rol Rol2 = new Rol();

        Rol1.setIdRol(1);
        Rol1.setNombreRol("vendedor");

        Rol2.setIdRol(2);
        Rol2.setNombreRol("ceo");

        lista.add(Rol1);
        lista.add(Rol1);

        when(rolRepository.findAll()).thenReturn(lista);

        List<Rol> resultadoBusqueda = rolServiceImpl.obtenerRoles();

        assertEquals(2, resultadoBusqueda.size());
        verify(rolRepository, times(1)).findAll();
    }

    @Test
    public void testBuscarUnItem(){
        Rol Rol1 = new Rol();
        Rol1.setIdRol(1);
        Rol1.setNombreRol("vendedor");


        when(rolRepository.findById(1)).thenReturn(Optional.of(Rol1));

        Rol itembuscado = rolServiceImpl.obtenerPorId(1);
        assertEquals(1, itembuscado.getIdRol());
        verify(rolRepository, times(1)).findById(1);
    }

    @Test
    public void testGuardarRol(){
        Rol Rol1 = new Rol();
        Rol1.setIdRol(1);
        Rol1.setNombreRol("vendedor");

        when(rolRepository.save(Rol1)).thenReturn(Rol1);

        Rol RolGuardado = rolServiceImpl.guardarRol(Rol1);

        assertEquals(1, RolGuardado.getIdRol());
        verify(rolRepository, times(1)).save(Rol1);

    }

    @Test
    public void testeliminarRol(){
        int Id_item = 1;
        doNothing().when(rolRepository).deleteById(Id_item);

        rolServiceImpl.eliminarRol(Id_item);

        verify(rolRepository,times(1)).deleteById(Id_item);
    }    
}
