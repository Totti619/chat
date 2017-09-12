/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client;

import chat.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Master
 */
public class Client extends javax.swing.JFrame implements Runnable {
    
    private short destinationPort;
    private String nick, messageText, originIp, destinationIp;
    private String serverIp = "192.168.1.10";
    private short CLIENT_PORT = 6968, SERVER_PORT = 6969;
    
    Thread thread;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    Socket outputSocket, inputSocket;
    ServerSocket serverSocket;
    Message outputMessage, inputMessage;
    
    public Client() throws UnknownHostException {
        initComponents();
        originIp = Inet4Address.getLocalHost().getHostAddress();
        setVisible(true);
        thread = new Thread(this);
        thread.start();
    }
    public Client(short port) throws UnknownHostException {
        this();
        this.CLIENT_PORT = port;
    }

    private void send() {
        if (messageTextFieldHasText()) {
            serverIp = serverIpTextField.getText();
            messageText = messageTextField.getText();
            nick = nickTextField.getText();
            destinationIp = destinationIpTextField.getText();
            destinationPort = Short.parseShort(portTextField.getText());
            
            messagesTextArea.append(nick + ": " + messageText + "\n"); 
            clearMessage();
            outputMessage = new Message(messageText, originIp, destinationIp, nick, destinationPort);
            outputSocket = null;
            oos = null;
            try {
                outputSocket = new Socket(serverIp, SERVER_PORT);
                oos = new ObjectOutputStream(outputSocket.getOutputStream());
                oos.writeObject(outputMessage);
                oos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                messagesTextArea.append(ex.getMessage() + "\n");
            } catch (Exception ex) {
                ex.printStackTrace();
                messagesTextArea.append(ex.getMessage() + "\n");
            }
        }
    }
    
    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(CLIENT_PORT);
            while(true) {
                inputSocket = serverSocket.accept();
                ois = new ObjectInputStream(inputSocket.getInputStream());
                inputMessage = (Message)ois.readObject();
                messagesTextArea.append(inputMessage.getNick() + ": " + inputMessage.getText() + "\n");
                
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            messagesTextArea.append(ex.getMessage() + "\n");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            messagesTextArea.append(ex.getMessage() + "\n");
        } catch (Exception ex) {
            ex.printStackTrace();
            messagesTextArea.append(ex.getMessage() + "\n");
        }
        
    }

    private void clearConversation() {
        messagesTextArea.setText("");
    }
    
    private void clearMessage() {
        messageTextField.setText("");
    }
    private boolean messageTextFieldHasText() {
        return !messageTextField.getText().equals("");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        messageTextField = new javax.swing.JTextField();
        buttonSend = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        messagesTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        nickTextField = new javax.swing.JTextField();
        destinationIpTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        portTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        buttonClearConversation = new javax.swing.JButton();
        buttonClearMessage = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        serverIpTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client - Chat");

        buttonSend.setText("Send");
        buttonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSendActionPerformed(evt);
            }
        });

        messagesTextArea.setEditable(false);
        messagesTextArea.setBackground(new java.awt.Color(255, 255, 204));
        messagesTextArea.setColumns(20);
        messagesTextArea.setRows(5);
        jScrollPane2.setViewportView(messagesTextArea);

        jLabel1.setText("Nick:");

        nickTextField.setText("Anonymous");

        destinationIpTextField.setText("localhost");
        destinationIpTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destinationIpTextFieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Destination IP:");

        portTextField.setText("6968");

        jLabel3.setText("Port:");

        buttonClearConversation.setText("Clear Conversation");
        buttonClearConversation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClearConversationActionPerformed(evt);
            }
        });

        buttonClearMessage.setText("Clear Message");
        buttonClearMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClearMessageActionPerformed(evt);
            }
        });

        jLabel4.setText("Server IP:");

        serverIpTextField.setText("192.168.1.10");
        serverIpTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serverIpTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nickTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(serverIpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(destinationIpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(portTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonClearConversation)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonClearMessage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(messageTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSend)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nickTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(destinationIpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(portTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonClearConversation)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(serverIpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(messageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSend)
                    .addComponent(buttonClearMessage))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSendActionPerformed
        send();
    }//GEN-LAST:event_buttonSendActionPerformed

    private void destinationIpTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_destinationIpTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_destinationIpTextFieldActionPerformed

    private void buttonClearConversationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonClearConversationActionPerformed
        clearConversation();
    }//GEN-LAST:event_buttonClearConversationActionPerformed

    private void buttonClearMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonClearMessageActionPerformed
        clearMessage();
    }//GEN-LAST:event_buttonClearMessageActionPerformed

    private void serverIpTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serverIpTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serverIpTextFieldActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClearConversation;
    private javax.swing.JButton buttonClearMessage;
    private javax.swing.JButton buttonSend;
    private javax.swing.JTextField destinationIpTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField messageTextField;
    private javax.swing.JTextArea messagesTextArea;
    private javax.swing.JTextField nickTextField;
    private javax.swing.JTextField portTextField;
    private javax.swing.JTextField serverIpTextField;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) throws UnknownHostException {
        new Client();
    }
    
}
