/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CMD;

/**
 *
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class TestCmdLine {
    
    public static void main(String[] args) {
          System.out.println("argumentos  " + args.length);
        for (String arg : args) {
          
            System.out.println("ARGUMENTO : " + arg);   
        }
        
    }
    
}
