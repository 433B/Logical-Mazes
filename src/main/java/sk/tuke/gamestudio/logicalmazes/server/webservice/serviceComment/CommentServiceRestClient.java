package sk.tuke.gamestudio.logicalmazes.server.webservice.serviceComment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.logicalmazes.entity.Comment;

import java.util.Arrays;
import java.util.List;

public class CommentServiceRestClient
        implements CommentService {
    @Value("${remote.server.api}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void addComment(Comment comment) {
        restTemplate.postForEntity(
                url + "/comment",
                comment,
                Comment.class);
    }

    @Override
    public List<Comment> getComments(String game) {
        return Arrays.asList(
                restTemplate
                        .getForEntity(
                                url + "/comment/" + game,
                                Comment[].class).getBody());
    }

    @Override
    public void reset() {
        throw new CommentServiceClient(
                "Not supported via web service");
    }
}