package ma.zs.task.bean.core.task;

import java.util.Objects;





import ma.zs.task.bean.core.commun.EtatAvancement;
import ma.zs.task.bean.core.utilisateur.Utilisateur;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.task.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tache_detail")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="tache_detail_seq",sequenceName="tache_detail_seq",allocationSize=1, initialValue = 1)
public class TacheDetail  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String description;

    private Utilisateur utilisateur ;
    private EtatAvancement etatAvancement ;
    private Tache tache ;


    public TacheDetail(){
        super();
    }





    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="tache_detail_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur")
    public Utilisateur getUtilisateur(){
        return this.utilisateur;
    }
    public void setUtilisateur(Utilisateur utilisateur){
        this.utilisateur = utilisateur;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etat_avancement")
    public EtatAvancement getEtatAvancement(){
        return this.etatAvancement;
    }
    public void setEtatAvancement(EtatAvancement etatAvancement){
        this.etatAvancement = etatAvancement;
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
        TacheDetail tacheDetail = (TacheDetail) o;
        return id != null && id.equals(tacheDetail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

