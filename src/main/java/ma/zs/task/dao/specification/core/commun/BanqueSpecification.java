package  ma.zs.task.dao.specification.core.commun;

import ma.zs.task.dao.criteria.core.commun.BanqueCriteria;
import ma.zs.task.bean.core.commun.Banque;
import ma.zs.task.zynerator.specification.AbstractSpecification;


public class BanqueSpecification extends  AbstractSpecification<BanqueCriteria, Banque>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public BanqueSpecification(BanqueCriteria criteria) {
        super(criteria);
    }

    public BanqueSpecification(BanqueCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
