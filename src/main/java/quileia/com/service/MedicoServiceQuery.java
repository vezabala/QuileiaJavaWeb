package quileia.com.service;
import io.github.jhipster.service.QueryService;
import io.github.jhipster.service.filter.StringFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quileia.com.Criteria.MedicoCriteria;
import quileia.com.domain.*;
import quileia.com.repository.MedicoRepository;

import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MedicoServiceQuery extends QueryService<Medico> {
    @Autowired
    MedicoRepository medicoRepository;

    public List<Medico> findByCriterial (MedicoCriteria medicoCriteria){
        final Specification<Medico> specification = createSpecification(medicoCriteria);
        List<Medico> medicos = medicoRepository.findAll(specification);
        return medicos;
    }
    private Specification<Medico> createSpecification (MedicoCriteria criteria){
        Specification<Medico> specification = Specification.where(null);
        if (criteria != null ){
            if(criteria.getIdentificacion() != null){
                specification =
                    specification.and(buildStringSpecification(criteria.getIdentificacion() , Medico_.identificacion));
            }
            if(criteria.getNombreCompleto() != null){
                specification =
                    specification.and(buildStringSpecification(criteria.getNombreCompleto() , Medico_.nombreCompleto));
            }
            if(criteria.getEspecialidad() != null){
                specification =
                    specification.and(buildReferringEntitySpecification(criteria.getEspecialidad(), Medico_.especialidad , Especialidad_.nombreEspecialidad));
            }
            if(criteria.getFranjaHoraria() != null){
                specification =
                    specification.and(buildReferringEntitySpecification(criteria.getFranjaHoraria(), Medico_.franjaHoraria , FranjaHoraria_.franja));
            }
        }
        return specification;
    }
}
