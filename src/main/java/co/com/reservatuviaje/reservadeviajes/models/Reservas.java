package co.com.reservatuviaje.reservadeviajes.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservas {

    @Id
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
