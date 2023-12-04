package co.com.reservatuviaje.reservadeviajes.repositories;

import co.com.reservatuviaje.reservadeviajes.models.Reservas;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ReservasRepository extends R2dbcRepository<Reservas,Integer> {

    Mono<Reservas> findById(@Param("id") Integer id);
}
