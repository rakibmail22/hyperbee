package net.bahmed.hyperbee.service;

import net.bahmed.hyperbee.dao.ActivityDao;
import net.bahmed.hyperbee.dao.UserDao;
import net.bahmed.hyperbee.domain.Activity;
import net.bahmed.hyperbee.domain.User;
import net.bahmed.hyperbee.web.helper.SessionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rayed
 * @since 11/28/16 10:23 AM
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private SessionHelper sessionHelper;

    @Override
    public List<Activity> findByUserId(int userId) {

        return activityDao.findByUserId(userId);
    }

    @Override
    public void archive(String summary) {
        Activity activity = new Activity();

        int userIdFromSession = sessionHelper.getUserIdFromSession();
        User user = userDao.findById(userIdFromSession);

        activity.setUser(user);
        activity.setSummary(summary);

        activityDao.create(activity);
    }
}
