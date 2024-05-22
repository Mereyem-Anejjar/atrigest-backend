package  ma.zs.task.dao.specification.core.task;

import ma.zs.task.dao.criteria.core.task.TacheDetailCriteria;
import ma.zs.task.bean.core.task.TacheDetail;
import ma.zs.task.zynerator.specification.AbstractSpecification;


public class TacheDetailSpecification extends  AbstractSpecification<TacheDetailCriteria, TacheDetail>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateFk("utilisateur","id", criteria.getUtilisateur()==null?null:criteria.getUtilisateur().getId());
        addPredicateFk("utilisateur","id", criteria.getUtilisateurs());
        addPredicateFk("etatAvancement","id", criteria.getEtatAvancement()==null?null:criteria.getEtatAvancement().getId());
        addPredicateFk("etatAvancement","id", criteria.getEtatAvancements());
        addPredicateFk("etatAvancement","code", criteria.getEtatAvancement()==null?null:criteria.getEtatAvancement().getCode());
        addPredicateFk("tache","id", criteria.getTache()==null?null:criteria.getTache().getId());
        addPredicateFk("tache","id", criteria.getTaches());
    }

    public TacheDetailSpecification(TacheDetailCriteria criteria) {
        super(criteria);
    }

    public TacheDetailSpecification(TacheDetailCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
