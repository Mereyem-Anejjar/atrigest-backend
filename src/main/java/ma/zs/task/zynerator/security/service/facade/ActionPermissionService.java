package ma.zs.task.zynerator.security.service.facade;

import ma.zs.task.zynerator.security.bean.ActionPermission;
import ma.zs.task.zynerator.security.dao.criteria.core.ActionPermissionCriteria;
import ma.zs.task.zynerator.service.IService;
import java.util.List;


public interface ActionPermissionService extends  IService<ActionPermission,ActionPermissionCriteria>  {

    List<ActionPermission> findAllOptimized();

}
