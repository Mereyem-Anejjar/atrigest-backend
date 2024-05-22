package  ma.zs.task.dao.specification.core.notif;

import ma.zs.task.dao.criteria.core.notif.NotificationDetailCriteria;
import ma.zs.task.bean.core.notif.NotificationDetail;
import ma.zs.task.zynerator.specification.AbstractSpecification;


public class NotificationDetailSpecification extends  AbstractSpecification<NotificationDetailCriteria, NotificationDetail>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateFk("utilisateur","id", criteria.getUtilisateur()==null?null:criteria.getUtilisateur().getId());
        addPredicateFk("utilisateur","id", criteria.getUtilisateurs());
        addPredicateFk("notification","id", criteria.getNotification()==null?null:criteria.getNotification().getId());
        addPredicateFk("notification","id", criteria.getNotifications());
    }

    public NotificationDetailSpecification(NotificationDetailCriteria criteria) {
        super(criteria);
    }

    public NotificationDetailSpecification(NotificationDetailCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
