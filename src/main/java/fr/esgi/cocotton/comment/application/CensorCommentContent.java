package fr.esgi.cocotton.comment.application;

import fr.esgi.cocotton.comment.domain.Comment;
import fr.esgi.cocotton.comment.domain.CommentDao;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;

@Service
public class CensorCommentContent {

    private final CommentDao commentDao;
    private ArrayList<String> bannedWords;

    public CensorCommentContent(CommentDao commentDao) {
        this.commentDao = commentDao;
        // TODO get file path from properties
        this.bannedWords = retrieveBannedWordsFromFile("src/main/java/fr/esgi/cocotton/comment/application/censored-words.txt");
    }

    public ArrayList<String> retrieveBannedWordsFromFile(String filePath) {
        ArrayList<String> bannedWords = new ArrayList<>();
        File censuredWordsFile = new File(filePath);
        try(FileReader fileStream = new FileReader( censuredWordsFile );
            BufferedReader bufferedReader = new BufferedReader( fileStream ) ) {
            String line;
            while( (line = bufferedReader.readLine()) != null ) {
                for (String word: line.split(","))
                    bannedWords.add(word.trim());
            }
        } catch ( IOException ex ) {
            System.out.println(ex.getMessage());
        }
        return bannedWords;
    }

    public String censorComment(Comment comment) {
        String censoredCommentContent = comment.getContent();
        for (String bannedWord : this.bannedWords) {
            String censor = new String(new char[bannedWord.length()]).replace('\0', '*');
            censoredCommentContent = censoredCommentContent.replaceAll(bannedWord, censor);
        }
        return censoredCommentContent;
    }

    public String execute(Comment comment){
        return censorComment(comment);
    }
}
