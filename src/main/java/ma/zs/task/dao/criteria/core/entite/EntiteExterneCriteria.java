package  ma.zs.task.dao.criteria.core.entite;



import ma.zs.task.zynerator.criteria.BaseCriteria;
import java.util.List;

public class EntiteExterneCriteria extends  BaseCriteria  {

    private String nom;
    private String nomLike;
    private String email;
    private String emailLike;
    private String tel;
    private String telLike;

    private TypeEntiteExterneCriteria typeEntiteExterne ;
    private List<TypeEntiteExterneCriteria> typeEntiteExternes ;


    public EntiteExterneCriteria(){}

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

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmailLike(){
        return this.emailLike;
    }
    public void setEmailLike(String emailLike){
        this.emailLike = emailLike;
    }

    public String getTel(){
        return this.tel;
    }
    public void setTel(String tel){
        this.tel = tel;
    }
    public String getTelLike(){
        return this.telLike;
    }
    public void setTelLike(String telLike){
        this.telLike = telLike;
    }


    public TypeEntiteExterneCriteria getTypeEntiteExterne(){
        return this.typeEntiteExterne;
    }

    public void setTypeEntiteExterne(TypeEntiteExterneCriteria typeEntiteExterne){
        this.typeEntiteExterne = typeEntiteExterne;
    }
    public List<TypeEntiteExterneCriteria> getTypeEntiteExternes(){
        return this.typeEntiteExternes;
    }

    public void setTypeEntiteExternes(List<TypeEntiteExterneCriteria> typeEntiteExternes){
        this.typeEntiteExternes = typeEntiteExternes;
    }
}
