/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 *
 * @author Master
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException {
       Client client1 = new Client(), client2 = new Client((short)6967);
       Server server = new Server();
    }
    
}
