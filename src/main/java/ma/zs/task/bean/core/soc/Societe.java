package ma.zs.task.bean.core.soc;

import java.util.Objects;
import java.util.List;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;




import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.task.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "societe")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="societe_seq",sequenceName="societe_seq",allocationSize=1, initialValue = 1)
public class Societe  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String nom;
    private LocalDateTime dateCreation ;
    @Column(length = 500)
    private String rc;
    @Column(length = 500)
    private String taxeProfessionnelle;
    @Column(length = 500)
    private String ice;
    @Column(length = 500)
    private String gerant;

    private TypeSociete type ;

    private List<Associe> associes ;

    public Societe(){
        super();
    }

    public Societe(Long id,String nom){
        this.id = id;
        this.nom = nom ;
    }
    public Societe(String nom){
        this.nom = nom ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="societe_seq")
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
    @JoinColumn(name = "type")
    public TypeSociete getType(){
        return this.type;
    }
    public void setType(TypeSociete type){
        this.type = type;
    }
    public LocalDateTime getDateCreation(){
        return this.dateCreation;
    }
    public void setDateCreation(LocalDateTime dateCreation){
        this.dateCreation = dateCreation;
    }
    public String getRc(){
        return this.rc;
    }
    public void setRc(String rc){
        this.rc = rc;
    }
    public String getTaxeProfessionnelle(){
        return this.taxeProfessionnelle;
    }
    public void setTaxeProfessionnelle(String taxeProfessionnelle){
        this.taxeProfessionnelle = taxeProfessionnelle;
    }
    public String getIce(){
        return this.ice;
    }
    public void setIce(String ice){
        this.ice = ice;
    }
    public String getGerant(){
        return this.gerant;
    }
    public void setGerant(String gerant){
        this.gerant = gerant;
    }
    @OneToMany(mappedBy = "societe")

    public List<Associe> getAssocies(){
        return this.associes;
    }
    public void setAssocies(List<Associe> associes){
        this.associes = associes;
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
        Societe societe = (Societe) o;
        return id != null && id.equals(societe.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

