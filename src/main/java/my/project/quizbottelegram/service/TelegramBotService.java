package my.project.quizbottelegram.service;


import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import my.project.quizbottelegram.config.TelegramBotConfig;
import my.project.quizbottelegram.constructor.MessageConstructor;
import my.project.quizbottelegram.constructor.poll.factory.JavaPollFactory;
import my.project.quizbottelegram.constructor.poll.factory.PollFactory;
import my.project.quizbottelegram.model.Poll;
import my.project.quizbottelegram.templates.CallbackData;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@AllArgsConstructor
@Slf4j
public class TelegramBotService extends TelegramLongPollingBot {

    private final TelegramBotConfig telegramBotConfig;
    private final MessageConstructor messageConstructor;
    private final PollFactory pollFactory;
    private final JavaPollFactory javaPollFactory;


    @Override
    public String getBotUsername() {
        return telegramBotConfig.getName();
    }

    @Override
    public String getBotToken() {
        return telegramBotConfig.getToken();
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            String chatId = update.getMessage().getChatId().toString();

            if (message.equals("/start")) {
                log.info("Starting bot");
                execute(messageConstructor.getStartCommand(chatId));
            }
        }
        else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            Message message = callbackQuery.getMessage();
            Integer callbackData = Integer.parseInt(callbackQuery.getData());

            Integer messageId = message.getMessageId();
            String chatId = String.valueOf(message.getChatId());

            if (callbackData.equals(CallbackData.START_COMMAND_JAVA_ID.getId())) {
                Poll poll = javaPollFactory.createPoll();

                new Thread(() -> {
                    try {
                        execute(messageConstructor.getDeleteCommand(chatId, messageId));
                        execute(messageConstructor.getExampleCodeCommand(chatId, poll.getCodeSnippet()));
                        execute(messageConstructor.getPollCommand(chatId, poll));
                    } catch (TelegramApiException e) {
                        log.error(e.getMessage());
                    }
                }).start();
            }
            else if (callbackData.equals(CallbackData.NEXT.getId())) {
                Poll poll = pollFactory.createPoll();

                new Thread(() -> {
                    try {
                        execute(messageConstructor.removeInlineKeyboard(chatId, messageId));
                        execute(messageConstructor.getExampleCodeCommand(chatId, poll.getCodeSnippet()));
                        execute(messageConstructor.getPollCommand(chatId, poll));
                    } catch (TelegramApiException e) {
                        log.error(e.getMessage());
                    }
                }).start();
            }
            else if (callbackData.equals(CallbackData.MENU.getId())) {
                new Thread(() -> {
                    try {
                        execute(messageConstructor.removeInlineKeyboard(chatId, messageId));
                        execute(messageConstructor.getStartCommand(chatId));
                    } catch (TelegramApiException e) {
                        log.error(e.getMessage());
                    }
                }).start();
            }
        }
    }
}
