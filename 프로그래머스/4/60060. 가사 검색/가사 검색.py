class TrieNode:
    def __init__(self):
        self.count = 0
        self.childern = {}


class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word):
        node = self.root
        for w in word:
            if w not in node.childern:
                node.childern[w] = TrieNode()
            node = node.childern[w]
            node.count += 1

    def search(self, query):
        node = self.root
        for q in query:
            if q == '?':
                return node.count
            if q not in node.childern:
                return 0
            node = node.childern[q]
        return node.count


def solution(words, queries):
    trie = {}
    reversed_trie = {}
    answer = []

    # trie 초기화
    for word in words:
        length = len(word)
        if length not in trie:
            trie[length] = Trie()
            reversed_trie[length] = Trie()
        trie[length].insert(word)
        reversed_trie[length].insert(word[::-1])

    for query in queries:
        length = len(query)

        if length not in trie:
            answer.append(0)
            continue

        # 전체가 와일드 카드인 경우 처리
        if query[-1] == '?' and query[0] == '?':
            total = 0
            for i in trie[length].root.childern.values():
                total += i.count
            answer.append(total)
            continue

        # 접두사인 경우 reversed 적용
        if query[0] == '?':
            result = reversed_trie[length].search(query[::-1])
        else:
            result = trie[length].search(query)
        answer.append(result)

    return answer