package  ma.zs.task.dao.criteria.core.soc;



import ma.zs.task.zynerator.criteria.BaseCriteria;
import java.util.List;

public class AssocieCriteria extends  BaseCriteria  {

    private String nom;
    private String nomLike;

    private SocieteCriteria societe ;
    private List<SocieteCriteria> societes ;
    private RoleAssocieCriteria roleAssocie ;
    private List<RoleAssocieCriteria> roleAssocies ;


    public AssocieCriteria(){}

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


    public SocieteCriteria getSociete(){
        return this.societe;
    }

    public void setSociete(SocieteCriteria societe){
        this.societe = societe;
    }
    public List<SocieteCriteria> getSocietes(){
        return this.societes;
    }

    public void setSocietes(List<SocieteCriteria> societes){
        this.societes = societes;
    }
    public RoleAssocieCriteria getRoleAssocie(){
        return this.roleAssocie;
    }

    public void setRoleAssocie(RoleAssocieCriteria roleAssocie){
        this.roleAssocie = roleAssocie;
    }
    public List<RoleAssocieCriteria> getRoleAssocies(){
        return this.roleAssocies;
    }

    public void setRoleAssocies(List<RoleAssocieCriteria> roleAssocies){
        this.roleAssocies = roleAssocies;
    }
}
