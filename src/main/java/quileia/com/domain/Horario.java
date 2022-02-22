package quileia.com.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A Horario.
 */
@Entity
@Table(name = "horario")
public class Horario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "hora", length = 20, nullable = false, unique = true)
    private String hora;

    @OneToMany(mappedBy = "horario")
    private Set<Cita> horas = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("horarios")
    private FranjaHoraria franjaHoraria;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHora() {
        return hora;
    }

    public Horario hora(String hora) {
        this.hora = hora;
        return this;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Set<Cita> getHoras() {
        return horas;
    }

    public Horario horas(Set<Cita> citas) {
        this.horas = citas;
        return this;
    }

    public Horario addHoras(Cita cita) {
        this.horas.add(cita);
        cita.setHorario(this);
        return this;
    }

    public Horario removeHoras(Cita cita) {
        this.horas.remove(cita);
        cita.setHorario(null);
        return this;
    }

    public void setHoras(Set<Cita> citas) {
        this.horas = citas;
    }

    public FranjaHoraria getFranjaHoraria() {
        return franjaHoraria;
    }

    public Horario franjaHoraria(FranjaHoraria franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
        return this;
    }

    public void setFranjaHoraria(FranjaHoraria franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Horario)) {
            return false;
        }
        return id != null && id.equals(((Horario) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Horario{" +
            "id=" + getId() +
            ", hora='" + getHora() + "'" +
            "}";
    }
}
