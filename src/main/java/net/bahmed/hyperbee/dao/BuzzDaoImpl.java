package net.bahmed.hyperbee.dao;

import net.bahmed.hyperbee.domain.Buzz;
import net.bahmed.hyperbee.domain.User;
import net.bahmed.hyperbee.domain.enums.DisplayStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author zoha
 * @since 11/22/16
 */
@Repository
public class BuzzDaoImpl implements BuzzDao {

    private final String QUERY_GET_BY_STATUS = "SELECT b FROM Buzz b WHERE b.displayStatus = :displayStatus";
    private final String QUERY_GET_LATEST = "SELECT b FROM Buzz b WHERE b.displayStatus = :displayStatus " +
            "AND b.pinned = :pinned ORDER BY b.id DESC";
    private final String QUERY_GET_FLAGGED = "SELECT b FROM Buzz b WHERE b.flagged = :flagged";
    private final String QUERY_GET_PINNED = "SELECT b FROM Buzz b WHERE b.pinned = :pinned " +
            "ORDER BY b.id DESC";

    private final String QUERY_GET_ACTIVE_BY_USER = "SELECT b FROM Buzz b WHERE b.user = :user " +
            "AND b.displayStatus = :displayStatus AND Date(b.buzzTime) = Curdate()";
    private final String QUERY_GET_PINNED_BY_USER = "SELECT b FROM Buzz b WHERE b.user = :user " +
            "AND b.pinned = :pinned AND Date(b.buzzTime) = Curdate()";
    private final String QUERY_GET_FLAGGED_BY_USER = "SELECT b FROM Buzz b WHERE b.user = :user " +
            "AND b.flagged = :flagged AND Date(b.buzzTime) = Curdate()";

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Buzz saveOrUpdate(Buzz buzz) {
        if (buzz.getId() == 0) {
            em.persist(buzz);
        } else {
            return em.merge(buzz);
        }

        return buzz;
    }

    @Override
    public List<Buzz> getAll() {
        return em.createQuery("FROM Buzz", Buzz.class).getResultList();
    }

    @Override
    public Buzz getById(int buzzId) {
        return em.find(Buzz.class, buzzId);
    }

    @Override
    public List<Buzz> getByDisplayStatus(DisplayStatus displayStatus) {
        return em.createQuery(QUERY_GET_BY_STATUS, Buzz.class)
                .setParameter("displayStatus", displayStatus.getStatus())
                .getResultList();
    }

    @Override
    public List<Buzz> getLatest(int range) {
        return em.createQuery(QUERY_GET_LATEST, Buzz.class)
                .setParameter("displayStatus", DisplayStatus.ACTIVE)
                .setParameter("pinned", false)
                .setMaxResults(range)
                .getResultList();
    }

    @Override
    public List<Buzz> getPinnedBuzz(int range) {
        return em.createQuery(QUERY_GET_PINNED, Buzz.class)
                .setParameter("pinned", true)
                .setMaxResults(range)
                .getResultList();
    }

    @Override
    public int getActiveCountByUser(User user) {
        return em.createQuery(QUERY_GET_ACTIVE_BY_USER, Buzz.class)
                .setParameter("user", user)
                .setParameter("displayStatus", DisplayStatus.ACTIVE)
                .getResultList()
                .size();
    }

    @Override
    public int getPinnedCountByUser(User user) {
        return em.createQuery(QUERY_GET_PINNED_BY_USER, Buzz.class)
                .setParameter("user", user)
                .setParameter("pinned", true)
                .getResultList()
                .size();
    }

    @Override
    public int getFlaggedCountByUser(User user) {
        return em.createQuery(QUERY_GET_FLAGGED_BY_USER, Buzz.class)
                .setParameter("user", user)
                .setParameter("flagged", true)
                .getResultList()
                .size();
    }

    @Override
    public int getActiveCount() {
        return em.createQuery(QUERY_GET_BY_STATUS, Buzz.class)
                .setParameter("displayStatus", DisplayStatus.ACTIVE)
                .getResultList()
                .size();
    }

    @Override
    public int getPinnedCount() {
        return em.createQuery(QUERY_GET_PINNED, Buzz.class)
                .setParameter("pinned", true)
                .getResultList()
                .size();
    }

    @Override
    public int getFlaggedCount() {
        return em.createQuery(QUERY_GET_FLAGGED, Buzz.class)
                .setParameter("flagged", true)
                .getResultList()
                .size();
    }

    @Override
    public int getInactiveCount() {
        return em.createQuery(QUERY_GET_BY_STATUS, Buzz.class)
                .setParameter("displayStatus", DisplayStatus.INACTIVE)
                .getResultList()
                .size();
    }
}