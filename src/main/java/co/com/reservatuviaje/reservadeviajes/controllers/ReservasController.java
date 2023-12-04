package co.com.reservatuviaje.reservadeviajes.controllers;

import co.com.reservatuviaje.reservadeviajes.models.Reservas;
import co.com.reservatuviaje.reservadeviajes.repositories.ReservasRepository;
import co.com.reservatuviaje.reservadeviajes.services.ReservasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/api/v1/reservas")
@RestController
@AllArgsConstructor
@Tag(name = "Reservas", description = " Operaciones para interactuar con el sistema de reservas disponibles en la aplicacion")

public class ReservasController {

    protected ReservasService reservasService;
    protected ReservasRepository reservasRepository;

    @Operation(summary = "Crear una reserva", description = "Permite crear una reserva")
    @Parameter(name = "Reserva", description = "reserva a crear", content = @Content(schema = @Schema(implementation = Reservas.class)))
    @ApiResponse(responseCode = "200", description = "Reserva creada",
            content = @Content(schema = @Schema(implementation = Reservas.class)))
    @PostMapping("/create")
    public Mono<Reservas> crearReserva(@RequestBody Reservas reserva) {
        return reservasService.crearReserva(reserva);
    }

    @Operation(summary = "Cancelar una reserva", description = "Permite cancelar una reserva")
    @Parameter(name = "Reservas", description = "Reserva a cancelar", content = @Content(schema = @Schema(implementation = Reservas.class)))
    @ApiResponse(responseCode = "200", description = "Reserva Cancelada",
            content = @Content(schema = @Schema(implementation = Reservas.class)))
    @PostMapping("/cancel/{id}")
    public Mono<Reservas> cancelarReserva(@PathVariable("id") Integer id,@RequestBody Reservas reservas) {
        return reservasRepository.findById(id)
                .flatMap(existingProducto -> {
                    existingProducto.setEstado(reservas.getEstado());
                    return reservasService.cancelarReserva(existingProducto);
                });

    }

    @Operation(summary = "Actualizar una reserva", description = "Permite actualizar una reserva")
    @Parameter(name = "Reservas", description = "Reserva a actualizar", content = @Content(schema = @Schema(implementation = Reservas.class)))
    @ApiResponse(responseCode = "200", description = "Reserva Actualizada",
            content = @Content(schema = @Schema(implementation = Reservas.class)))
    @PutMapping("/update/{id}")
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
    @Operation(summary = "Obtener todas las reservas", description = "Permite consultar todas las reservas")
    @ApiResponse(responseCode = "200", description = "Reservas obtenidas",
            content = @Content(schema = @Schema(implementation = Reservas.class)))
    @GetMapping("/getAll")
    public Flux<Reservas> consultarTodasLasReservas() {
        return reservasService.ObtenerTodasLasReservas();
    }


    @Operation(summary = "Obtener una reserva por id", description = "Permite consultar una reserva")
    @ApiResponse(responseCode = "200", description = "Reserva Obtenida",
            content = @Content(schema = @Schema(implementation = Reservas.class)))
    @GetMapping("/get/{id}")
    public Mono<Reservas> consultarReservaPorId(@PathVariable("id") Integer reservaId){
        return reservasService.ObtenerReserva(reservaId);
    }

    @Operation(summary = "Eliminar todas las reservas", description = "Permite eliminar todas las reservas")
    @ApiResponse(responseCode = "200", description = "Reservas eliminadas",
            content = @Content(schema = @Schema(implementation = Reservas.class)))
    @DeleteMapping("/deleteAll")
    public Mono<Void> eliminarTodasLasReservas() {
        return reservasService.eliminarTodasLasReservas();
    }


    @Operation(summary = "Eliminar Reserva por id", description = "Permite eliminar una reserva por id")
    @ApiResponse(responseCode = "200", description = "Reservas eliminada",
            content = @Content(schema = @Schema(implementation = Reservas.class)))
    @DeleteMapping("/delete/{id}")
    public Mono<Reservas> eliminarReservaPorId(@PathVariable("id") Integer reservaId){
        return reservasService.eliminarReserva(reservaId);
    }

}
