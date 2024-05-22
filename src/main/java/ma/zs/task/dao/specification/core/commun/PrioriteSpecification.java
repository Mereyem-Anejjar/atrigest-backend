package  ma.zs.task.dao.specification.core.commun;

import ma.zs.task.dao.criteria.core.commun.PrioriteCriteria;
import ma.zs.task.bean.core.commun.Priorite;
import ma.zs.task.zynerator.specification.AbstractSpecification;


public class PrioriteSpecification extends  AbstractSpecification<PrioriteCriteria, Priorite>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public PrioriteSpecification(PrioriteCriteria criteria) {
        super(criteria);
    }

    public PrioriteSpecification(PrioriteCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
