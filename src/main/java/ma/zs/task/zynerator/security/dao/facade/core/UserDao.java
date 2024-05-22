package ma.zs.task.zynerator.security.dao.facade.core;

import ma.zs.task.zynerator.repository.AbstractRepository;
import ma.zs.task.zynerator.security.bean.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDao extends AbstractRepository<User,Long>  {
    User findByEmail(String email);
    int deleteByEmail(String email);
    User findByUsername(String username);
    int deleteByUsername(String username);


    @Query("SELECT NEW User(item.id,item.email) FROM User item")
    List<User> findAllOptimized();

}
