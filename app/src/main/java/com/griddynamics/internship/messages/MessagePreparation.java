package com.griddynamics.internship.messages;

public class MessagePreparation {
    public static String getMessage(String [] args){
        Message message;
        if(args[0].contains("-in")){
            message = new MessageFromFile();
        }
        else{
            message = new MessageFromCli();
        }
        return message.get(args);
    }
}
