
import sys

input = sys.stdin.readline


string = list(input().strip())
word = [[0]*26 for _ in range(len(string))]
for idx,char in enumerate(string):
    index = ord(char) - ord('a')
    for i in range(26):
        if i == index:
            word[idx][i] = word[idx-1][i]+1
        else:
            word[idx][i] = word[idx-1][i]

n = int(input())

proc = [list(input().split()) for _ in range(n)]

for alpha, start,finish in proc:
    index = ord(alpha)- ord('a')
    start = int(start)
    finish = int(finish)
    if start:
        print(word[finish][index]-word[start-1][index])
    else:
        print(word[finish][index])