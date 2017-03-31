package net.bahmed.hyperbee.service;

import net.bahmed.hyperbee.domain.Buzz;
import net.bahmed.hyperbee.domain.enums.DisplayStatus;

import java.util.List;

/**
 * @author zoha
 * @since 11/22/16
 */
public interface BuzzService {

    public Buzz saveBuzz(Buzz newBuzz);

    public List<Buzz> getAllBuzz();

    public Buzz getBuzzById(int buzzId);

    public List<Buzz> getBuzzByStatus(DisplayStatus displayStatus);

    List<Buzz> getLatestBuzz();

    public List<Buzz> getPinnedBuzz();

    public Buzz flagBuzz(Buzz buzzToFlag);

    public Buzz deactivateBuzz(Buzz buzzToDeactivate);

    public Buzz pinBuzz(Buzz buzzToPin);

    public int getActiveCountByUser(int userId);

    public int getPinnedCountByUser(int userId);

    public int getFlaggedCountByUser(int userId);

    public int getActiveCount();

    public int getInactiveCount();

    public int getPinnedCount();

    public int getFlaggedCount();
}
