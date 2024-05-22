package  ma.zs.task.ws.dto.task;

import ma.zs.task.zynerator.audit.Log;
import ma.zs.task.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zs.task.ws.dto.commun.EtatAvancementDto;
import ma.zs.task.ws.dto.utilisateur.UtilisateurDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class TacheDetailDto  extends AuditBaseDto {

    private String description  ;

    private UtilisateurDto utilisateur ;
    private EtatAvancementDto etatAvancement ;
    private TacheDto tache ;



    public TacheDetailDto(){
        super();
    }



    @Log
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    public UtilisateurDto getUtilisateur(){
        return this.utilisateur;
    }

    public void setUtilisateur(UtilisateurDto utilisateur){
        this.utilisateur = utilisateur;
    }
    public EtatAvancementDto getEtatAvancement(){
        return this.etatAvancement;
    }

    public void setEtatAvancement(EtatAvancementDto etatAvancement){
        this.etatAvancement = etatAvancement;
    }
    public TacheDto getTache(){
        return this.tache;
    }

    public void setTache(TacheDto tache){
        this.tache = tache;
    }






}
