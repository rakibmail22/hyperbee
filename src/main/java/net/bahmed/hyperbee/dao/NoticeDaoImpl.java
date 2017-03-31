package net.bahmed.hyperbee.dao;

import net.bahmed.hyperbee.domain.Notice;
import net.bahmed.hyperbee.domain.Hive;
import net.bahmed.hyperbee.domain.enums.DisplayStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author rumman
 * @since 11/22/16
 */
@Repository
public class NoticeDaoImpl implements NoticeDao {

    private static final String ACTIVE_NOTICE_LIST_QUERY = "SELECT n FROM Notice n where n.displayStatus =:status " +
            "AND n IN :noticeList ORDER BY n.id DESC";

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Notice saveOrUpdate(Notice notice) {
        if (notice.isNew()) {
            em.persist(notice);
            em.flush();
        } else {
            notice = em.merge(notice);
        }

        return notice;
    }

    @Override
    public Notice findById(int noticeId) {
        return em.find(Notice.class, noticeId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Notice> findAll() {
        return em.createNamedQuery("Notice.findAllNotice", Notice.class)
                .getResultList();
    }

    @Override
    public List<Notice> findLatestNotices(int range) {
        return em.createNamedQuery("Notice.findLatestNotices", Notice.class)
                .setMaxResults(range)
                .getResultList();
    }

    @Override
    @Transactional
    public void delete(Notice notice) {
        delete(notice.getId());
    }

    @Override
    @Transactional
    public void delete(int noticeId) {
        Notice attachedNotice = em.getReference(Notice.class, noticeId);
        em.remove(attachedNotice);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Notice> getNoticeListByHiveId(int hiveId, int range) {
        Hive hive = em.find(Hive.class, hiveId);
        List<Notice> noticeList = hive.getNoticeList();

        if (noticeList.size() == 0) {

            return noticeList;
        }

        return em.createQuery(ACTIVE_NOTICE_LIST_QUERY, Notice.class)
                .setParameter("status", DisplayStatus.ACTIVE)
                .setParameter("noticeList", noticeList)
                .setMaxResults(range)
                .getResultList();
    }
}