package  ma.zs.task.dao.specification.core.entite;

import ma.zs.task.dao.criteria.core.entite.TypeEntiteExterneCriteria;
import ma.zs.task.bean.core.entite.TypeEntiteExterne;
import ma.zs.task.zynerator.specification.AbstractSpecification;


public class TypeEntiteExterneSpecification extends  AbstractSpecification<TypeEntiteExterneCriteria, TypeEntiteExterne>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public TypeEntiteExterneSpecification(TypeEntiteExterneCriteria criteria) {
        super(criteria);
    }

    public TypeEntiteExterneSpecification(TypeEntiteExterneCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
