package  ma.zs.task.dao.specification.core.soc;

import ma.zs.task.dao.criteria.core.soc.RoleAssocieCriteria;
import ma.zs.task.bean.core.soc.RoleAssocie;
import ma.zs.task.zynerator.specification.AbstractSpecification;


public class RoleAssocieSpecification extends  AbstractSpecification<RoleAssocieCriteria, RoleAssocie>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public RoleAssocieSpecification(RoleAssocieCriteria criteria) {
        super(criteria);
    }

    public RoleAssocieSpecification(RoleAssocieCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
