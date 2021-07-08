package fr.esgi.cocotton.comment.domain;

import java.io.*;
import java.util.ArrayList;

public class CensorCommentContent {

    public static ArrayList<String> retrieveBannedWordsFromFile(String filePath) {
        ArrayList<String> bannedWords = new ArrayList<>();
        File censuredWordsFile = null;
        try{
            censuredWordsFile = new File(filePath);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

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
            content = content.replace(bannedWord, censor);
        }
        return content;
    }
}
