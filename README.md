

# QuizBot Telegram

Quiz-Bot - это Telegram бот, который поможет подготовиться к техническому собеседованию.

При запуске бота вы можете выбрать тему для вопросов. После этого бот формирует опрос: пример кода, задание и несколько вариантов ответа. Всегда есть только один правильный ответ, при неправильном ответе всплывает подсказка.

## Стек технологий
- Java 21
- Spring Boot
- Lombok
- TelegramBots API

## Структура проекта

```text
./src/main/quizbottelegram
    ├─ config               # Конфиги для инициализации и настройки Telegram бота
    ├─ constructor          # Конструкторы сообщений, кнопок и опросов.
    ├─ model                # Модель опроса
    ├─ service              # Сервис, описывающий работу бота
    └─ templates            # Шаблоны для кнопок и команд

./src/main/resources
    ├─ application.yaml     # Конфигурационный файл Spring Boot
    └─ questions.json       # Список всех вопросов
```

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