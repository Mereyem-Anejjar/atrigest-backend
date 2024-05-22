package  ma.zs.task.dao.specification.core.dc;

import ma.zs.task.dao.criteria.core.dc.DossierClientCriteria;
import ma.zs.task.bean.core.dc.DossierClient;
import ma.zs.task.zynerator.specification.AbstractSpecification;


public class DossierClientSpecification extends  AbstractSpecification<DossierClientCriteria, DossierClient>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("nom", criteria.getNom(),criteria.getNomLike());
        addPredicate("adresse", criteria.getAdresse(),criteria.getAdresseLike());
        addPredicate("numeroIdentite", criteria.getNumeroIdentite(),criteria.getNumeroIdentiteLike());
        addPredicate("raisonSociale", criteria.getRaisonSociale(),criteria.getRaisonSocialeLike());
        addPredicate("identifiantCommun", criteria.getIdentifiantCommun(),criteria.getIdentifiantCommunLike());
        addPredicate("registreCommerce", criteria.getRegistreCommerce(),criteria.getRegistreCommerceLike());
        addPredicate("taxeProfessionnelle", criteria.getTaxeProfessionnelle(),criteria.getTaxeProfessionnelleLike());
        addPredicate("cnss", criteria.getCnss(),criteria.getCnssLike());
        addPredicate("groupeSociete", criteria.getGroupeSociete(),criteria.getGroupeSocieteLike());
        addPredicateFk("nationalite","id", criteria.getNationalite()==null?null:criteria.getNationalite().getId());
        addPredicateFk("nationalite","id", criteria.getNationalites());
        addPredicateFk("nationalite","code", criteria.getNationalite()==null?null:criteria.getNationalite().getCode());
        addPredicateFk("typeIdentite","id", criteria.getTypeIdentite()==null?null:criteria.getTypeIdentite().getId());
        addPredicateFk("typeIdentite","id", criteria.getTypeIdentites());
        addPredicateFk("typeIdentite","code", criteria.getTypeIdentite()==null?null:criteria.getTypeIdentite().getCode());
        addPredicateFk("banqueAdherente","id", criteria.getBanqueAdherente()==null?null:criteria.getBanqueAdherente().getId());
        addPredicateFk("banqueAdherente","id", criteria.getBanqueAdherentes());
        addPredicateFk("banqueAdherente","code", criteria.getBanqueAdherente()==null?null:criteria.getBanqueAdherente().getCode());
    }

    public DossierClientSpecification(DossierClientCriteria criteria) {
        super(criteria);
    }

    public DossierClientSpecification(DossierClientCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
