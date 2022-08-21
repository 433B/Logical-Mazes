package sk.tuke.gamestudio.logicalmazes.server.webservice.serviceRating;

import sk.tuke.gamestudio.logicalmazes.entity.Rating;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class RatingServiceJPA
        implements RatingService{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void setRating(Rating rating) {
        entityManager.persist(rating);
    }

    @Override
    public Double getAverageRating(String game) {
        List<Rating> ratings =
                entityManager.createQuery(
                                "select s from Rating s")
                        .getResultList();
        int value = 0;

        for (Rating r : ratings) {
            value += r.getRating();
        }

        return Double
                .valueOf(value / ratings.size());
    }

    @Override
    public List<Rating> getRating(String game) {
        return entityManager.createQuery(
                        "select s from Rating s where s.game = :game")
                .setParameter("game", game)
                .setMaxResults(10)
                .getResultList();
    }

    @Override
    public void reset() {
        entityManager.createNativeQuery(
                "DELETE FROM rating").executeUpdate();
    }
}