## REST API сервис для доставки еды

## Стек

Java 17

Spring Boot (Web, Validation, Data JPA)

БД: H2 (in-memory)

Сборка: Maven

Swagger/OpenAPI

## Запуск приложения
```
mvn spring-boot:run
```

## Через Docker
```
docker-compose up
```

## Swagger

После запуска приложения документация АРІ доступна по адресу:

```

http://localhost:8080/swagger-ui/index.html

```

OpenAPI-описание:

```

http://localhost:8080/v3/api-docs

```

## Эндпоинты

### Пользователи

1\.POST /users — регистрация пользователя.

2\. GET /users/{id} — профиль пользователя.

3\. PUT /users/{id} — обновление профиля.

4\. GET /users?role=... — фильтр по роли.

5\. DELETE /users/{id} — деактивация

### Рестораны

7\. POST /restaurants — создать ресторан.

8\. GET /restaurants — список ресторанов с фильтрами по кухне и рейтингу.

9\. GET /restaurants/{id} — детально.

10\. PUT /restaurants/{id} — обновить ресторан.

11\. DELETE /restaurants/{id} — закрыть ресторан.

12\. POST /restaurants/{id}/menu — добавить блюдо.

13\. GET /restaurants/{id}/menu — список блюд.

### Меню

14\. PUT /menu/{id} — обновить блюдо.

15\. DELETE /menu/{id} — убрать блюдо.

16\. PATCH /menu/{id}/availability — изменить доступность блюда

### Корзина

18\. POST /cart/items — добавить блюдо в корзину.

19\. DELETE /cart/items/{id} — удалить блюдо.

21\. GET /cart — текущая корзина пользователя.

22\. DELETE /cart — очистить корзину.

### Заказы

24\. POST /orders — создать заказ из корзины.

25\. GET /orders/{id} — получить заказ.

26\. GET /orders?userId=... — заказы пользователя.

28\. DELETE /orders/{id} — отмена заказа.

29\. GET /orders?status=... — фильтр по статусу.

### Курьеры

31\. POST /couriers — зарегистрировать курьера.

32\. GET /couriers — список курьеров.

34\. GET /couriers/{id}/orders — активные заказы курьера.

35\. PATCH /couriers/{id}/status — (например, ONLINE/OFFLINE).

### Платежи

37\. POST /payments — создать платёж.

38\. GET /payments/{id} — статус платежа.

39\. PATCH /payments/{id}/status — обновить (например, для webhook).

40\. GET /payments?orderId=... — платежи по заказу.

### Отзывы

42\. POST /reviews — оставить отзыв ресторану.

43\. GET /reviews?restaurantId=... — отзывы о ресторане.

44\. PUT /reviews/{id} — обновить отзыв.

45\. DELETE /reviews/{id} — удалить отзыв.

### Уведомления

47\. POST /notifications — создать уведомление.

49\. PATCH /notifications/{id}/status — обновить статус.





