import collections
global dic
dic = collections.defaultdict(set)

def solution(n, l, r):
    answer = 0
    l -= 1
    r -= 1
    
    return find(n,l,r)


def find(n,l,r):
    if n == 0:
        return 1
    cnt = 5 ** (n - 1)
    if dic[(n,l,r)]:
        return dic[(n,l,r)]
    else:
        arr = []
        a = 0
        for _ in range(5):
            arr.append((a, a + cnt - 1))
            a += cnt
        val = 0
        for i in range(5):
            if i == 2:
                continue
            else:
                if arr[i][1] < l or arr[i][0] > r:
                    continue
                else:
                    val += find(n-1, max(arr[i][0], l) % cnt, min(arr[i][1], r) % cnt)
        dic[(n,l,r)] = val
        return val
    