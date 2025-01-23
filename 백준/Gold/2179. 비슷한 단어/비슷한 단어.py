N = int(input())
visited = []


class TrieNode:
    def __init__(self):
        self.count = 0
        self.children = {}


class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word):
        root = self.root

        for w in word:
            if w in root.children.keys():
                root.children[w].count += 1
            else:
                root.children[w] = TrieNode()
                root.children[w].count += 1
            root = root.children[w]


def dfs(node, height):
    global stack

    if node.count == 1:
        if height > 1:
            if prefix[1] < height:
                prefix[1] = height
                prefix[0] = stack[:-1]
        return

    for word, node in node.children.items():
        stack.append(word)
        dfs(node, height + 1)
        stack.pop()


trie = Trie()
stack = []
words = []

prefix = ["", 0]

for _ in range(N):
    word = input()
    if word not in words:
        words.append(word)
        trie.insert(word)

dfs(trie.root, 0)
answer = list(filter(lambda x: x.startswith(''.join(prefix[0])), words))

for i in range(2):
    print(answer[i])