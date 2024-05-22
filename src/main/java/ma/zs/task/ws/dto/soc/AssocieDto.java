package  ma.zs.task.ws.dto.soc;

import ma.zs.task.zynerator.audit.Log;
import ma.zs.task.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssocieDto  extends AuditBaseDto {

    private String nom  ;

    private SocieteDto societe ;
    private RoleAssocieDto roleAssocie ;



    public AssocieDto(){
        super();
    }



    @Log
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }


    public SocieteDto getSociete(){
        return this.societe;
    }

    public void setSociete(SocieteDto societe){
        this.societe = societe;
    }
    public RoleAssocieDto getRoleAssocie(){
        return this.roleAssocie;
    }

    public void setRoleAssocie(RoleAssocieDto roleAssocie){
        this.roleAssocie = roleAssocie;
    }






}
