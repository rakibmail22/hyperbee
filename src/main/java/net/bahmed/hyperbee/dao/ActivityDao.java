package net.bahmed.hyperbee.dao;

import net.bahmed.hyperbee.domain.Activity;

import java.util.List;

/**
 * @author rayed
 * @since 11/28/16 10:19 AM
 */
public interface ActivityDao {

    public void create(Activity activity);

    public List<Activity> findByUserId(int userId);
}
