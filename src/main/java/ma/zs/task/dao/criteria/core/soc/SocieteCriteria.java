package  ma.zs.task.dao.criteria.core.soc;



import ma.zs.task.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class SocieteCriteria extends  BaseCriteria  {

    private String nom;
    private String nomLike;
    private LocalDateTime dateCreation;
    private LocalDateTime dateCreationFrom;
    private LocalDateTime dateCreationTo;
    private String rc;
    private String rcLike;
    private String taxeProfessionnelle;
    private String taxeProfessionnelleLike;
    private String ice;
    private String iceLike;
    private String gerant;
    private String gerantLike;

    private TypeSocieteCriteria type ;
    private List<TypeSocieteCriteria> types ;


    public SocieteCriteria(){}

    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public String getNomLike(){
        return this.nomLike;
    }
    public void setNomLike(String nomLike){
        this.nomLike = nomLike;
    }

    public LocalDateTime getDateCreation(){
        return this.dateCreation;
    }
    public void setDateCreation(LocalDateTime dateCreation){
        this.dateCreation = dateCreation;
    }
    public LocalDateTime getDateCreationFrom(){
        return this.dateCreationFrom;
    }
    public void setDateCreationFrom(LocalDateTime dateCreationFrom){
        this.dateCreationFrom = dateCreationFrom;
    }
    public LocalDateTime getDateCreationTo(){
        return this.dateCreationTo;
    }
    public void setDateCreationTo(LocalDateTime dateCreationTo){
        this.dateCreationTo = dateCreationTo;
    }
    public String getRc(){
        return this.rc;
    }
    public void setRc(String rc){
        this.rc = rc;
    }
    public String getRcLike(){
        return this.rcLike;
    }
    public void setRcLike(String rcLike){
        this.rcLike = rcLike;
    }

    public String getTaxeProfessionnelle(){
        return this.taxeProfessionnelle;
    }
    public void setTaxeProfessionnelle(String taxeProfessionnelle){
        this.taxeProfessionnelle = taxeProfessionnelle;
    }
    public String getTaxeProfessionnelleLike(){
        return this.taxeProfessionnelleLike;
    }
    public void setTaxeProfessionnelleLike(String taxeProfessionnelleLike){
        this.taxeProfessionnelleLike = taxeProfessionnelleLike;
    }

    public String getIce(){
        return this.ice;
    }
    public void setIce(String ice){
        this.ice = ice;
    }
    public String getIceLike(){
        return this.iceLike;
    }
    public void setIceLike(String iceLike){
        this.iceLike = iceLike;
    }

    public String getGerant(){
        return this.gerant;
    }
    public void setGerant(String gerant){
        this.gerant = gerant;
    }
    public String getGerantLike(){
        return this.gerantLike;
    }
    public void setGerantLike(String gerantLike){
        this.gerantLike = gerantLike;
    }


    public TypeSocieteCriteria getType(){
        return this.type;
    }

    public void setType(TypeSocieteCriteria type){
        this.type = type;
    }
    public List<TypeSocieteCriteria> getTypes(){
        return this.types;
    }

    public void setTypes(List<TypeSocieteCriteria> types){
        this.types = types;
    }
}
