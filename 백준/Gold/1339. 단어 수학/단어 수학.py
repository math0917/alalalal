import sys

n = int(sys.stdin.readline().strip())
words = []
for i in range(n):
    words.append(sys.stdin.readline().strip())

# 각 알파벳의 등장 횟수를 세서 딕셔너리에 저장
alpha_count = {}
for word in words:
    for i in range(len(word)):
        alpha = word[i]
        if alpha not in alpha_count:
            alpha_count[alpha] = 0
        alpha_count[alpha] += 10**(len(word)-i-1)

# 등장 횟수가 높은 알파벳 순서대로 정렬하여 9부터 0까지 차례대로 숫자를 대응시킴
alpha_count = sorted(alpha_count.items(), key=lambda x: x[1], reverse=True)
ans = 0
for i in range(len(alpha_count)):
    ans += alpha_count[i][1] * (9-i)

print(ans)