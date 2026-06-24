package com.pm.ms_suscripciones;

import com.pm.ms_suscripciones.Model.Suscripcion;
import com.pm.ms_suscripciones.Repository.SuscripcionRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner{

    @Autowired
    private SuscripcionRepository suscripcionRepository;

    @Override
    public void run(String... args) throws Exception {

        // Solo insertar si la tabla está vacía
        if (suscripcionRepository.count() > 0) {
            return;
        }

        Faker faker = new Faker(Locale.of("es"));
        Random random = new Random();

        String[] planes  = {"BASIC", "PREMIUM", "FAMILIAR"};
        String[] estados = {"ACTIVO", "CANCELADO", "PENDIENTE"};

        // Generar 20 suscripciones de prueba
        for (int i = 0; i < 20; i++) {

            String estado     = estados[random.nextInt(estados.length)];
            LocalDate inicio  = LocalDate.now().minusDays(faker.number().numberBetween(1, 365));
            LocalDate fin     = estado.equals("CANCELADO")
                    ? inicio.plusDays(faker.number().numberBetween(1, 180))
                    : null;

            Suscripcion sus = new Suscripcion();
            sus.setUsuarioId((long) faker.number().numberBetween(1, 30));
            sus.setPlanId(planes[random.nextInt(planes.length)]);
            sus.setMonto(faker.number().randomDouble(2, 5, 50));
            sus.setFechaInicio(inicio);
            sus.setFechaFin(fin);
            sus.setEstado(estado);

            suscripcionRepository.save(sus);
        }

        System.out.println(" DataLoader: 20 suscripciones de prueba insertadas correctamente.");
    }
}
