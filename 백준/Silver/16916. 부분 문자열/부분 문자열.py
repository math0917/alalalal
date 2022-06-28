import sys
input = sys.stdin.readline

#시작 부분과 같은 부분이 있는 인덱스를 저장해둘 리스트
def make(pattern):
    j = 0
    for i in range(1,len(pattern)):
        while j > 0 and pattern[i] != pattern[j]:
            j = lst[j-1]
        if pattern[i] == pattern[j]:
            j += 1
            lst[i] = j

# 이제 string을 순회하며 찾되 만약 틀리면 합리적인 곳부터 탐색 시작
def kmp(string, pattern):
    make(pattern)
    j = 0
    for i in range(len(string)):
        while j > 0 and string[i]!=pattern[j]:
            j = lst[j-1]
        if string[i] == pattern[j]:
            if j == len(pattern)-1 :
                print(1)
                sys.exit()
            else:
                j+=1
S = input().strip()
R = input().strip()

lst = [0]*len(R)

kmp(S,R)

print(0)