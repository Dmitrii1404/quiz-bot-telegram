

# QuizBot Telegram

Telegram-бот для подготовки к собеседованиям

## Стек технологий
- Java 21
- Spring Boot
- Lombok
- TelegramBots API

## Запуск
1. Вставьте в `src/main/resources/application.yaml` имя бота:
   ```yaml
   telegram:
     bot:
       username: YourBotName           <- сюда имя бота
       token: ${TELEGRAM_BOT_TOKEN}
2. Создайте `.env` файл в корне проекта и добавьте туда токен:
   ```angular2html
    TELEGRAM_BOT_TOKEN=YourSecretToken <- сюда токен бота
    ```

3. Запуск:
    ```angular2html 
    mvn spring-boot:run
    ```

## Добавление вопросов

Изначально загружены вопросы по Java. При желании в папке `src/main/resources/questions.json` можно добавить свои вопросы на любые темы