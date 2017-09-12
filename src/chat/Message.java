/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.Serializable;

/**
 *
 * @author Master
 */
public final class Message implements Serializable {
    private String text, originIp, destinationIp, nick;
    private short port;

    public Message(String text, String originIp, String destinationIp, String nick, short port) {
        setText(text);
        setOriginIp(originIp);
        setDestinationIp(destinationIp);
        setNick(nick);
        setPort(port);
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOriginIp() {
        return originIp;
    }

    public void setOriginIp(String ip) {
        this.originIp = ip;
    }

    public String getNick() {
        return nick;
    }
    
    public void setNick(String nick) {
        this.nick = nick;
    }
    
    public short getPort() {
        return port;
    }

    public void setPort(short port) {
        this.port = port;
    }
    public void setPort(int port) {
        this.port = (short)port;
    }

    public String getDestinationIp() {
        return destinationIp;
    }
    
    private void setDestinationIp(String destinationIp) {
        this.destinationIp = destinationIp;
    }
    
    
}
