import sys
input = sys.stdin.readline

string = input().strip()

string = string.replace('c=','c')
string = string.replace('c-','c')
string = string.replace('dz=','d')
string = string.replace('d-','d')
string = string.replace('lj','l')
string = string.replace('nj','l')
string = string.replace('s=','l')
string = string.replace('z=','l')
print(len(string))