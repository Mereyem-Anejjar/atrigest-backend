package  ma.zs.task.dao.specification.core.soc;

import ma.zs.task.dao.criteria.core.soc.TypeSocieteCriteria;
import ma.zs.task.bean.core.soc.TypeSociete;
import ma.zs.task.zynerator.specification.AbstractSpecification;


public class TypeSocieteSpecification extends  AbstractSpecification<TypeSocieteCriteria, TypeSociete>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public TypeSocieteSpecification(TypeSocieteCriteria criteria) {
        super(criteria);
    }

    public TypeSocieteSpecification(TypeSocieteCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
