package  ma.zs.task.ws.dto.soc;

import ma.zs.task.zynerator.audit.Log;
import ma.zs.task.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypeSocieteDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;




    public TypeSocieteDto(){
        super();
    }



    @Log
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }

    @Log
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }








}
