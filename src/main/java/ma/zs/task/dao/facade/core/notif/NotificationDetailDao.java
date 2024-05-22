package ma.zs.task.dao.facade.core.notif;

import ma.zs.task.zynerator.repository.AbstractRepository;
import ma.zs.task.bean.core.notif.NotificationDetail;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface NotificationDetailDao extends AbstractRepository<NotificationDetail,Long>  {

    List<NotificationDetail> findByUtilisateurId(Long id);
    int deleteByUtilisateurId(Long id);
    long countByUtilisateurId(Long id);
    List<NotificationDetail> findByNotificationId(Long id);
    int deleteByNotificationId(Long id);
    long countByNotificationId(Long id);


}
