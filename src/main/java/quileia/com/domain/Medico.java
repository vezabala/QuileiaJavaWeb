package quileia.com.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A Medico.
 */
@Entity
@Table(name = "medico")
public class Medico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGeneratorMedico")
    @SequenceGenerator(name = "sequenceGeneratorMedico")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "nombre_completo", length = 255, nullable = false)
    private String nombreCompleto;

    @NotNull
    @Size(max = 100)
    @Column(name = "identificacion", length = 100, nullable = false, unique = true)
    private String identificacion;

    @NotNull
    @Size(max = 100)
    @Column(name = "tarjeta_profesional", length = 100, nullable = false, unique = true)
    private String tarjetaProfesional;

    @NotNull
    @Column(name = "anos_experiencia", nullable = false)
    private Boolean anosExperiencia;

    @OneToMany(mappedBy = "medicos")
    private Set<Cita> citas = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("medicos")
    private TipoDocumento tipoDocumento;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("medicos")
    private Especialidad especialidad;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("medicos")
    private FranjaHoraria franjaHoraria;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public Medico nombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
        return this;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public Medico identificacion(String identificacion) {
        this.identificacion = identificacion;
        return this;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTarjetaProfesional() {
        return tarjetaProfesional;
    }

    public Medico tarjetaProfesional(String tarjetaProfesional) {
        this.tarjetaProfesional = tarjetaProfesional;
        return this;
    }

    public void setTarjetaProfesional(String tarjetaProfesional) {
        this.tarjetaProfesional = tarjetaProfesional;
    }

    public Boolean isAnosExperiencia() {
        return anosExperiencia;
    }

    public Medico anosExperiencia(Boolean anosExperiencia) {
        this.anosExperiencia = anosExperiencia;
        return this;
    }

    public void setAnosExperiencia(Boolean anosExperiencia) {
        this.anosExperiencia = anosExperiencia;
    }

    public Set<Cita> getCitas() {
        return citas;
    }

    public Medico citas(Set<Cita> citas) {
        this.citas = citas;
        return this;
    }

    public Medico addCitas(Cita cita) {
        this.citas.add(cita);
        cita.setMedicos(this);
        return this;
    }

    public Medico removeCitas(Cita cita) {
        this.citas.remove(cita);
        cita.setMedicos(null);
        return this;
    }

    public void setCitas(Set<Cita> citas) {
        this.citas = citas;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public Medico tipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
        return this;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public Medico especialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
        return this;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public FranjaHoraria getFranjaHoraria() {
        return franjaHoraria;
    }

    public Medico franjaHoraria(FranjaHoraria franjaHoraria) {
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
        if (!(o instanceof Medico)) {
            return false;
        }
        return id != null && id.equals(((Medico) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Medico{" +
            "id=" + getId() +
            ", nombreCompleto='" + getNombreCompleto() + "'" +
            ", identificacion='" + getIdentificacion() + "'" +
            ", tarjetaProfesional='" + getTarjetaProfesional() + "'" +
            ", anosExperiencia='" + isAnosExperiencia() + "'" +
            "}";
    }
}
