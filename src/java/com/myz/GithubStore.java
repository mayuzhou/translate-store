package com.myz;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author mayuzhou
 * @date 2020/04/30
 **/
public class GithubStore {

    static String url="https://github.com/mayuzhou/translate-store.git";
//    static String rootPath=new File("").getAbsoluteFile().getPath()+"/translate-store";

    static String rootPath="/Users/mayuzhou/translate-store";
    static String wordCut="-------";
    static String filedCut=":::";





    public static void main(String[] args) throws IOException {
        Set<Word> words = Word.toWord(new FileInputStream("/Users/mayuzhou/java/code/translate-store/word.txt"));
        System.out.println(words);
    }


    static class Word implements Comparable {
        public String word;
        public String trans;

        public Word(String word, String trans) {
            this.word = word;
            this.trans = trans;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            };
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(word);
        }

        public static Set<Word> toWord(FileInputStream in) throws IOException {
            Set<Word> words=new TreeSet<>();
            BufferedReader reader=new BufferedReader(new InputStreamReader(in));
            String wordString=IOUtils.toString(reader);

            if(StringUtils.isNoneEmpty(wordString)){
                String[] oneWordAndTrans = wordString.trim().split(wordCut);
                for (String oneWordAndTran : oneWordAndTrans) {
                    String[] split = oneWordAndTran.split(filedCut);
                    if(split!=null&&split.length>1){
                        words.add(new Word(split[0].trim(),split[1].trim()));
                    }
                }
            }
            IOUtils.closeQuietly(in);
            return words;

        }

        @Override
        public int compareTo( Object o) {
            if(o instanceof Word){
                return this.word.compareTo(((Word) o).word);
            }
            return -1;
        }
    }
}
