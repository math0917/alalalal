class User:
    '''SNS user class'''
    number_users = 0

    def __init__(self, name, email, password):
        '''This class needs initailizer with SNS user's name, email, password'''
        self.name = name
        self.email = email
        self.password = password
        User.number_users += 1

    def say_hello(self):
        '''this function prints greeting message'''
        print('hello I am {}'.format(self.name))
    
    def __str__(self):
        '''this funtion returns information in string format'''
        return '이름 : {}, email : {}, password : {}'. format(self.name, self.email, self.password)
    @classmethod
    def number_of_users(cls):
        ''' returns total amount of SNS users '''
        return User.number_users
    
help(User)