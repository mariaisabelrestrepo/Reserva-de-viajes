package co.com.reservatuviaje.reservadeviajes.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Entity(name = "reservas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Reservas que realizan los clientes de la app")
public class Reservas {

    //@Id
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer reservaId;
    protected Integer clienteID;
    protected String destino;
    protected String transporte;
    protected Integer totalDias;
    protected Integer totalPersonas;
    protected String costo;
    protected String estado;
    protected String fechaIngreso;
    protected String fechaSalida;
}
