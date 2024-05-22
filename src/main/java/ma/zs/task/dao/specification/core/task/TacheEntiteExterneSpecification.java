package  ma.zs.task.dao.specification.core.task;

import ma.zs.task.dao.criteria.core.task.TacheEntiteExterneCriteria;
import ma.zs.task.bean.core.task.TacheEntiteExterne;
import ma.zs.task.zynerator.specification.AbstractSpecification;


public class TacheEntiteExterneSpecification extends  AbstractSpecification<TacheEntiteExterneCriteria, TacheEntiteExterne>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateFk("entiteExterne","id", criteria.getEntiteExterne()==null?null:criteria.getEntiteExterne().getId());
        addPredicateFk("entiteExterne","id", criteria.getEntiteExternes());
        addPredicateFk("tache","id", criteria.getTache()==null?null:criteria.getTache().getId());
        addPredicateFk("tache","id", criteria.getTaches());
    }

    public TacheEntiteExterneSpecification(TacheEntiteExterneCriteria criteria) {
        super(criteria);
    }

    public TacheEntiteExterneSpecification(TacheEntiteExterneCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
