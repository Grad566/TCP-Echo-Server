## Многопоточный TCP Echo Server
Простой многопоточный TCP Echo Server

### Требования для локального запуска
jdk - 21

gradle - 8.7

### EchoServer
На вход принимает порт и ExecutorService.
```
EchoServer server = new EchoServer(9091, Executors.newFixedThreadPool(15));
server.start();
```

### Запуск тестов
``` ./gradlew test```
