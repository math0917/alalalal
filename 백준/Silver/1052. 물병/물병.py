import sys
input = sys.stdin.readline

N, K = map(int, input().split())

answer = 0
# 1의 개수가 k개보다 크면
while bin(N).count('1') > K:
    # 가장 작은쪽에 있는애중에 1을 0으로 바꾸기
    plus = 2 ** (bin(N)[::-1].index('1'))
    answer += plus
    N += plus
print(answer)