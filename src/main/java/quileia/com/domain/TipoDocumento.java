package quileia.com.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

import quileia.com.domain.enumeration.Estado;

/**
 * A TipoDocumento.
 */
@Entity
@Table(name = "tipo_documento")
public class TipoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGeneratorTipo")
    @SequenceGenerator(name = "sequenceGeneratorTipo")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "iniciales", length = 20, nullable = false, unique = true)
    private String iniciales;

    @NotNull
    @Size(max = 100)
    @Column(name = "nombre_documento", length = 100, nullable = false, unique = true)
    private String nombreDocumento;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_tipo_documento", nullable = false)
    private Estado estadoTipoDocumento;

    @OneToMany(mappedBy = "tipoDocumento")
    private Set<Medico> medicos = new HashSet<>();

    @OneToMany(mappedBy = "tipoDocumento")
    private Set<Paciente> pacientes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIniciales() {
        return iniciales;
    }

    public TipoDocumento iniciales(String iniciales) {
        this.iniciales = iniciales;
        return this;
    }

    public void setIniciales(String iniciales) {
        this.iniciales = iniciales;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public TipoDocumento nombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
        return this;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public Estado getEstadoTipoDocumento() {
        return estadoTipoDocumento;
    }

    public TipoDocumento estadoTipoDocumento(Estado estadoTipoDocumento) {
        this.estadoTipoDocumento = estadoTipoDocumento;
        return this;
    }

    public void setEstadoTipoDocumento(Estado estadoTipoDocumento) {
        this.estadoTipoDocumento = estadoTipoDocumento;
    }

    public Set<Medico> getMedicos() {
        return medicos;
    }

    public TipoDocumento medicos(Set<Medico> medicos) {
        this.medicos = medicos;
        return this;
    }

    public TipoDocumento addMedicos(Medico medico) {
        this.medicos.add(medico);
        medico.setTipoDocumento(this);
        return this;
    }

    public TipoDocumento removeMedicos(Medico medico) {
        this.medicos.remove(medico);
        medico.setTipoDocumento(null);
        return this;
    }

    public void setMedicos(Set<Medico> medicos) {
        this.medicos = medicos;
    }

    public Set<Paciente> getPacientes() {
        return pacientes;
    }

    public TipoDocumento pacientes(Set<Paciente> pacientes) {
        this.pacientes = pacientes;
        return this;
    }

    public TipoDocumento addPacientes(Paciente paciente) {
        this.pacientes.add(paciente);
        paciente.setTipoDocumento(this);
        return this;
    }

    public TipoDocumento removePacientes(Paciente paciente) {
        this.pacientes.remove(paciente);
        paciente.setTipoDocumento(null);
        return this;
    }

    public void setPacientes(Set<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TipoDocumento)) {
            return false;
        }
        return id != null && id.equals(((TipoDocumento) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TipoDocumento{" +
            "id=" + getId() +
            ", iniciales='" + getIniciales() + "'" +
            ", nombreDocumento='" + getNombreDocumento() + "'" +
            ", estadoTipoDocumento='" + getEstadoTipoDocumento() + "'" +
            "}";
    }
}
