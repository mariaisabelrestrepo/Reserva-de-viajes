package co.com.reservatuviaje.reservadeviajes.controllers;

import co.com.reservatuviaje.reservadeviajes.models.Reservas;
import co.com.reservatuviaje.reservadeviajes.repositories.ReservasRepository;
import co.com.reservatuviaje.reservadeviajes.services.ReservasService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/api/v1/reservas")
@RestController
@AllArgsConstructor
public class ReservasController {

    protected ReservasService reservasService;
    protected ReservasRepository reservasRepository;

    @PostMapping("/crearReserva")
    public Mono<Reservas> crearReserva(@RequestBody Reservas reserva) {
        return reservasService.crearReserva(reserva);
    }

    @PostMapping("/cancelarReserva/{id}")
    public Mono<Reservas> cancelarReserva(@PathVariable("id") Integer id,@RequestBody Reservas reserva) {
        return reservasRepository.findById(id)
                .flatMap(existingProducto -> {
                    existingProducto.setEstado(existingProducto.getEstado());
                    return reservasRepository.save(existingProducto);
                });

    }
    @PostMapping("/actualizarReserva/{id}")
    public Mono<Reservas> actualizarReserva(@PathVariable("id") Integer id,@RequestBody Reservas reserva) {
        return reservasRepository.findById(id)
                .flatMap(existingProducto -> {

                    existingProducto.setEstado(reserva.getEstado());
                    existingProducto.setDestino(reserva.getDestino());
                    existingProducto.setCosto(reserva.getCosto());
                    existingProducto.setTotalDias(reserva.getTotalDias());
                    existingProducto.setTotalPersonas(reserva.getTotalPersonas());
                    existingProducto.setFechaIngreso(reserva.getFechaIngreso());
                    existingProducto.setFechaSalida(reserva.getFechaSalida());
                    existingProducto.setTransporte(reserva.getTransporte());
                    return reservasRepository.save(existingProducto);
                });

    }
    @GetMapping("/consultarTodo")
    public Flux<Reservas> consultarTodasLasReservas() {
        return reservasService.ObtenerTodasLasReservas();
    }

    @GetMapping("/consultarReservaId/{id}")
    public Mono<Reservas> consultarReservaPorId(@PathVariable("id") Integer id){
        return reservasService.ObtenerReserva(id);
    }
}
