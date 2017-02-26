package Server;

import java.sql.Timestamp;

/**
 * Created by Windows on 25.02.2017.
 */
public class Message {
    private int id;
    private String sender;
    private String receiver;
    private String body;
    private Timestamp timestamp;


    public Message(String sender, String receiver, String body) {
        this.sender = sender;
        this.receiver = receiver;
        this.body = body;
    }

    public Message(String sender, String receiver, String body, Timestamp timestamp) {
        this.sender = sender;
        this.receiver = receiver;
        this.body = body;
        this.timestamp = timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getBody() {
        return body;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
