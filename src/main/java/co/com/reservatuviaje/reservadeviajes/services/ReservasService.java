package co.com.reservatuviaje.reservadeviajes.services;

import co.com.reservatuviaje.reservadeviajes.models.Reservas;
import co.com.reservatuviaje.reservadeviajes.repositories.ReservasRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ReservasService {

    private final Logger LOGGER = LoggerFactory.getLogger(ReservasService.class);

    private final ReservasRepository reservasRepository;

    public Mono<Reservas> crearReserva(Reservas reservas) {
        return reservasRepository.save(reservas);
    }

    public Mono<Reservas> cancelarReserva(Reservas reservas) {
        return reservasRepository.save(reservas);
    }

    public Mono<Reservas> ObtenerReserva(Integer id) {
        return reservasRepository.findById(id)
                .onErrorResume(throwable -> {
                    LOGGER.error("Error al consultar reserva con id= " + id, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Reserva con id " + id + " no encontrado").getMostSpecificCause()));
    }

    public Flux<Reservas> ObtenerTodasLasReservas() {
        return reservasRepository.findAll()
                .onErrorResume(throwable -> {
                    LOGGER.error("Error al consultar todas las reservas", throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Ninguna reserva encontrado").getMostSpecificCause()));
    }

    public Mono<Void> eliminarTodasLasReservas() {
        return reservasRepository.deleteAll()
                .onErrorResume(throwable -> {
                    LOGGER.error("Error al borrar todas las reservas", throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Reservas no eliminadas").getMostSpecificCause()));
    }

    public Mono<Reservas> eliminarReserva(Integer id) {
        return reservasRepository.findById(id)
                .flatMap(reservas -> reservasRepository.deleteById(reservas.getReservaId()).thenReturn(reservas))
                .onErrorResume(throwable -> {
                    LOGGER.error("Error al borrar una reserva con id " + id, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Reserva con id= " + id + " no borrado").getMostSpecificCause()));
    }


}
