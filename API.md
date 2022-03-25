# Описание API

Все вызовы API принимают и отдают JSON-данные.

## Для администратора

Все вызовы для администратора требуют базовую HTTP-авторизацию.

#### GET /admin/quizzes

Получение списка всех опросов. Тело ответа:
```
[
    {
        id,             # Число
        name,           # Строка
        description,    # Строка
        startDate,      # Формат YYYY-MM-DD
        stopDate        # Формат YYYY-MM-DD
        questions: [
        {
            id,
            text,           # Текст вопроса
            type,           # Тип вопроса: INPUT, SELECT, CHECK_BOX
        },
        ...
        ]
    }, ...
]
```

#### POST /admin/quizzes

Создание нового опроса. Тело запроса:
```
{
    name,           # Строка
    description,    # Строка
    startDate,      # Строка YYYY-MM-DD
    stopDate        # Строка YYYY-MM-DD
}
```
Тело ответа:
```
{
    id, name, description, startDate, stopDate
}
```

#### GET /admin/quizzes/(id)

Получение подробной информации об одном опросе, с вопросами.
Тело ответа:
```
{
    id, 
    name,           # Строка
    description,    # Строка
    startDate,      # Строка YYYY-MM-DD
    finishDate      # Строка YYYY-MM-DD
    questions: [
        {
            id,
            text,           # Текст вопроса
            type,           # Тип вопроса: INPUT, SELECT, CHECK_BOX
        },
        ...
    ]
}

```

#### DELETE /admin/quizzes/(id)

Удаление опроса.

#### PUT /admin/quizzes/(id)

Редактирование опроса. Тело запроса:
```
{
    name,           # Строка
    description,    # Строка
    stopDate        # Строка YYYY-MM-DD
}
```
Формат ответа:
```
{
    id, name, description, startDate, stopDate
}
```

#### GET /admin/quizzes/(quizId)/questions
Получение всех вопросов данного опроса
```
[   
    {
        text,           # Текст вопроса
        type,           # Тип вопроса: INPUT, SELECT, CHECK_BOX
    }, ...
]
```

#### POST /admin/quizzes/(quizId)/questions
Добавление нового вопроса к опросу id. Тело запроса:
```
{
    text,           # Текст вопроса
    type,           # Тип вопроса: INPUT, SELECT, CHECK_BOX
}
```
Тело ответа:
```
{
    id,
    text,           # Текст вопроса
    type,           # Тип вопроса: INPUT, SELECT, CHECK_BOX
}
```

#### GET /admin/quizzes/(quizId)/questions/(questionId)

Подробная информация об одном вопросе. Тело ответа:
```
{
    id,
    text,           # Текст вопроса
    type,           # Тип вопроса: INPUT, SELECT, CHECK_BOX
}
```

#### DELETE /admin/polls/(pollId)/questions/(questionId)

Удаление вопроса из опроса.

#### PUT /admin/polls/(pollId)/questions/(questionId)

Изменение существующего вопроса. Тело запроса:
```
{
    text,           # Текст вопроса
    type,           # Тип вопроса: INPUT, SELECT, CHECK_BOX
}
```
Тело ответа:
```
{
    id,
    text,           # Текст вопроса
    type,           # Тип вопроса: TEXT, CHOICE, MULTIPLE_CHOICE
}
```


## Для пользователя

#### GET /users/{userId}/quizzes

Получить список активных опросов. Тело ответа:
```
[
    {
        id,             # Число
        name,           # Строка
        description,    # Строка
        startDate,      # Формат YYYY-MM-DD
        stopDate        # Формат YYYY-MM-DD
        questions: [
        {
            id,
            text,           # Текст вопроса
            type,           # Тип вопроса: INPUT, SELECT, CHECK_BOX
        },
        ...
        ]
    }, ...
]
```

#### GET /users/{userId}/quizzes/quiz/{quizId}

Получение подробной информации об одном опросе, с вопросами.
Тело ответа:
```
{
    id, 
    name,           # Строка
    description,    # Строка
    startDate,      # Строка YYYY-MM-DD
    stopDate        # Строка YYYY-MM-DD
    questions: [
        {
            id,
            text,           # Текст вопроса
            type,           # Тип вопроса: INPUT, SELECT, CHECK_BOX
        },
        ...
    ]
}

```

#### POST /users/{userId}/quizzes/{quizId}

Прохождение опроса пользователем. Тело запроса:
```
{
    userId,     # Идентификатор пользователя
    answers: [{
                {
                question: {                 # Описание вопроса
                    id,                     # Идентификатор вопроса
                    type,                   # Тип вопроса: INPUT, SELECT, CHECK_BOX
                    text,                   # Текст вопроса, на момент прохождения
                },
                answer:   {
                    text,                   # Текст ответа
                }
              }, ...

}
```

#### GET /users/{userId}/quizzes/quizzesByUserId

Получить пройденные пользователем опросы, с детализацией выбранных ответов.
Тело ответа:
```
[
    {
        id,                 # Идентификатор заполненного опроса
        pollId,             # Идентификатор опроса
        answers: [
            {
                question: {                 # Описание вопроса
                    id,                     # Идентификатор вопроса
                    text,                   # Текст вопроса, на момент прохождения
                    type,                   # Тип вопроса: INPUT, SELECT, CHECK_BOX
                },
                answer: {
                     id,                    # Идентификатор ответа
                    'Text',                 # Текст ответа
                }
            },
            ...
        ]
    },
    ...
]
```
