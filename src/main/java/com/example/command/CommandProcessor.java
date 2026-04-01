package com.example.command;

public class CommandProcessor {

    public String process(String input) {
        if (input == null || input.isBlank()) {
            return "Please enter a command.";
        }

        String command = input.trim().toLowerCase();

        switch (command) {
            case "ping":
                return "pong";

            case "help":
                return "Available commands: ping, help, exit";

            case "exit":
                return "Goodbye!";

            default:
                return "Unknown command: " + input;
        }
    }
}