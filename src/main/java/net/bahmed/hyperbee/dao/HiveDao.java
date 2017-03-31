package net.bahmed.hyperbee.dao;

import net.bahmed.hyperbee.domain.Hive;
import net.bahmed.hyperbee.domain.Notice;
import net.bahmed.hyperbee.domain.User;

import java.util.List;

/**
 * @author azim
 * @since 11/22/16
 */
public interface HiveDao {

    public void saveHive(Hive hive);

    public List<User> getUserListById(List<Integer> idList);

    public Hive retrieveHiveById(int id);

    public void addUsersToHive(Hive hive, List<User> userList);

    public Hive getHiveByHiveName(String name);

    public List<User> findUserNotInList(List<User> userList);

    public Hive findById(int hiveId);

    public void removeUsersFromHive(Hive hive, List<User> userList);

    public List<Notice> getLastFiveNotice(List<Notice> noticeList, int range);

    public List<Hive> findAll();
}
