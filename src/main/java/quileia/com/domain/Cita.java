package quileia.com.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

/**
 * A Cita.
 */
@Entity
@Table(name = "cita")
public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGeneratorCita")
    @SequenceGenerator(name = "sequenceGeneratorCita")
    private Long id;

    @NotNull
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("citas")
    private Especialidad especialidad;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("citas")
    private FranjaHoraria franjaHoraria;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("horas")
    private Horario horario;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("citas")
    private Medico medicos;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("citas")
    private Paciente pacientes;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Cita fecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public Cita especialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
        return this;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public FranjaHoraria getFranjaHoraria() {
        return franjaHoraria;
    }

    public Cita franjaHoraria(FranjaHoraria franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
        return this;
    }

    public void setFranjaHoraria(FranjaHoraria franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
    }

    public Horario getHorario() {
        return horario;
    }

    public Cita horario(Horario horario) {
        this.horario = horario;
        return this;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Medico getMedicos() {
        return medicos;
    }

    public Cita medicos(Medico medico) {
        this.medicos = medico;
        return this;
    }

    public void setMedicos(Medico medico) {
        this.medicos = medico;
    }

    public Paciente getPacientes() {
        return pacientes;
    }

    public Cita pacientes(Paciente paciente) {
        this.pacientes = paciente;
        return this;
    }

    public void setPacientes(Paciente paciente) {
        this.pacientes = paciente;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cita)) {
            return false;
        }
        return id != null && id.equals(((Cita) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Cita{" +
            "id=" + getId() +
            ", fecha='" + getFecha() + "'" +
            "}";
    }
}
