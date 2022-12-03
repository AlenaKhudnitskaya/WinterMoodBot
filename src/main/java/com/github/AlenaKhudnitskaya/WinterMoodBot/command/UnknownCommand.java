package com.github.AlenaKhudnitskaya.WinterMoodBot.command;

import com.github.AlenaKhudnitskaya.WinterMoodBot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UnknownCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    private static final String UNKNOWN_MESSAGE = "Не понимаю вас \uD83D\uDE1F, напишите /help, чтобы узнать доступные команды.";

    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), UNKNOWN_MESSAGE);
    }
}
