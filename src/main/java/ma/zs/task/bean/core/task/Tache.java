package ma.zs.task.bean.core.task;

import java.util.Objects;
import java.util.List;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.task.bean.core.dc.DossierClient;
import ma.zs.task.bean.core.entite.EntiteExterne;
import ma.zs.task.bean.core.commun.EtatAvancement;
import ma.zs.task.bean.core.commun.Priorite;
import ma.zs.task.bean.core.utilisateur.Utilisateur;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.task.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tache")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="tache_seq",sequenceName="tache_seq",allocationSize=1, initialValue = 1)
public class Tache  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String description;
    private LocalDateTime dateLimite ;

    private Priorite priorite ;
    private EtatAvancement etatAvancement ;
    private DossierClient dossierClient ;

    private List<TacheDetail> tacheDetails ;
    private List<TacheEntiteExterne> tacheEntiteExternes ;

    public Tache(){
        super();
    }





    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="tache_seq")
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
    public LocalDateTime getDateLimite(){
        return this.dateLimite;
    }
    public void setDateLimite(LocalDateTime dateLimite){
        this.dateLimite = dateLimite;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priorite")
    public Priorite getPriorite(){
        return this.priorite;
    }
    public void setPriorite(Priorite priorite){
        this.priorite = priorite;
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
    @JoinColumn(name = "dossier_client")
    public DossierClient getDossierClient(){
        return this.dossierClient;
    }
    public void setDossierClient(DossierClient dossierClient){
        this.dossierClient = dossierClient;
    }
    @OneToMany(mappedBy = "tache")

    public List<TacheDetail> getTacheDetails(){
        return this.tacheDetails;
    }
    public void setTacheDetails(List<TacheDetail> tacheDetails){
        this.tacheDetails = tacheDetails;
    }
    @OneToMany(mappedBy = "tache")

    public List<TacheEntiteExterne> getTacheEntiteExternes(){
        return this.tacheEntiteExternes;
    }
    public void setTacheEntiteExternes(List<TacheEntiteExterne> tacheEntiteExternes){
        this.tacheEntiteExternes = tacheEntiteExternes;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tache tache = (Tache) o;
        return id != null && id.equals(tache.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

