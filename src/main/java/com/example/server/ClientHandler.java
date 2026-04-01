package com.example.server;

import com.example.command.CommandProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {

    private final Socket clientSocket;
    private final CommandProcessor commandProcessor;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.commandProcessor = new CommandProcessor();
    }

    @Override
    public void run() {
        System.out.println("Client handler started for: " + clientSocket.getInetAddress());

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            writer.println("Welcome to tcp-order-server");
            writer.println("Type 'help' to see available commands.");

            String inputLine;

            while ((inputLine = reader.readLine()) != null) {
                String response = commandProcessor.process(inputLine);

                writer.println(response);

                if ("Goodbye!".equals(response)) {
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("Connection error with client: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                System.out.println("Client disconnected: " + clientSocket.getInetAddress());
            } catch (IOException e) {
                System.err.println("Error while closing client socket: " + e.getMessage());
            }
        }
    }
}