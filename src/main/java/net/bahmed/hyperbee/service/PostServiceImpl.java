package net.bahmed.hyperbee.service;

import net.bahmed.hyperbee.dao.PostDao;
import net.bahmed.hyperbee.domain.Hive;
import net.bahmed.hyperbee.domain.Post;
import net.bahmed.hyperbee.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author azim
 * @since 11/27/16
 */
@Repository
public class PostServiceImpl implements PostService {

    @Autowired
    private UserService userService;

    @Autowired
    private HiveService hiveService;

    @Autowired
    private PostDao postDao;

    @Override
    @Transactional
    public void savePost(int userId, int hiveId, Post post) {
        User user = userService.findById(userId);
        Hive hive = hiveService.findById(hiveId);
        post.setUser(user);
        post.setHive(hive);
        postDao.insertPost(post);
    }
}
