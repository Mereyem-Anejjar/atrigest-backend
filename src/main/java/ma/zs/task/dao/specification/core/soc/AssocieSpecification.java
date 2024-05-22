package  ma.zs.task.dao.specification.core.soc;

import ma.zs.task.dao.criteria.core.soc.AssocieCriteria;
import ma.zs.task.bean.core.soc.Associe;
import ma.zs.task.zynerator.specification.AbstractSpecification;


public class AssocieSpecification extends  AbstractSpecification<AssocieCriteria, Associe>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("nom", criteria.getNom(),criteria.getNomLike());
        addPredicateFk("societe","id", criteria.getSociete()==null?null:criteria.getSociete().getId());
        addPredicateFk("societe","id", criteria.getSocietes());
        addPredicateFk("roleAssocie","id", criteria.getRoleAssocie()==null?null:criteria.getRoleAssocie().getId());
        addPredicateFk("roleAssocie","id", criteria.getRoleAssocies());
        addPredicateFk("roleAssocie","code", criteria.getRoleAssocie()==null?null:criteria.getRoleAssocie().getCode());
    }

    public AssocieSpecification(AssocieCriteria criteria) {
        super(criteria);
    }

    public AssocieSpecification(AssocieCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
