package net.bahmed.hyperbee.service;

import net.bahmed.hyperbee.domain.Hive;
import net.bahmed.hyperbee.domain.Notice;
import net.bahmed.hyperbee.domain.User;

import java.util.List;

/**
 * @author azim
 * @since 11/22/16
 */
public interface HiveService {

    public void insertHive(Hive hive);

    public Hive retrieveHiveById(int id);

    public void insertUsersToHive(int hiveId, List<Integer> userIdList);

    public Hive insertFirstUserToHive(Hive hive, int userId);

    public Hive getHiveByHiveName(String name);

    public List<User> getUserNotInList(int hiveId);

    public Hive findById(int hiveId);

    public void removeUsersFromHive(int hiveId, List<Integer> userIdList);

    public List<User> getUserListToRemove(int id);

    public List<Notice> getLastFiveNotice(List<Notice> noticeList);

    public List<Hive> getAllHive();
}
