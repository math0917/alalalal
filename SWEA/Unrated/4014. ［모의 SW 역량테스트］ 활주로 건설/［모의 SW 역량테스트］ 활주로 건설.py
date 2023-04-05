

def find_path(row,col,flag):
  if col == 0 and not flag:
    
    have_to_consider = [road[row][col],1]
    idx = 1
    while idx < n:
      if have_to_consider[0] == road[row][idx]:
        have_to_consider[1] += 1
        idx +=1
      elif have_to_consider[0]+1 == road[row][idx]:
        if have_to_consider[1] >= l:
          have_to_consider = [road[row][idx],1]
          idx += 1
        else:
          return 0
      elif have_to_consider[0] == road[row][idx]+ 1:
        for i in range(1,l):
          if idx + i < n:
            if road[row][idx] == road[row][idx+i]:
              continue
            else:
              return 0
          else:
            return 0

        idx += l-1
        have_to_consider = [road[row][idx],0]
        idx += 1
      else:
        return 0
    return 1
  if row == 0:
    have_to_consider = [road[row][col],1]
    idx = 1
    while idx < n:
  
      if have_to_consider[0] == road[idx][col]:
        have_to_consider[1] += 1
        idx +=1
      elif have_to_consider[0]+1 == road[idx][col]:
        if have_to_consider[1] >= l:
          have_to_consider = [road[idx][col],1]
          idx += 1
        else:
          return 0
      elif have_to_consider[0] == road[idx][col]+ 1:
        for i in range(1,l):
          if idx + i < n:
            if road[idx][col] == road[idx+i][col]:
              continue
            else:
              return 0
          else:
            return 0
      
        idx += l-1
        have_to_consider = [road[idx][col],0]
        idx += 1
      else:
        return 0
   
  return 1

testCase = int(input())

for t in range(1, testCase+1):
  n, l = map(int,input().split())

  road = [list(map(int,input().split())) for _ in range(n)]
  result = 0

  result += find_path(0,0,False)
  result += find_path(0,0,True)
  for i in range(1,n):
    
    result += find_path(i,0,False)
    result += find_path(0,i,False)
    
  print("#",t," ",result,sep="")