package quileia.com.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

import quileia.com.domain.enumeration.Estado;

/**
 * A FranjaHoraria.
 */
@Entity
@Table(name = "franja_horaria")
public class FranjaHoraria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "franja", length = 30, nullable = false, unique = true)
    private String franja;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_franja_horaria", nullable = false)
    private Estado estadoFranjaHoraria;

    @OneToMany(mappedBy = "franjaHoraria")
    private Set<Horario> horarios = new HashSet<>();

    @OneToMany(mappedBy = "franjaHoraria")
    private Set<Medico> medicos = new HashSet<>();

    @OneToMany(mappedBy = "franjaHoraria")
    private Set<Cita> citas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFranja() {
        return franja;
    }

    public FranjaHoraria franja(String franja) {
        this.franja = franja;
        return this;
    }

    public void setFranja(String franja) {
        this.franja = franja;
    }

    public Estado getEstadoFranjaHoraria() {
        return estadoFranjaHoraria;
    }

    public FranjaHoraria estadoFranjaHoraria(Estado estadoFranjaHoraria) {
        this.estadoFranjaHoraria = estadoFranjaHoraria;
        return this;
    }

    public void setEstadoFranjaHoraria(Estado estadoFranjaHoraria) {
        this.estadoFranjaHoraria = estadoFranjaHoraria;
    }

    public Set<Horario> getHorarios() {
        return horarios;
    }

    public FranjaHoraria horarios(Set<Horario> horarios) {
        this.horarios = horarios;
        return this;
    }

    public FranjaHoraria addHorarios(Horario horario) {
        this.horarios.add(horario);
        horario.setFranjaHoraria(this);
        return this;
    }

    public FranjaHoraria removeHorarios(Horario horario) {
        this.horarios.remove(horario);
        horario.setFranjaHoraria(null);
        return this;
    }

    public void setHorarios(Set<Horario> horarios) {
        this.horarios = horarios;
    }

    public Set<Medico> getMedicos() {
        return medicos;
    }

    public FranjaHoraria medicos(Set<Medico> medicos) {
        this.medicos = medicos;
        return this;
    }

    public FranjaHoraria addMedicos(Medico medico) {
        this.medicos.add(medico);
        medico.setFranjaHoraria(this);
        return this;
    }

    public FranjaHoraria removeMedicos(Medico medico) {
        this.medicos.remove(medico);
        medico.setFranjaHoraria(null);
        return this;
    }

    public void setMedicos(Set<Medico> medicos) {
        this.medicos = medicos;
    }

    public Set<Cita> getCitas() {
        return citas;
    }

    public FranjaHoraria citas(Set<Cita> citas) {
        this.citas = citas;
        return this;
    }

    public FranjaHoraria addCitas(Cita cita) {
        this.citas.add(cita);
        cita.setFranjaHoraria(this);
        return this;
    }

    public FranjaHoraria removeCitas(Cita cita) {
        this.citas.remove(cita);
        cita.setFranjaHoraria(null);
        return this;
    }

    public void setCitas(Set<Cita> citas) {
        this.citas = citas;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FranjaHoraria)) {
            return false;
        }
        return id != null && id.equals(((FranjaHoraria) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FranjaHoraria{" +
            "id=" + getId() +
            ", franja='" + getFranja() + "'" +
            ", estadoFranjaHoraria='" + getEstadoFranjaHoraria() + "'" +
            "}";
    }
}
