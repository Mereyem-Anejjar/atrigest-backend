package  ma.zs.task.dao.specification.core.commun;

import ma.zs.task.dao.criteria.core.commun.TypeIdentiteCriteria;
import ma.zs.task.bean.core.commun.TypeIdentite;
import ma.zs.task.zynerator.specification.AbstractSpecification;


public class TypeIdentiteSpecification extends  AbstractSpecification<TypeIdentiteCriteria, TypeIdentite>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public TypeIdentiteSpecification(TypeIdentiteCriteria criteria) {
        super(criteria);
    }

    public TypeIdentiteSpecification(TypeIdentiteCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
