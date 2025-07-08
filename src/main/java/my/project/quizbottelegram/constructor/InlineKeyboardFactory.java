package my.project.quizbottelegram.constructor;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.*;

@Service
public class InlineKeyboardFactory {

    public InlineKeyboardMarkup createKeyboard(int column, InlineKeyboardButton... inlineKeyboardButtons) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        for (int i = 0; i < inlineKeyboardButtons.length; i += column) {
            int end = Math.min(inlineKeyboardButtons.length, i + column);

            rows.add(Arrays.asList(inlineKeyboardButtons).subList(i, end));
        }
        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }
}
