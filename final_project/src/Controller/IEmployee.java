package Controller;

import Model.*;

interface IEmployee extends IUser{
void deleteEmployee(String id);
boolean loginEmployee(Employee emp);
void addUser(Employee emp);
Employee getUser(String Username,String password);
 
}