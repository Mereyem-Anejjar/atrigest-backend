package  ma.zs.task.dao.specification.core.entite;

import ma.zs.task.dao.criteria.core.entite.EntiteExterneCriteria;
import ma.zs.task.bean.core.entite.EntiteExterne;
import ma.zs.task.zynerator.specification.AbstractSpecification;


public class EntiteExterneSpecification extends  AbstractSpecification<EntiteExterneCriteria, EntiteExterne>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("nom", criteria.getNom(),criteria.getNomLike());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicate("tel", criteria.getTel(),criteria.getTelLike());
        addPredicateFk("typeEntiteExterne","id", criteria.getTypeEntiteExterne()==null?null:criteria.getTypeEntiteExterne().getId());
        addPredicateFk("typeEntiteExterne","id", criteria.getTypeEntiteExternes());
        addPredicateFk("typeEntiteExterne","code", criteria.getTypeEntiteExterne()==null?null:criteria.getTypeEntiteExterne().getCode());
    }

    public EntiteExterneSpecification(EntiteExterneCriteria criteria) {
        super(criteria);
    }

    public EntiteExterneSpecification(EntiteExterneCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
