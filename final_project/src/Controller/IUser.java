package Controller;

import Model.*;

interface IUser{
void addUser(User user);
void updateUser(User user);
User searchUser(String name);
}