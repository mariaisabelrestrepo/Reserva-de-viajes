package co.com.reservatuviaje.reservadeviajes.repositories;

import co.com.reservatuviaje.reservadeviajes.models.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservasRepository extends R2dbcRepository<Reservas,Integer> {
}
