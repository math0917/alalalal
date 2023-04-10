INF = 987654321

def dfs(idx, cnt, board, paper):
    global ans

    if idx == 100:  # 1~100의 모든 셀을 탐색한 경우
        ans = min(ans, cnt)  # 현재까지 사용한 색종이 수의 최소값으로 갱신
        return

    if cnt >= ans:  # 현재까지 사용한 색종이 수가 이미 최소값보다 큰 경우
        return

    r, c = idx // 10, idx % 10

    if board[r][c] == 0:  # 현재 셀이 0인 경우 다음 셀 탐색
        dfs(idx+1, cnt, board, paper)
        return

    for k in range(5, 0, -1):  # 큰 종이부터 검사
        if paper[k] == 0:  # 현재 크기의 종이가 없는 경우 다음 크기의 종이 검사
            continue

        if r + k > 10 or c + k > 10:  # 색종이를 놓을 공간이 부족한 경우 다음 크기의 종이 검사
            continue

        flag = True
        for i in range(r, r+k):
            for j in range(c, c+k):
                if board[i][j] == 0:  # 색종이를 놓을 공간에 0이 있는 경우
                    flag = False
                    break
            if not flag:
                break

        if flag:  # 색종이를 놓을 수 있는 경우
            for i in range(r, r+k):
                for j in range(c, c+k):
                    board[i][j] = 0  # 색종이를 놓은 곳을 0으로 바꿈
            paper[k] -= 1  # 색종이 사용
            dfs(idx+1, cnt+1, board, paper)
            paper[k] += 1  # 백트래킹
            for i in range(r, r+k):
                for j in range(c, c+k):
                    board[i][j] = 1  # 원래대로 복구

    return

board = [list(map(int, input().split())) for _ in range(10)]
paper = [0, 5, 5, 5, 5, 5]
ans = INF

dfs(0, 0, board, paper)

if ans == INF:
    print(-1)
else:
    print(ans)