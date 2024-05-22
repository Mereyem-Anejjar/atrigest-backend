package ma.zs.task.dao.facade.core.notif;

import ma.zs.task.zynerator.repository.AbstractRepository;
import ma.zs.task.bean.core.notif.Notification;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface NotificationDao extends AbstractRepository<Notification,Long>  {



}
