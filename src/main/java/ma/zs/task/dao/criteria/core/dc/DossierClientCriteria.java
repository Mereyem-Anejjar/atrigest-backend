package  ma.zs.task.dao.criteria.core.dc;


import ma.zs.task.dao.criteria.core.commun.NationaliteCriteria;
import ma.zs.task.dao.criteria.core.commun.TypeIdentiteCriteria;
import ma.zs.task.dao.criteria.core.commun.BanqueCriteria;

import ma.zs.task.zynerator.criteria.BaseCriteria;
import java.util.List;

public class DossierClientCriteria extends  BaseCriteria  {

    private String nom;
    private String nomLike;
    private String adresse;
    private String adresseLike;
    private String numeroIdentite;
    private String numeroIdentiteLike;
    private String raisonSociale;
    private String raisonSocialeLike;
    private String identifiantCommun;
    private String identifiantCommunLike;
    private String registreCommerce;
    private String registreCommerceLike;
    private String taxeProfessionnelle;
    private String taxeProfessionnelleLike;
    private String cnss;
    private String cnssLike;
    private String groupeSociete;
    private String groupeSocieteLike;

    private NationaliteCriteria nationalite ;
    private List<NationaliteCriteria> nationalites ;
    private TypeIdentiteCriteria typeIdentite ;
    private List<TypeIdentiteCriteria> typeIdentites ;
    private BanqueCriteria banqueAdherente ;
    private List<BanqueCriteria> banqueAdherentes ;


    public DossierClientCriteria(){}

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

    public String getAdresse(){
        return this.adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }
    public String getAdresseLike(){
        return this.adresseLike;
    }
    public void setAdresseLike(String adresseLike){
        this.adresseLike = adresseLike;
    }

    public String getNumeroIdentite(){
        return this.numeroIdentite;
    }
    public void setNumeroIdentite(String numeroIdentite){
        this.numeroIdentite = numeroIdentite;
    }
    public String getNumeroIdentiteLike(){
        return this.numeroIdentiteLike;
    }
    public void setNumeroIdentiteLike(String numeroIdentiteLike){
        this.numeroIdentiteLike = numeroIdentiteLike;
    }

    public String getRaisonSociale(){
        return this.raisonSociale;
    }
    public void setRaisonSociale(String raisonSociale){
        this.raisonSociale = raisonSociale;
    }
    public String getRaisonSocialeLike(){
        return this.raisonSocialeLike;
    }
    public void setRaisonSocialeLike(String raisonSocialeLike){
        this.raisonSocialeLike = raisonSocialeLike;
    }

    public String getIdentifiantCommun(){
        return this.identifiantCommun;
    }
    public void setIdentifiantCommun(String identifiantCommun){
        this.identifiantCommun = identifiantCommun;
    }
    public String getIdentifiantCommunLike(){
        return this.identifiantCommunLike;
    }
    public void setIdentifiantCommunLike(String identifiantCommunLike){
        this.identifiantCommunLike = identifiantCommunLike;
    }

    public String getRegistreCommerce(){
        return this.registreCommerce;
    }
    public void setRegistreCommerce(String registreCommerce){
        this.registreCommerce = registreCommerce;
    }
    public String getRegistreCommerceLike(){
        return this.registreCommerceLike;
    }
    public void setRegistreCommerceLike(String registreCommerceLike){
        this.registreCommerceLike = registreCommerceLike;
    }

    public String getTaxeProfessionnelle(){
        return this.taxeProfessionnelle;
    }
    public void setTaxeProfessionnelle(String taxeProfessionnelle){
        this.taxeProfessionnelle = taxeProfessionnelle;
    }
    public String getTaxeProfessionnelleLike(){
        return this.taxeProfessionnelleLike;
    }
    public void setTaxeProfessionnelleLike(String taxeProfessionnelleLike){
        this.taxeProfessionnelleLike = taxeProfessionnelleLike;
    }

    public String getCnss(){
        return this.cnss;
    }
    public void setCnss(String cnss){
        this.cnss = cnss;
    }
    public String getCnssLike(){
        return this.cnssLike;
    }
    public void setCnssLike(String cnssLike){
        this.cnssLike = cnssLike;
    }

    public String getGroupeSociete(){
        return this.groupeSociete;
    }
    public void setGroupeSociete(String groupeSociete){
        this.groupeSociete = groupeSociete;
    }
    public String getGroupeSocieteLike(){
        return this.groupeSocieteLike;
    }
    public void setGroupeSocieteLike(String groupeSocieteLike){
        this.groupeSocieteLike = groupeSocieteLike;
    }


    public NationaliteCriteria getNationalite(){
        return this.nationalite;
    }

    public void setNationalite(NationaliteCriteria nationalite){
        this.nationalite = nationalite;
    }
    public List<NationaliteCriteria> getNationalites(){
        return this.nationalites;
    }

    public void setNationalites(List<NationaliteCriteria> nationalites){
        this.nationalites = nationalites;
    }
    public TypeIdentiteCriteria getTypeIdentite(){
        return this.typeIdentite;
    }

    public void setTypeIdentite(TypeIdentiteCriteria typeIdentite){
        this.typeIdentite = typeIdentite;
    }
    public List<TypeIdentiteCriteria> getTypeIdentites(){
        return this.typeIdentites;
    }

    public void setTypeIdentites(List<TypeIdentiteCriteria> typeIdentites){
        this.typeIdentites = typeIdentites;
    }
    public BanqueCriteria getBanqueAdherente(){
        return this.banqueAdherente;
    }

    public void setBanqueAdherente(BanqueCriteria banqueAdherente){
        this.banqueAdherente = banqueAdherente;
    }
    public List<BanqueCriteria> getBanqueAdherentes(){
        return this.banqueAdherentes;
    }

    public void setBanqueAdherentes(List<BanqueCriteria> banqueAdherentes){
        this.banqueAdherentes = banqueAdherentes;
    }
}
