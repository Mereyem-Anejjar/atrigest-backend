package  ma.zs.task.dao.specification.core.utilisateur;

import ma.zs.task.dao.criteria.core.utilisateur.UtilisateurCriteria;
import ma.zs.task.bean.core.utilisateur.Utilisateur;
import ma.zs.task.zynerator.specification.AbstractSpecification;


public class UtilisateurSpecification extends  AbstractSpecification<UtilisateurCriteria, Utilisateur>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("description", criteria.getDescription(),criteria.getDescriptionLike());
        addPredicateBool("credentialsNonExpired", criteria.getCredentialsNonExpired());
        addPredicateBool("enabled", criteria.getEnabled());
        addPredicateBool("accountNonExpired", criteria.getAccountNonExpired());
        addPredicateBool("accountNonLocked", criteria.getAccountNonLocked());
        addPredicateBool("passwordChanged", criteria.getPasswordChanged());
        addPredicate("username", criteria.getUsername(),criteria.getUsernameLike());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
    }

    public UtilisateurSpecification(UtilisateurCriteria criteria) {
        super(criteria);
    }

    public UtilisateurSpecification(UtilisateurCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
