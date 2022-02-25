package quileia.com.service;
import io.github.jhipster.service.QueryService;
import io.github.jhipster.service.filter.StringFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quileia.com.Criteria.MedicoCriteria;
import quileia.com.domain.FranjaHoraria;
import quileia.com.domain.FranjaHoraria_;
import quileia.com.domain.Medico;
import quileia.com.domain.Medico_;
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
    private Specification<Medico> createSpecification (MedicoCriteria medicoCriteria){
        Specification<Medico> specification = Specification.where(null);
        if (medicoCriteria != null ){
            if(medicoCriteria.getIdentificacion() != null){
                specification =
                    specification.and(buildStringSpecification(medicoCriteria.getIdentificacion() , Medico_.identificacion));
            }
            if(medicoCriteria.getIdentificacion() != null){
                specification =
                    specification.and(buildStringSpecification(medicoCriteria.getNombreCompleto() , Medico_.nombreCompleto));
            }
            if(medicoCriteria.getFranjaHoraria() != null){
                specification =
                    specification.and(buildReferringEntitySpecification(medicoCriteria.getFranjaHoraria(), Medico_.franjaHoraria , FranjaHoraria_.franja));
            }
        }
        return specification;
    }
    public List<Medico> findAll(){
        return medicoRepository.findAll();
    }
}
