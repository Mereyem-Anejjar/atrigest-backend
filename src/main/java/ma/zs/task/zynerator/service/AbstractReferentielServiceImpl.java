package ma.zs.task.zynerator.service;

import ma.zs.task.zynerator.bean.BaseEntity;
import ma.zs.task.zynerator.criteria.BaseCriteria;
import ma.zs.task.zynerator.repository.AbstractRepository;

public abstract class AbstractReferentielServiceImpl<T extends BaseEntity, CRITERIA extends BaseCriteria, REPO extends AbstractRepository<T, Long>> extends AbstractServiceImpl<T, CRITERIA, REPO> {

    public AbstractReferentielServiceImpl(REPO dao) {
        super(dao);
    }

}
