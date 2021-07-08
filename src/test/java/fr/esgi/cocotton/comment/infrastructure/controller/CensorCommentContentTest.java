package fr.esgi.cocotton.comment.infrastructure.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static fr.esgi.cocotton.comment.domain.CensorCommentContent.censorComment;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CensorCommentContentTest {

    private final String content = "a comment content with bra";
    private final String censoredContent = "a comment content with ***";

    @Test
    public void should_return_censured_string(){
        String returnedString = censorComment(this.content);
        Assert.assertEquals(this.censoredContent, returnedString);
    }

}
