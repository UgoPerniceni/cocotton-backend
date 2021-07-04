package fr.esgi.cocotton.comment.domain;

import fr.esgi.cocotton.comment.domain.Comment;
import fr.esgi.cocotton.comment.domain.CommentDao;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;

public class CensorCommentContent {

    public static ArrayList<String> retrieveBannedWordsFromFile(String filePath) {
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

    public static String censorComment(String content) {
        ArrayList<String> bannedWords = retrieveBannedWordsFromFile("src/main/resources/censored-words.txt");
        for (String bannedWord : bannedWords) {
            String censor = new String(new char[bannedWord.length()]).replace('\0', '*');
            content = content.replaceAll(bannedWord, censor);
        }
        return content;
    }
}
