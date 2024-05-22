package  ma.zs.task.dao.specification.core.notif;

import ma.zs.task.dao.criteria.core.notif.NotificationCriteria;
import ma.zs.task.bean.core.notif.Notification;
import ma.zs.task.zynerator.specification.AbstractSpecification;


public class NotificationSpecification extends  AbstractSpecification<NotificationCriteria, Notification>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("contenue", criteria.getContenue(),criteria.getContenueLike());
        addPredicate("dateEnvoi", criteria.getDateEnvoi(), criteria.getDateEnvoiFrom(), criteria.getDateEnvoiTo());
    }

    public NotificationSpecification(NotificationCriteria criteria) {
        super(criteria);
    }

    public NotificationSpecification(NotificationCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
