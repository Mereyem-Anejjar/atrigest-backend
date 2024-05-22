package ma.zs.task.dao.facade.core.utilisateur;

import ma.zs.task.zynerator.repository.AbstractRepository;
import ma.zs.task.bean.core.utilisateur.Utilisateur;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface UtilisateurDao extends AbstractRepository<Utilisateur,Long>  {

    Utilisateur findByUsername(String username);


}
