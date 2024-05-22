package  ma.zs.task.ws.dto.entite;

import ma.zs.task.zynerator.audit.Log;
import ma.zs.task.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntiteExterneDto  extends AuditBaseDto {

    private String nom  ;
    private String email  ;
    private String tel  ;

    private TypeEntiteExterneDto typeEntiteExterne ;



    public EntiteExterneDto(){
        super();
    }



    @Log
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }

    @Log
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    @Log
    public String getTel(){
        return this.tel;
    }
    public void setTel(String tel){
        this.tel = tel;
    }


    public TypeEntiteExterneDto getTypeEntiteExterne(){
        return this.typeEntiteExterne;
    }

    public void setTypeEntiteExterne(TypeEntiteExterneDto typeEntiteExterne){
        this.typeEntiteExterne = typeEntiteExterne;
    }






}
