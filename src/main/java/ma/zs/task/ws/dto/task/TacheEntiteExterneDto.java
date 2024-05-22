package  ma.zs.task.ws.dto.task;

import ma.zs.task.zynerator.audit.Log;
import ma.zs.task.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zs.task.ws.dto.entite.EntiteExterneDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class TacheEntiteExterneDto  extends AuditBaseDto {


    private EntiteExterneDto entiteExterne ;
    private TacheDto tache ;



    public TacheEntiteExterneDto(){
        super();
    }




    public EntiteExterneDto getEntiteExterne(){
        return this.entiteExterne;
    }

    public void setEntiteExterne(EntiteExterneDto entiteExterne){
        this.entiteExterne = entiteExterne;
    }
    public TacheDto getTache(){
        return this.tache;
    }

    public void setTache(TacheDto tache){
        this.tache = tache;
    }






}
