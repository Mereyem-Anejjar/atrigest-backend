package  ma.zs.task.ws.dto.soc;

import ma.zs.task.zynerator.audit.Log;
import ma.zs.task.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class SocieteDto  extends AuditBaseDto {

    private String nom  ;
    private String dateCreation ;
    private String rc  ;
    private String taxeProfessionnelle  ;
    private String ice  ;
    private String gerant  ;

    private TypeSocieteDto type ;

    private List<AssocieDto> associes ;


    public SocieteDto(){
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateCreation(){
        return this.dateCreation;
    }
    public void setDateCreation(String dateCreation){
        this.dateCreation = dateCreation;
    }

    @Log
    public String getRc(){
        return this.rc;
    }
    public void setRc(String rc){
        this.rc = rc;
    }

    @Log
    public String getTaxeProfessionnelle(){
        return this.taxeProfessionnelle;
    }
    public void setTaxeProfessionnelle(String taxeProfessionnelle){
        this.taxeProfessionnelle = taxeProfessionnelle;
    }

    @Log
    public String getIce(){
        return this.ice;
    }
    public void setIce(String ice){
        this.ice = ice;
    }

    @Log
    public String getGerant(){
        return this.gerant;
    }
    public void setGerant(String gerant){
        this.gerant = gerant;
    }


    public TypeSocieteDto getType(){
        return this.type;
    }

    public void setType(TypeSocieteDto type){
        this.type = type;
    }



    public List<AssocieDto> getAssocies(){
        return this.associes;
    }

    public void setAssocies(List<AssocieDto> associes){
        this.associes = associes;
    }



}
