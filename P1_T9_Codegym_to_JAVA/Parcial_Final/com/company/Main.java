package com.company;
//AUTHOR: DANIEL NUÑEZ
//DATE: 26/03/2021
//DESCRIPTION: Parcial finish term
 import java.util.Scanner;
 public class Main{
     public static void main (String[] args){
         p_show_info_program();
         double v_sum=0;
         for (int i=1; i<=5; i=i+1){
             v_sum=v_sum+p_height();
         }
         System.out.println (v_sum);
         System.out.println("the averague height is"+(v_sum)/5);

     }
     public static void p_show_info_program(){
         //DESCRIPTION: this method show info program
         System.out.println("____________________________");
         System.out.println("     averague heigth v1.0   ");
         System.out.println("      Maker:Daniel Nuñez    ");
         System.out.println("      Date: 2021/March/26   ");
         System.out.println("____________________________");

     }
     public static int p_height () {
         //DESCRIPTION: this methodd return the heigh of the childrens
         Scanner keyboard = new Scanner (System.in);
         System.out.println("input the heigth of children (cm)");
         int p_height = keyboard.nextInt();
         while (p_height < 50 || p_height > 200)
         {
             System.err.println("ERR: the height cannot be more than 200 cm or less than 50 cm ");
             p_height= keyboard.nextInt();
         }
         return p_height;
     }
}
