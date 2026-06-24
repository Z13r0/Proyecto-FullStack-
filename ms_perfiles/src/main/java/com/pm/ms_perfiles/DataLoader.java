package com.pm.ms_perfiles;

import com.pm.ms_perfiles.Model.Perfil;
import com.pm.ms_perfiles.Repository.PerfilRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner{

    @Autowired
    private PerfilRepository perfilRepository;

    @Override
    public void run(String... args) throws Exception {

        if (perfilRepository.count() > 0) {
            return;
        }

        Faker faker = new Faker(Locale.of("es"));

        for (int i = 0; i < 15; i++) {
            Perfil perfil = new Perfil();
            perfil.setUsuarioId((long) faker.number().numberBetween(1, 10));
            perfil.setNombrePerfil(faker.name().firstName());
            perfil.setUrlAvatar("https://avatar.example.com/" + faker.internet().slug());
            perfil.setEsInfantil(faker.bool().bool());
            perfil.setActivo(true);
            perfilRepository.save(perfil);
        }

        System.out.println("DataLoader: 15 perfiles de prueba insertados correctamente.");
    }
}
