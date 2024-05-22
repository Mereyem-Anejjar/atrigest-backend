package ma.zs.task.bean.core.entite;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.task.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "entite_externe")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="entite_externe_seq",sequenceName="entite_externe_seq",allocationSize=1, initialValue = 1)
public class EntiteExterne  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String nom;
    @Column(length = 500)
    private String email;
    @Column(length = 500)
    private String tel;

    private TypeEntiteExterne typeEntiteExterne ;


    public EntiteExterne(){
        super();
    }

    public EntiteExterne(Long id,String nom){
        this.id = id;
        this.nom = nom ;
    }
    public EntiteExterne(String nom){
        this.nom = nom ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="entite_externe_seq")
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
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getTel(){
        return this.tel;
    }
    public void setTel(String tel){
        this.tel = tel;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_entite_externe")
    public TypeEntiteExterne getTypeEntiteExterne(){
        return this.typeEntiteExterne;
    }
    public void setTypeEntiteExterne(TypeEntiteExterne typeEntiteExterne){
        this.typeEntiteExterne = typeEntiteExterne;
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
        EntiteExterne entiteExterne = (EntiteExterne) o;
        return id != null && id.equals(entiteExterne.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

