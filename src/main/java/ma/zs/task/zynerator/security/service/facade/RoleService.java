package ma.zs.task.zynerator.security.service.facade;

import ma.zs.task.zynerator.security.bean.Role;
import ma.zs.task.zynerator.security.dao.criteria.core.RoleCriteria;
import ma.zs.task.zynerator.service.IService;



public interface RoleService extends  IService<Role,RoleCriteria>  {
    Role findByAuthority(String authority);
    int deleteByAuthority(String authority);


    



}
