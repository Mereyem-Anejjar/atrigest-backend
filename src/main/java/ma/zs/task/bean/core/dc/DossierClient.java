package ma.zs.task.bean.core.dc;

import java.util.Objects;





import ma.zs.task.bean.core.commun.Nationalite;
import ma.zs.task.bean.core.commun.TypeIdentite;
import ma.zs.task.bean.core.commun.Banque;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.task.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dossier_client")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="dossier_client_seq",sequenceName="dossier_client_seq",allocationSize=1, initialValue = 1)
public class DossierClient  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String nom;
    @Column(length = 500)
    private String adresse;
    @Column(length = 500)
    private String numeroIdentite;
    @Column(length = 500)
    private String raisonSociale;
    @Column(length = 500)
    private String identifiantCommun;
    @Column(length = 500)
    private String registreCommerce;
    @Column(length = 500)
    private String taxeProfessionnelle;
    @Column(length = 500)
    private String cnss;
    @Column(length = 500)
    private String groupeSociete;

    private Nationalite nationalite ;
    private TypeIdentite typeIdentite ;
    private Banque banqueAdherente ;


    public DossierClient(){
        super();
    }

    public DossierClient(Long id,String nom){
        this.id = id;
        this.nom = nom ;
    }
    public DossierClient(String nom){
        this.nom = nom ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="dossier_client_seq")
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
    public String getAdresse(){
        return this.adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nationalite")
    public Nationalite getNationalite(){
        return this.nationalite;
    }
    public void setNationalite(Nationalite nationalite){
        this.nationalite = nationalite;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_identite")
    public TypeIdentite getTypeIdentite(){
        return this.typeIdentite;
    }
    public void setTypeIdentite(TypeIdentite typeIdentite){
        this.typeIdentite = typeIdentite;
    }
    public String getNumeroIdentite(){
        return this.numeroIdentite;
    }
    public void setNumeroIdentite(String numeroIdentite){
        this.numeroIdentite = numeroIdentite;
    }
    public String getRaisonSociale(){
        return this.raisonSociale;
    }
    public void setRaisonSociale(String raisonSociale){
        this.raisonSociale = raisonSociale;
    }
    public String getIdentifiantCommun(){
        return this.identifiantCommun;
    }
    public void setIdentifiantCommun(String identifiantCommun){
        this.identifiantCommun = identifiantCommun;
    }
    public String getRegistreCommerce(){
        return this.registreCommerce;
    }
    public void setRegistreCommerce(String registreCommerce){
        this.registreCommerce = registreCommerce;
    }
    public String getTaxeProfessionnelle(){
        return this.taxeProfessionnelle;
    }
    public void setTaxeProfessionnelle(String taxeProfessionnelle){
        this.taxeProfessionnelle = taxeProfessionnelle;
    }
    public String getCnss(){
        return this.cnss;
    }
    public void setCnss(String cnss){
        this.cnss = cnss;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banque_adherente")
    public Banque getBanqueAdherente(){
        return this.banqueAdherente;
    }
    public void setBanqueAdherente(Banque banqueAdherente){
        this.banqueAdherente = banqueAdherente;
    }
    public String getGroupeSociete(){
        return this.groupeSociete;
    }
    public void setGroupeSociete(String groupeSociete){
        this.groupeSociete = groupeSociete;
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
        DossierClient dossierClient = (DossierClient) o;
        return id != null && id.equals(dossierClient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

