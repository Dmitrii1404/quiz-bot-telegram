package my.project.quizbottelegram.templates;

import com.vdurmont.emoji.EmojiParser;

public class TextTemplate {

    public static String START_COMMAND_TEXT = EmojiParser.parseToUnicode("\uD83C\uDF5C") +
            " Quiz-Bot - это бот, который поможет тебе подготовиться к техническим собеседованиям в Backend разработке";

    public static String START_BUTTON_JAVA = "Java " + EmojiParser.parseToUnicode("☕");

    public static String MENU_BUTTON = "Menu";

    public static String NEXT_BUTTON = "Next";

}
