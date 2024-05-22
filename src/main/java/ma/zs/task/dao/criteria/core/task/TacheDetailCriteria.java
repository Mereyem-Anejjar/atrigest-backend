package  ma.zs.task.dao.criteria.core.task;


import ma.zs.task.dao.criteria.core.commun.EtatAvancementCriteria;
import ma.zs.task.dao.criteria.core.utilisateur.UtilisateurCriteria;

import ma.zs.task.zynerator.criteria.BaseCriteria;
import java.util.List;

public class TacheDetailCriteria extends  BaseCriteria  {

    private String description;
    private String descriptionLike;

    private UtilisateurCriteria utilisateur ;
    private List<UtilisateurCriteria> utilisateurs ;
    private EtatAvancementCriteria etatAvancement ;
    private List<EtatAvancementCriteria> etatAvancements ;
    private TacheCriteria tache ;
    private List<TacheCriteria> taches ;


    public TacheDetailCriteria(){}

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
    }


    public UtilisateurCriteria getUtilisateur(){
        return this.utilisateur;
    }

    public void setUtilisateur(UtilisateurCriteria utilisateur){
        this.utilisateur = utilisateur;
    }
    public List<UtilisateurCriteria> getUtilisateurs(){
        return this.utilisateurs;
    }

    public void setUtilisateurs(List<UtilisateurCriteria> utilisateurs){
        this.utilisateurs = utilisateurs;
    }
    public EtatAvancementCriteria getEtatAvancement(){
        return this.etatAvancement;
    }

    public void setEtatAvancement(EtatAvancementCriteria etatAvancement){
        this.etatAvancement = etatAvancement;
    }
    public List<EtatAvancementCriteria> getEtatAvancements(){
        return this.etatAvancements;
    }

    public void setEtatAvancements(List<EtatAvancementCriteria> etatAvancements){
        this.etatAvancements = etatAvancements;
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
