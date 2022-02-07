

class User:
    count = 0

    def __init__(self, name, email, password):
        self.name= name
        self.email = email
        self.password = password
        
        self.follow_list = []
        self.follower_list = []
        User.count += 1
    
    def say_hello(self):
        print("안녕하세요 저는 {}입니다". format(self.name))
    def login(self, my_email,my_password):
        if self.email == my_email and self.password == my_password:
            print('로그인 성공, 환영합니다.')
        else:
            print('로그인 실패, 없는 아이디이거나 잘못된 비밀번호입니다.')
    def check_name(self,name):
        return self.name == name

    def follow(self, people):
        self.follow_list.append(people)
        people.follower_list.append(self)
    def num_following(self):
        return len(self.follow_list)
    def num_followers(self):
        return len(self.follower_list)
    def __str__(self):
        return "사용자 : {}, 이메일 : {}, 비밀번호 {}".format(self.name, self.email,'*'*len(self.password))
    
    @classmethod #class 메소드
    def number_of_users(cls):
        print('총 유저 수는 {}입니다.'.format(cls.count))
User.number_of_users()
user1 = User("김원표","math0917@naver.com","12345")
print(User.count)
user2 = User('메롱',"aodq@naver.com",'123456')
print(User.count)
user3 = User('우엑','aoqkdh123@naver.com','231')
print(User.count)

user1.say_hello()
user1.login("math0917@naver.com","12345")
user1.login("heofh","123")
print(user1.check_name('김원표'))
print(user1)
print(user2)

User.number_of_users()

user1.count = 5 #instance 변수에 추가되는거고
User.count = 5 #class 변수에 바꾸기
print(User.count) 



def add_print_to(original):
    def wrapper():
        print('함수 시작')
        original()
        print('함수 끝')
    return wrapper
#데코레이터 선언    
@add_print_to
def print_hello():
    print('안녕하세요')

print_hello()
#데코레이터의 장점 : 중복되는 부분을 하나의 함수로 만들어두고 데코레이터로 선언만 해두면
#보기 좋다.


class User:
    def __init__(self, name, email, password):
        self.name = name
        self.email = email
        self.password = password
    
    @classmethod
    def from_stirng(cls,string_params):
        temp = string_params.split(',')
        return cls(temp[0],temp[1],temp[2])
    @classmethod
    def from_list(cls,list_params):
        return cls(list_params[0],list_params[1],list_params[2])
    #staticmethod의 경우 객체생성이 되지 않아도 class이름으로 호출해서 사용 가능
    #즉 def는 객체이름 없이 모든곳에서 쓸 수 있지만 이 static은 이 객체를 통해서만 호출 가능
    @staticmethod
    def is_valid_email(email_address):
        return '@' in email_address
info_string = "강영훈,younghoon@codeit.kr,123456"
info_list = ["이윤수", "yoonsoo@codeit.kr", "abcdef"]
print(User.is_valid_email(info_list[1]))
younghoon = User.from_stirng(info_string)
yoonsoo = User.from_list(info_list)

# 인스턴스가 제대로 생성되었는지 확인
print(younghoon.name, younghoon.email, younghoon.password)
print(yoonsoo.name, yoonsoo.email, yoonsoo.password)