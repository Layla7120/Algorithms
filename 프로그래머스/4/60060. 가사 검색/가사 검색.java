import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TrieNode {
    public int count = 0;
    public HashMap<Character, TrieNode> children;

    public TrieNode() {
        this.count = 0;
        this.children = new HashMap<>();
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word){
        TrieNode node = root;
        for(char c: word.toCharArray()){
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
            node.count++;
        }
    }

    public int search(String query){
        TrieNode node = root;
        for (char q : query.toCharArray()) {
            if (q == '?'){
                return node.count;
            }
            if (!node.children.containsKey(q)){
                return 0;
            }
            node = node.children.get(q);
        }
        return node.count;
    }
}

class Solution {
    public List<Integer> solution(String[] words, String[] queries){
        Map<Integer, Trie> trie = new HashMap<>();
        Map<Integer, Trie> reversedTrie = new HashMap<>();
        List<Integer> answer = new ArrayList<>();

        for (String word : words){
            int length = word.length();
            trie.putIfAbsent(length, new Trie());
            reversedTrie.putIfAbsent(length, new Trie());

            trie.get(length).insert(word);
            reversedTrie.get(length).insert(new StringBuilder(word).reverse().toString());
        }

        for (String query : queries){
            int length = query.length();

            if (!trie.containsKey(length)){
                answer.add(0);
                continue;
            }

            if (query.charAt(length - 1) == '?' && query.charAt(0) == '?'){
                int total = 0;

                for (TrieNode t : trie.get(length).root.children.values()) {
                    total += t.count;
                }
                
                answer.add(total);
                continue;
            }
            int result;
            if (query.charAt(0) == '?'){
                result = reversedTrie.get(length).search(new StringBuilder(query).reverse().toString());
            }
            else {
                result = trie.get(length).search(query);
            }
            answer.add(result);
        }
        return answer;
    }
}
