package org.Abgehoben;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.channel.concrete.NewsChannel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.util.Scanner;

public class Main {

    private static JDA jda;

    public static void main( String[] args ) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter bot token: ");
            String token = scanner.nextLine();
            if (token == null || token.isEmpty()) {
                System.out.println("Token file is missing or empty. Please provide a valid bot token.");
            }
            JDABuilder builder = JDABuilder.createDefault(token);

            try {
                jda = builder.build();
                break;
            } catch (Exception e) {
                System.out.println("Error creating JDA instance");
                e.printStackTrace();
            }
        }

        String channelId;
        while (true) {
            System.out.println("enter Channel ID:  ");
            channelId = scanner.nextLine();
            if (channelId == null || channelId.isEmpty()) {
                System.out.println("Channel ID is missing or empty. Please provide a valid channel ID.");
            } else {
                break;
            }
        }


        while (true) {
            System.out.println("Enter message: ");
            String message = scanner.nextLine();
            sendMessage(message, channelId);
        }
    }


    public static void sendMessage(String message, String channelId) {

        if (message.isEmpty()) {
            System.out.println("Message is empty. Please provide a valid message.");
            return;
        }

        NewsChannel Newschannel = jda.getNewsChannelById(channelId);

        TextChannel Textchannel = jda.getTextChannelById(channelId);
        //channel is null if wrong type

        if (Newschannel == null && Textchannel != null) {
            Textchannel.sendMessage(message).queue();
        }
        else if (Newschannel != null && Textchannel == null) {
            Newschannel.sendMessage(message).queue();
        }
        else {
            System.out.println("Channel not found. Please provide a valid channel ID.");
        }
    }
}
