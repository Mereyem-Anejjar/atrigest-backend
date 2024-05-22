package  ma.zs.task.dao.specification.core.soc;

import ma.zs.task.dao.criteria.core.soc.SocieteCriteria;
import ma.zs.task.bean.core.soc.Societe;
import ma.zs.task.zynerator.specification.AbstractSpecification;


public class SocieteSpecification extends  AbstractSpecification<SocieteCriteria, Societe>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("nom", criteria.getNom(),criteria.getNomLike());
        addPredicate("dateCreation", criteria.getDateCreation(), criteria.getDateCreationFrom(), criteria.getDateCreationTo());
        addPredicate("rc", criteria.getRc(),criteria.getRcLike());
        addPredicate("taxeProfessionnelle", criteria.getTaxeProfessionnelle(),criteria.getTaxeProfessionnelleLike());
        addPredicate("ice", criteria.getIce(),criteria.getIceLike());
        addPredicate("gerant", criteria.getGerant(),criteria.getGerantLike());
        addPredicateFk("type","id", criteria.getType()==null?null:criteria.getType().getId());
        addPredicateFk("type","id", criteria.getTypes());
        addPredicateFk("type","code", criteria.getType()==null?null:criteria.getType().getCode());
    }

    public SocieteSpecification(SocieteCriteria criteria) {
        super(criteria);
    }

    public SocieteSpecification(SocieteCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
