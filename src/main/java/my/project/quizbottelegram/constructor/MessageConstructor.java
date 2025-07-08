package my.project.quizbottelegram.constructor;

import lombok.AllArgsConstructor;
import my.project.quizbottelegram.model.Poll;
import my.project.quizbottelegram.templates.TextTemplate;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;

@Service
@AllArgsConstructor
public class MessageConstructor {

    private final InlineKeyboardConstructor inlineKeyboardConstructor;

    public SendMessage getStartCommand(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(TextTemplate.START_COMMAND_TEXT);
        sendMessage.setReplyMarkup(inlineKeyboardConstructor.getStartInlineKeyboard());

        return sendMessage;
    }

    public DeleteMessage getDeleteCommand(String chatId, int messageId) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(chatId);
        deleteMessage.setMessageId(messageId);

        return deleteMessage;
    }

    public SendMessage getExampleCodeCommand(String chatId, String code) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("```\n" + code + "\n```");
        sendMessage.setParseMode("Markdown");

        return sendMessage;
    }

    public SendPoll getPollCommand(String chatId, Poll poll) {
        SendPoll sendPoll = new SendPoll();
        sendPoll.setChatId(chatId);
        sendPoll.setQuestion(poll.getTask());
        sendPoll.setExplanation(poll.getDescription());
        sendPoll.setOptions(poll.getAnswers());
        sendPoll.setCorrectOptionId(poll.getCorrectAnswer());
        sendPoll.setType("quiz");
        sendPoll.setReplyMarkup(inlineKeyboardConstructor.getTaskInlineKeyboard());

        return sendPoll;
    }

    public EditMessageReplyMarkup removeInlineKeyboard(String chatId, int messageId) {
        EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();
        editMessageReplyMarkup.setChatId(chatId);
        editMessageReplyMarkup.setMessageId(messageId);
        editMessageReplyMarkup.setReplyMarkup(null);

        return editMessageReplyMarkup;
    }
}
