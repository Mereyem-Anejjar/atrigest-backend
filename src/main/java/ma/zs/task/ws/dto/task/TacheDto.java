package  ma.zs.task.ws.dto.task;

import ma.zs.task.zynerator.audit.Log;
import ma.zs.task.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.task.ws.dto.dc.DossierClientDto;
import ma.zs.task.ws.dto.entite.EntiteExterneDto;
import ma.zs.task.ws.dto.commun.EtatAvancementDto;
import ma.zs.task.ws.dto.commun.PrioriteDto;
import ma.zs.task.ws.dto.utilisateur.UtilisateurDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class TacheDto  extends AuditBaseDto {

    private String description  ;
    private String dateLimite ;

    private PrioriteDto priorite ;
    private EtatAvancementDto etatAvancement ;
    private DossierClientDto dossierClient ;

    private List<TacheDetailDto> tacheDetails ;
    private List<TacheEntiteExterneDto> tacheEntiteExternes ;


    public TacheDto(){
        super();
    }



    @Log
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateLimite(){
        return this.dateLimite;
    }
    public void setDateLimite(String dateLimite){
        this.dateLimite = dateLimite;
    }


    public PrioriteDto getPriorite(){
        return this.priorite;
    }

    public void setPriorite(PrioriteDto priorite){
        this.priorite = priorite;
    }
    public EtatAvancementDto getEtatAvancement(){
        return this.etatAvancement;
    }

    public void setEtatAvancement(EtatAvancementDto etatAvancement){
        this.etatAvancement = etatAvancement;
    }
    public DossierClientDto getDossierClient(){
        return this.dossierClient;
    }

    public void setDossierClient(DossierClientDto dossierClient){
        this.dossierClient = dossierClient;
    }



    public List<TacheDetailDto> getTacheDetails(){
        return this.tacheDetails;
    }

    public void setTacheDetails(List<TacheDetailDto> tacheDetails){
        this.tacheDetails = tacheDetails;
    }
    public List<TacheEntiteExterneDto> getTacheEntiteExternes(){
        return this.tacheEntiteExternes;
    }

    public void setTacheEntiteExternes(List<TacheEntiteExterneDto> tacheEntiteExternes){
        this.tacheEntiteExternes = tacheEntiteExternes;
    }



}
