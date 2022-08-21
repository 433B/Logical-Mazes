package sk.tuke.gamestudio.logicalmazes.server.webservice.serviceComment;

import org.springframework.stereotype.Repository;
import sk.tuke.gamestudio.logicalmazes.entity.Comment;

import java.util.List;

@Repository
public interface CommentService {
    void addComment(Comment comment);

    List<Comment> getComments(String game);

    void reset();
}
