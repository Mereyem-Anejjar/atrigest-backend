package  ma.zs.task.dao.specification.core.commun;

import ma.zs.task.dao.criteria.core.commun.NationaliteCriteria;
import ma.zs.task.bean.core.commun.Nationalite;
import ma.zs.task.zynerator.specification.AbstractSpecification;


public class NationaliteSpecification extends  AbstractSpecification<NationaliteCriteria, Nationalite>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public NationaliteSpecification(NationaliteCriteria criteria) {
        super(criteria);
    }

    public NationaliteSpecification(NationaliteCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
