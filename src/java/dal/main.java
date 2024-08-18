/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.User;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class main {
      public static void main(String[] args) throws ParseException {
           UserDBContext dao = new UserDBContext();
        ArrayList<User> userList = (ArrayList<User>) dao.listAll();
        User user = dao.getUserByID(7);
          System.err.println(user);
      CartDao cartDao = new CartDao();
       cartDao.increaseItemQuantity(1, 11);
     
      }
}
