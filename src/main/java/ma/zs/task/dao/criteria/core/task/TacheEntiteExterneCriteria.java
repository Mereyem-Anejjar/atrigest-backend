package  ma.zs.task.dao.criteria.core.task;


import ma.zs.task.dao.criteria.core.entite.EntiteExterneCriteria;

import ma.zs.task.zynerator.criteria.BaseCriteria;
import java.util.List;

public class TacheEntiteExterneCriteria extends  BaseCriteria  {


    private EntiteExterneCriteria entiteExterne ;
    private List<EntiteExterneCriteria> entiteExternes ;
    private TacheCriteria tache ;
    private List<TacheCriteria> taches ;


    public TacheEntiteExterneCriteria(){}


    public EntiteExterneCriteria getEntiteExterne(){
        return this.entiteExterne;
    }

    public void setEntiteExterne(EntiteExterneCriteria entiteExterne){
        this.entiteExterne = entiteExterne;
    }
    public List<EntiteExterneCriteria> getEntiteExternes(){
        return this.entiteExternes;
    }

    public void setEntiteExternes(List<EntiteExterneCriteria> entiteExternes){
        this.entiteExternes = entiteExternes;
    }
    public TacheCriteria getTache(){
        return this.tache;
    }

    public void setTache(TacheCriteria tache){
        this.tache = tache;
    }
    public List<TacheCriteria> getTaches(){
        return this.taches;
    }

    public void setTaches(List<TacheCriteria> taches){
        this.taches = taches;
    }
}
