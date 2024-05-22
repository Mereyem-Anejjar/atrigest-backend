package  ma.zs.task.dao.specification.core.commun;

import ma.zs.task.dao.criteria.core.commun.EtatAvancementCriteria;
import ma.zs.task.bean.core.commun.EtatAvancement;
import ma.zs.task.zynerator.specification.AbstractSpecification;


public class EtatAvancementSpecification extends  AbstractSpecification<EtatAvancementCriteria, EtatAvancement>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public EtatAvancementSpecification(EtatAvancementCriteria criteria) {
        super(criteria);
    }

    public EtatAvancementSpecification(EtatAvancementCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
