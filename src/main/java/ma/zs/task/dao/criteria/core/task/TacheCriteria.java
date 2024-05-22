package  ma.zs.task.dao.criteria.core.task;


import ma.zs.task.dao.criteria.core.dc.DossierClientCriteria;
import ma.zs.task.dao.criteria.core.commun.EtatAvancementCriteria;
import ma.zs.task.dao.criteria.core.commun.PrioriteCriteria;

import ma.zs.task.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class TacheCriteria extends  BaseCriteria  {

    private String description;
    private String descriptionLike;
    private LocalDateTime dateLimite;
    private LocalDateTime dateLimiteFrom;
    private LocalDateTime dateLimiteTo;

    private PrioriteCriteria priorite ;
    private List<PrioriteCriteria> priorites ;
    private EtatAvancementCriteria etatAvancement ;
    private List<EtatAvancementCriteria> etatAvancements ;
    private DossierClientCriteria dossierClient ;
    private List<DossierClientCriteria> dossierClients ;


    public TacheCriteria(){}

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

    public LocalDateTime getDateLimite(){
        return this.dateLimite;
    }
    public void setDateLimite(LocalDateTime dateLimite){
        this.dateLimite = dateLimite;
    }
    public LocalDateTime getDateLimiteFrom(){
        return this.dateLimiteFrom;
    }
    public void setDateLimiteFrom(LocalDateTime dateLimiteFrom){
        this.dateLimiteFrom = dateLimiteFrom;
    }
    public LocalDateTime getDateLimiteTo(){
        return this.dateLimiteTo;
    }
    public void setDateLimiteTo(LocalDateTime dateLimiteTo){
        this.dateLimiteTo = dateLimiteTo;
    }

    public PrioriteCriteria getPriorite(){
        return this.priorite;
    }

    public void setPriorite(PrioriteCriteria priorite){
        this.priorite = priorite;
    }
    public List<PrioriteCriteria> getPriorites(){
        return this.priorites;
    }

    public void setPriorites(List<PrioriteCriteria> priorites){
        this.priorites = priorites;
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
    public DossierClientCriteria getDossierClient(){
        return this.dossierClient;
    }

    public void setDossierClient(DossierClientCriteria dossierClient){
        this.dossierClient = dossierClient;
    }
    public List<DossierClientCriteria> getDossierClients(){
        return this.dossierClients;
    }

    public void setDossierClients(List<DossierClientCriteria> dossierClients){
        this.dossierClients = dossierClients;
    }
}
