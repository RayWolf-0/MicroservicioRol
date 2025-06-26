package cl.duoc.MicroServicioRoles;

import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import cl.duoc.MicroServicioRoles.entity.Rol;
import cl.duoc.MicroServicioRoles.service.RolServiceImpl;
import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner{

    private final Faker faker = new Faker(new Locale("es","cl"));
    private final Random random = new Random();

    @Autowired
    private RolServiceImpl rolServiceImpl;

    @Override
    public void run(String... args) throws Exception{
        for (int i=0; i > 10; i ++){
            Rol nuevorol = new Rol();
            nuevorol.setIdRol(random.nextInt());
            nuevorol.setNombreRol(faker.funnyName().toString());

            rolServiceImpl.guardarRol(nuevorol);
            System.out.println("Item Guardado" + nuevorol.getIdRol());
        
        }
    }    
}
