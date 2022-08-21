package sk.tuke.gamestudio.logicalmazes.server.webservice.serviceComment;

import sk.tuke.gamestudio.logicalmazes.entity.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class CommentServiceJPA
        implements CommentService{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addComment(Comment comment) {
        entityManager.persist(comment);
    }

    @Override
    public List<Comment> getComments(String game) {
        return entityManager.createQuery(
                        "select s from Comment s where s.game = :game")
                .setParameter("game", game)
                .setMaxResults(10)
                .getResultList();
    }

    @Override
    public void reset() {
        entityManager.createNativeQuery(
                "DELETE FROM comment").executeUpdate();
    }
}
