package ma.zs.task.bean.core.soc;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.task.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "associe")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="associe_seq",sequenceName="associe_seq",allocationSize=1, initialValue = 1)
public class Associe  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String nom;

    private Societe societe ;
    private RoleAssocie roleAssocie ;


    public Associe(){
        super();
    }

    public Associe(Long id,String nom){
        this.id = id;
        this.nom = nom ;
    }
    public Associe(String nom){
        this.nom = nom ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="associe_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "societe")
    public Societe getSociete(){
        return this.societe;
    }
    public void setSociete(Societe societe){
        this.societe = societe;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_associe")
    public RoleAssocie getRoleAssocie(){
        return this.roleAssocie;
    }
    public void setRoleAssocie(RoleAssocie roleAssocie){
        this.roleAssocie = roleAssocie;
    }

    @Transient
    public String getLabel() {
        label = nom;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Associe associe = (Associe) o;
        return id != null && id.equals(associe.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

