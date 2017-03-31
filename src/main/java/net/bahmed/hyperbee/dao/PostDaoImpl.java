package net.bahmed.hyperbee.dao;

import net.bahmed.hyperbee.domain.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author azim
 * @since 11/27/16
 */
@Repository
public class PostDaoImpl implements PostDao {


    private final String QUERY_GET_POST_ID = "SELECT p FROM Post p WHERE p.hive.id =:id";

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insertPost(Post post) {
        em.persist(em.merge(post));
        em.flush();
    }

    @Override
    public List<Post> getPostListByHive(int id) {
        return em.createQuery(QUERY_GET_POST_ID, Post.class).setParameter("id", id).getResultList();
    }
}
