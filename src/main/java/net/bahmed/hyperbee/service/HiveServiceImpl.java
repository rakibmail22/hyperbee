package net.bahmed.hyperbee.service;

import net.bahmed.hyperbee.dao.HiveDao;
import net.bahmed.hyperbee.domain.Hive;
import net.bahmed.hyperbee.domain.Notice;
import net.bahmed.hyperbee.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author azim
 * @since 11/22/16
 */
@Service
public class HiveServiceImpl implements HiveService {

    @Autowired
    private HiveDao hiveDao;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public void insertHive(Hive hive) {
        hiveDao.saveHive(hive);
    }

    @Transactional
    public List<User> getUserListById(List<Integer> idList) {
        List<User> userList = hiveDao.getUserListById(idList);

        return userList;
    }

    @Override
    @Transactional
    public Hive insertFirstUserToHive(Hive hive, int userId) {
        List<User> userList = hive.getUserList();
        User user = userService.findById(userId);
        userList.add(user);

        hive.setUserList(userList);

        return hive;
    }

    @Override
    public Hive retrieveHiveById(int id) {

        return hiveDao.retrieveHiveById(id);
    }

    @Override
    @Transactional
    public void insertUsersToHive(int hiveId, List<Integer> userIdList) {
        Hive hive = retrieveHiveById(hiveId);
        List<User> userList = getUserListById(userIdList);
        hiveDao.addUsersToHive(hive, userList);
    }

    @Override
    public Hive getHiveByHiveName(String name) {
        return hiveDao.getHiveByHiveName(name);
    }

    @Transactional
    public List<User> getUserNotInList(int hiveId) {
        Hive hive = retrieveHiveById(hiveId);
        List<User> userList = hive.getUserList();

        return hiveDao.findUserNotInList(userList);
    }

    @Override
    public Hive findById(int hiveId) {

        return hiveDao.findById(hiveId);
    }

    @Override
    @Transactional
    public void removeUsersFromHive(int hiveId, List<Integer> userIdList) {
        Hive hive = retrieveHiveById(hiveId);
        List<User> userList = getUserListById(userIdList);
        hiveDao.removeUsersFromHive(hive, userList);
    }

    @Override
    public List<User> getUserListToRemove(int id) {
        Hive hive = retrieveHiveById(id);
        List<User> userList = hive.getUserList();
        userList.remove(userService.findById(hive.getCreatorId()));

        return userList;
    }

    @Override
    public List<Notice> getLastFiveNotice(List<Notice> noticeList) {

        if (noticeList.isEmpty()) {
            return noticeList;
        }

        return hiveDao.getLastFiveNotice(noticeList, 5);
    }

    @Override
    public List<Hive> getAllHive() {
        return hiveDao.findAll();
    }
}
