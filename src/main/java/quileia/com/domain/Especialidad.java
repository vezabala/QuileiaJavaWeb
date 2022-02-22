package quileia.com.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

import quileia.com.domain.enumeration.Estado;

/**
 * A Especialidad.
 */
@Entity
@Table(name = "especialidad")
public class Especialidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "nombre_especialidad", length = 255, nullable = false, unique = true)
    private String nombreEspecialidad;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_tipo_documento", nullable = false)
    private Estado estadoTipoDocumento;

    @OneToMany(mappedBy = "especialidad")
    private Set<Medico> medicos = new HashSet<>();

    @OneToMany(mappedBy = "especialidad")
    private Set<Cita> citas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public Especialidad nombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
        return this;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public Estado getEstadoTipoDocumento() {
        return estadoTipoDocumento;
    }

    public Especialidad estadoTipoDocumento(Estado estadoTipoDocumento) {
        this.estadoTipoDocumento = estadoTipoDocumento;
        return this;
    }

    public void setEstadoTipoDocumento(Estado estadoTipoDocumento) {
        this.estadoTipoDocumento = estadoTipoDocumento;
    }

    public Set<Medico> getMedicos() {
        return medicos;
    }

    public Especialidad medicos(Set<Medico> medicos) {
        this.medicos = medicos;
        return this;
    }

    public Especialidad addMedicos(Medico medico) {
        this.medicos.add(medico);
        medico.setEspecialidad(this);
        return this;
    }

    public Especialidad removeMedicos(Medico medico) {
        this.medicos.remove(medico);
        medico.setEspecialidad(null);
        return this;
    }

    public void setMedicos(Set<Medico> medicos) {
        this.medicos = medicos;
    }

    public Set<Cita> getCitas() {
        return citas;
    }

    public Especialidad citas(Set<Cita> citas) {
        this.citas = citas;
        return this;
    }

    public Especialidad addCitas(Cita cita) {
        this.citas.add(cita);
        cita.setEspecialidad(this);
        return this;
    }

    public Especialidad removeCitas(Cita cita) {
        this.citas.remove(cita);
        cita.setEspecialidad(null);
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
        if (!(o instanceof Especialidad)) {
            return false;
        }
        return id != null && id.equals(((Especialidad) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Especialidad{" +
            "id=" + getId() +
            ", nombreEspecialidad='" + getNombreEspecialidad() + "'" +
            ", estadoTipoDocumento='" + getEstadoTipoDocumento() + "'" +
            "}";
    }
}
