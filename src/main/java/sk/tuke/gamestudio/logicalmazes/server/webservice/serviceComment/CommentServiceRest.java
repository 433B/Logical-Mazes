package sk.tuke.gamestudio.logicalmazes.server.webservice.serviceComment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.logicalmazes.entity.Comment;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentServiceRest {
    @Autowired
    private CommentService commentService;

    @GetMapping("/{game}")
    List<Comment> getComments(@PathVariable String game) {
        return commentService.getComments(game);
    }

    @PostMapping
    void addComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
    }
}
