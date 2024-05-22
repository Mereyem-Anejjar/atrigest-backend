package  ma.zs.task.ws.dto.dc;

import ma.zs.task.zynerator.audit.Log;
import ma.zs.task.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zs.task.ws.dto.commun.NationaliteDto;
import ma.zs.task.ws.dto.commun.TypeIdentiteDto;
import ma.zs.task.ws.dto.commun.BanqueDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class DossierClientDto  extends AuditBaseDto {

    private String nom  ;
    private String adresse  ;
    private String numeroIdentite  ;
    private String raisonSociale  ;
    private String identifiantCommun  ;
    private String registreCommerce  ;
    private String taxeProfessionnelle  ;
    private String cnss  ;
    private String groupeSociete  ;

    private NationaliteDto nationalite ;
    private TypeIdentiteDto typeIdentite ;
    private BanqueDto banqueAdherente ;



    public DossierClientDto(){
        super();
    }



    @Log
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }

    @Log
    public String getAdresse(){
        return this.adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }

    @Log
    public String getNumeroIdentite(){
        return this.numeroIdentite;
    }
    public void setNumeroIdentite(String numeroIdentite){
        this.numeroIdentite = numeroIdentite;
    }

    @Log
    public String getRaisonSociale(){
        return this.raisonSociale;
    }
    public void setRaisonSociale(String raisonSociale){
        this.raisonSociale = raisonSociale;
    }

    @Log
    public String getIdentifiantCommun(){
        return this.identifiantCommun;
    }
    public void setIdentifiantCommun(String identifiantCommun){
        this.identifiantCommun = identifiantCommun;
    }

    @Log
    public String getRegistreCommerce(){
        return this.registreCommerce;
    }
    public void setRegistreCommerce(String registreCommerce){
        this.registreCommerce = registreCommerce;
    }

    @Log
    public String getTaxeProfessionnelle(){
        return this.taxeProfessionnelle;
    }
    public void setTaxeProfessionnelle(String taxeProfessionnelle){
        this.taxeProfessionnelle = taxeProfessionnelle;
    }

    @Log
    public String getCnss(){
        return this.cnss;
    }
    public void setCnss(String cnss){
        this.cnss = cnss;
    }

    @Log
    public String getGroupeSociete(){
        return this.groupeSociete;
    }
    public void setGroupeSociete(String groupeSociete){
        this.groupeSociete = groupeSociete;
    }


    public NationaliteDto getNationalite(){
        return this.nationalite;
    }

    public void setNationalite(NationaliteDto nationalite){
        this.nationalite = nationalite;
    }
    public TypeIdentiteDto getTypeIdentite(){
        return this.typeIdentite;
    }

    public void setTypeIdentite(TypeIdentiteDto typeIdentite){
        this.typeIdentite = typeIdentite;
    }
    public BanqueDto getBanqueAdherente(){
        return this.banqueAdherente;
    }

    public void setBanqueAdherente(BanqueDto banqueAdherente){
        this.banqueAdherente = banqueAdherente;
    }






}
