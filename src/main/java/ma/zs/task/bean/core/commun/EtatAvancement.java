package ma.zs.task.bean.core.commun;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.task.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "etat_avancement")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="etat_avancement_seq",sequenceName="etat_avancement_seq",allocationSize=1, initialValue = 1)
public class EtatAvancement  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String code;
    @Column(length = 500)
    private String libelle;



    public EtatAvancement(){
        super();
    }

    public EtatAvancement(Long id,String code){
        this.id = id;
        this.code = code ;
    }
    public EtatAvancement(String code){
        this.code = code ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="etat_avancement_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }

    @Transient
    public String getLabel() {
        label = code;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EtatAvancement etatAvancement = (EtatAvancement) o;
        return id != null && id.equals(etatAvancement.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

