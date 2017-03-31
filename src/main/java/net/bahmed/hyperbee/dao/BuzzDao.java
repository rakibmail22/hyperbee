package net.bahmed.hyperbee.dao;

import net.bahmed.hyperbee.domain.Buzz;
import net.bahmed.hyperbee.domain.User;
import net.bahmed.hyperbee.domain.enums.DisplayStatus;

import java.util.List;

/**
 * @author zoha
 * @since 11/22/16
 */
public interface BuzzDao {

    public Buzz saveOrUpdate(Buzz newBuzz);

    public List<Buzz> getAll();

    public Buzz getById(int buzzId);

    public List<Buzz> getByDisplayStatus(DisplayStatus displayStatus);

    public List<Buzz> getLatest(int range);

    List<Buzz> getPinnedBuzz(int range);

    public int getActiveCountByUser(User user);

    public int getPinnedCountByUser(User user);

    public int getFlaggedCountByUser(User user);

    public int getActiveCount();

    public int getPinnedCount();

    public int getFlaggedCount();

    public int getInactiveCount();
}
