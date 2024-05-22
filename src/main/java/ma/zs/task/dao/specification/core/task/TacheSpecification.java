package  ma.zs.task.dao.specification.core.task;

import ma.zs.task.dao.criteria.core.task.TacheCriteria;
import ma.zs.task.bean.core.task.Tache;
import ma.zs.task.zynerator.specification.AbstractSpecification;


public class TacheSpecification extends  AbstractSpecification<TacheCriteria, Tache>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("dateLimite", criteria.getDateLimite(), criteria.getDateLimiteFrom(), criteria.getDateLimiteTo());
        addPredicateFk("priorite","id", criteria.getPriorite()==null?null:criteria.getPriorite().getId());
        addPredicateFk("priorite","id", criteria.getPriorites());
        addPredicateFk("priorite","code", criteria.getPriorite()==null?null:criteria.getPriorite().getCode());
        addPredicateFk("etatAvancement","id", criteria.getEtatAvancement()==null?null:criteria.getEtatAvancement().getId());
        addPredicateFk("etatAvancement","id", criteria.getEtatAvancements());
        addPredicateFk("etatAvancement","code", criteria.getEtatAvancement()==null?null:criteria.getEtatAvancement().getCode());
        addPredicateFk("dossierClient","id", criteria.getDossierClient()==null?null:criteria.getDossierClient().getId());
        addPredicateFk("dossierClient","id", criteria.getDossierClients());
    }

    public TacheSpecification(TacheCriteria criteria) {
        super(criteria);
    }

    public TacheSpecification(TacheCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
