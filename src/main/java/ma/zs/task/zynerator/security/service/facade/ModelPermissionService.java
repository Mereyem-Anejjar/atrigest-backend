package ma.zs.task.zynerator.security.service.facade;

import ma.zs.task.zynerator.security.bean.ModelPermission;
import ma.zs.task.zynerator.security.dao.criteria.core.ModelPermissionCriteria;
import ma.zs.task.zynerator.service.IService;
import java.util.List;



public interface ModelPermissionService extends  IService<ModelPermission,ModelPermissionCriteria>  {
    List<ModelPermission> findAllOptimized();

}
