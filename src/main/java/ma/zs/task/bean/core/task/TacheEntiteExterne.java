package ma.zs.task.bean.core.task;

import java.util.Objects;





import ma.zs.task.bean.core.entite.EntiteExterne;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.task.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tache_entite_externe")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="tache_entite_externe_seq",sequenceName="tache_entite_externe_seq",allocationSize=1, initialValue = 1)
public class TacheEntiteExterne  extends BaseEntity     {

    private Long id;


    private EntiteExterne entiteExterne ;
    private Tache tache ;


    public TacheEntiteExterne(){
        super();
    }





    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="tache_entite_externe_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entite_externe")
    public EntiteExterne getEntiteExterne(){
        return this.entiteExterne;
    }
    public void setEntiteExterne(EntiteExterne entiteExterne){
        this.entiteExterne = entiteExterne;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tache")
    public Tache getTache(){
        return this.tache;
    }
    public void setTache(Tache tache){
        this.tache = tache;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TacheEntiteExterne tacheEntiteExterne = (TacheEntiteExterne) o;
        return id != null && id.equals(tacheEntiteExterne.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

