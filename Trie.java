
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

public class Trie {
    private TrieNode root;

    public Trie(){
        root = new TrieNode(false);
    }

    public static void main(String[] args ){
        Trie obj = new Trie();
        obj.insertString("car",true);
        obj.insertString("cat",true);
        obj.insertString("trie",true);
        obj.insertString("tree",true);
        obj.deleteString("car");
        obj.deleteString("cat");
        obj.insertString("dog",true);
        obj.DFSTrie(obj.getRoot(),"");
    }

    public TrieNode getRoot() {
        return root;
    }

    public void insertString(String str,boolean IsWord){
        InsertString(str.toCharArray(),0,str.length(),IsWord,getRoot());
    }

    public boolean isWord(String str){
        return IsWord(str.toCharArray(),0,str.length(),getRoot());
    }


    public boolean inTrie(String s){
        return InTrie(s.toCharArray(),0,s.length(),getRoot());
    }

    public void deleteString(String s){
        DeleteString(s.toCharArray(),0,s.length(),getRoot(),new LinkedList<>());
    }

    public void DFSTrie(TrieNode curr,String s){
        for (char c : curr.getLinks().keySet()){
            DFSTrie(curr.getLink(c),s+c);

        }
        if (curr.getWord())System.out.println(s);
    }

    private void DeleteString(char [] s, int i, int length, TrieNode curr, LinkedList<TrieNode> stk){
        if ( i == length){
            while ( curr != getRoot() ) {
                if ( curr.getLinks().size() == 0 ){
                    TrieNode last = stk.removeLast();
                    last.getLinks().remove(s[--i]);
                    curr = last;
                }
                else{
                    curr.setWord(false);
                    break;
                }
            }
        }
        else{
            stk.addLast(curr);
            if ( curr.contains(s[i])){
                DeleteString(s,i+1,length,curr.getLink(s[i]),stk);
            }
            else{
                System.out.println("Not In Trie");
            }
        }
    }

    private boolean InTrie(char [] str,int i, int length,TrieNode curr){
        if ( i == length){
            return true;
        }
        else{
            if ( curr.contains(str[i])){
                return InTrie(str,i+1,length,curr.getLink(str[i]));
            }
            else{
                return false;
            }
        }

    }

    private boolean IsWord(char [] str, int i, int length,TrieNode curr){
        if ( i == length){
            return curr.getWord();
        }
        else{
            if ( curr.contains(str[i])){
                return IsWord(str,i+1,length,curr.getLink(str[i]));
            }
            else{
                return false;
            }
        }
    }

    private void InsertString(char [] str,int i,int length,boolean IsWord,TrieNode curr){
        if ( i == length){
            curr.setWord(IsWord);
        }
        else if (curr.contains(str[i])){
            InsertString(str,i+1,length,IsWord,curr.getLink(str[i]));
        }
        else{
            curr.getLinks().put(str[i],new TrieNode(false));
            InsertString(str,i+1,length,IsWord,curr.getLink(str[i]));
        }
    }


}

class TrieNode{
    private HashMap<Character,TrieNode> links;
    private boolean isWord;

    public TrieNode(boolean IsWord){
        links = new HashMap<>();
        isWord = IsWord;
    }

    public boolean contains(char c){
        if ( links.containsKey(c)){
            return true;
        }
        return false;
    }

    public TrieNode getLink(char c){
        if (contains(c)){
            return links.get(c);
        }
        else{
            return null;
        }
    }

    public void setWord(boolean word) {
        isWord = word;
    }

    public HashMap<Character, TrieNode> getLinks(){
        return links;
    }

    public boolean getWord(){
        return isWord;
    }

}
