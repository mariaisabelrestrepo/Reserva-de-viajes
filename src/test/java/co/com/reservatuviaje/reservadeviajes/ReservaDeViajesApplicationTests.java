package co.com.reservatuviaje.reservadeviajes;

import co.com.reservatuviaje.reservadeviajes.controllers.ReservasController;
import co.com.reservatuviaje.reservadeviajes.models.Reservas;
import co.com.reservatuviaje.reservadeviajes.repositories.ReservasRepository;
import co.com.reservatuviaje.reservadeviajes.services.ReservasService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ReservaDeViajesApplicationTests {

	@InjectMocks
	private ReservasController reservasController;

	@Mock
	private ReservasService reservasService;

	@Mock
	private ReservasRepository reservasRepository;

	@Test
	void crearReservaDeberiaRetornarReserva() {
		// Arrange
		Reservas reservaInput = new Reservas(1, 1, "Miami", "Avión", 7, 2, "1000", "Activa", "2023-11-20", "2023-11-27");
		Reservas reservaOutput = new Reservas(1, 1, "Miami", "Avión", 7, 2, "1000", "Activa", "2023-11-20", "2023-11-27");

		when(reservasService.crearReserva(any(Reservas.class))).thenReturn(Mono.just(reservaOutput));

		// Act
		Mono<Reservas> resultado = reservasController.crearReserva(reservaInput);

		// Assert
		StepVerifier.create(resultado)
				.expectNext(reservaOutput)
				.verifyComplete();
	}


	@Test
	void cancelarReservaDeberiaRetornarReservaCancelada() {
		// Arrange
		Integer id = 1;
		Reservas reservaInput = new Reservas(null, 1, "Miami", "Avión", 7, 2, "1000", "Cancelada", "2023-11-20", "2023-11-27");
		Reservas reservaExistente = new Reservas(1, 1, "Miami", "Avión", 7, 2, "1000", "Activa", "2023-11-20", "2023-11-27");

		when(reservasRepository.findById(id)).thenReturn(Mono.just(reservaExistente));
		when(reservasRepository.save(any(Reservas.class))).thenReturn(Mono.just(reservaExistente));

		// Act
		Mono<Reservas> resultado = reservasController.cancelarReserva(id,reservaInput);

		// Assert
		StepVerifier.create(resultado)
				.expectNext(reservaExistente)
				.verifyComplete();
	}

	@Test
	void actualizarReservaDeberiaRetornarReservaActualizada() {
		// Arrange
		Integer id = 1;
		Reservas reservaInput = new Reservas(null, 1, "Miami", "Avión", 7, 2, "1200", "Activa", "2023-11-20", "2023-11-27");
		Reservas reservaExistente = new Reservas(1, 1, "Miami", "Avión", 7, 2, "1000", "Activa", "2023-11-20", "2023-11-27");

		when(reservasRepository.findById(id)).thenReturn(Mono.just(reservaExistente));
		when(reservasRepository.save(any(Reservas.class))).thenReturn(Mono.just(reservaInput));

		// Act
		Mono<Reservas> resultado = reservasController.actualizarReserva(id, reservaInput);

		// Assert
		StepVerifier.create(resultado)
				.expectNext(reservaInput)
				.verifyComplete();
	}
	@Test
	void consultarTodasLasReservasDeberiaRetornarFlujoDeReservas() {
		// Arrange
		Reservas reserva1 = new Reservas(1, 1, "Miami", "Avión", 7, 2, "1000", "Activa", "2023-11-20", "2023-11-27");
		Reservas reserva2 = new Reservas(2, 2, "Nueva York", "Barco", 5, 3, "1500", "Activa", "2023-11-25", "2023-11-30");

		Flux<Reservas> reservas = Flux.just(reserva1, reserva2);

		when(reservasService.ObtenerTodasLasReservas()).thenReturn(reservas);

		// Act
		Flux<Reservas> resultado = reservasController.consultarTodasLasReservas();

		// Assert
		StepVerifier.create(resultado)
				.expectNext(reserva1, reserva2)
				.verifyComplete();
	}


	@Test
	void consultarReservaPorIdDeberiaRetornarReserva() {
		// Arrange
		Integer id = 1;
		Reservas reserva = new Reservas(1, 1, "Miami", "Avión", 7, 2, "1000", "Activa", "2023-11-20", "2023-11-27");

		when(reservasService.ObtenerReserva(id)).thenReturn(Mono.just(reserva));

		// Act
		Mono<Reservas> resultado = reservasController.consultarReservaPorId(id);

		// Assert
		StepVerifier.create(resultado)
				.expectNext(reserva)
				.verifyComplete();
	}



}
