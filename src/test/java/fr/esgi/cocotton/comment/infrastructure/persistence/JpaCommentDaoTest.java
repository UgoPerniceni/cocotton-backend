package fr.esgi.cocotton.comment.infrastructure.persistence;

import fr.esgi.cocotton.comment.domain.Comment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaCommentDaoTest {

    @Autowired
    JpaCommentDao jpaCommentDao;

    private String currentCommentId;
    private final String userId = "userId";

    Comment comment = Comment.builder().build();
    private final String content = "a comment content with bra";
    private final String censoredContent = "a comment content with ***";

    @Before
    public void init() {
        this.comment.setContent(this.content);
        this.currentCommentId = jpaCommentDao.save(this.comment);
    }
    
    @Test
    public void should_save_comment() {
        String id = jpaCommentDao.save(this.comment);
        assertThat(id).isNotNull();
        jpaCommentDao.deleteById(id);
    }
    
    @Test
    public void should_save_comment_with_censored_content() {
        String id = jpaCommentDao.save(this.comment);
        Comment fetchedComment = jpaCommentDao.findById(id).get();
        assertThat(fetchedComment.getContent()).isEqualTo(this.censoredContent);
        jpaCommentDao.deleteById(id);
    }

    @Test
    public void should_find_all_comments() {
        List<Comment> commentList = jpaCommentDao.findAll();
        System.out.println(commentList.size());
        assertThat(commentList).hasSize(1);
    }

    @Test
    public void should_find_all_comments_by_user() {
        List<Comment> commentListdd = jpaCommentDao.findAll();
        List<Comment> commentList = jpaCommentDao.findAllByUserId(this.userId);
        assertThat(commentList).hasSize(1);
    }

    @Test
    public void should_delete_comment_by_id() {
        String toDeleteId = jpaCommentDao.save(this.comment);
        jpaCommentDao.deleteById(toDeleteId);
        assertThat(jpaCommentDao.findById(toDeleteId)).isEmpty();
    }

    @After
    public void clear() {
        jpaCommentDao.deleteById(this.currentCommentId);
    }
}
