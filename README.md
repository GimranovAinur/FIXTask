# Программа для подсчета кратчайшего пути коня на шахматной доске M*N
## Установка
```
$ git clone git@github.com:GimranovAinur/FIXTask.git
$ cd knightchess
$ mvn install
```

## Запуск
```
$ mvn spring-boot:run
```

## Использование
После запуска перейдите по нужной ссылке или скопируйте ее в адресную строку:

Java Servlet:
http://localhost:8080/horse/servlet/count?width=10&height=14&start=B1&end=A3 

Spring Rest Controller:
http://localhost:8080/horse/rest/count?width=10&height=14&start=B4&end=A3

Введите необходимые параметры
