
import java.io.File;
import java.util.Scanner;

public class JournalDemo {
    public static void main (String[] args) {

        Journal journal = new Journal("Office");

        //Ввод данных в журнал из файла
        Scanner scanner = null;

        try {
            scanner = new Scanner(new File(args[0]));
        }catch (Exception e){
            System.out.println("Файл не найден");
        }

        while (scanner.hasNext()){
            int id = Integer.parseInt(scanner.next());
            String cipher = scanner.next();
            int date = Integer.parseInt(scanner.next());
            Integer quantity = Integer.parseInt(scanner.next());

            journal.add(new Key(id, cipher.replace("_"," "), date), quantity);
        }

        scanner.close();

        //Вывод журнала с естественным порядком сортировки
        System.out.println(String.format("Начальное состояние журнала записей о выделяемых минутах%s:", journal));
        journal.printRecord();

        System.out.println("\n----------------1.Запрос на добавление записи о выделяемых минутах.----------------");
        //Запрос на добавление записи с ключом, который уже есть в журнале
        Key key = new Key(250,"Мытье окон",2);
        if (journal.add(key, 65))
            System.out.println(String.format("Запись %s\nуспешно добавлена\n", key));
        else
            System.out.println(String.format("Запись %s\nнарушает правило уникальности ключа\n", key));


        //Запрос на добавление записи с ключом, которого нет в журнале
        key = new Key(253,"Чистка потолков",2);
        if (journal.add(key, 130))
            System.out.println(String.format("Запись %s\nуспешно добавлена\n", key));
        else
            System.out.println(String.format("Запись %s\nнарушает правило уникальности ключа\n", key));

        //Вывод журнала после добавления записи
        System.out.println(String.format("Журнал после добавления записи\n%s:\n", key));
        journal.printRecord();


        //Запрос на изменение оценки
        System.out.println("\n----------------2.Запрос на обновление записи о выделяемых минутах.----------------");

        key = new Key(251,"Мытье окон",5);
        journal.update(key,40);

        //Вывод журнала после обновления выделяемых минут записи о выделяемых минутах
        System.out.println(String.format("После обновления выделяемых минут в записи:\n%s\n", key));
        journal.printRecord();

        //Запрос на выборку из карты записей о выделяемых минутахдля заданного офиса,
        System.out.println("\n----------------3.Запросы на выборку записей о выделяемых минутах.----------------");

        journal.selectOfficeData(251).printRecord();
        System.out.println();


        //Вывод записей о выделяемых минутах конкретного офиса, упорядоченного по номеру и дне недели
        journal.selectOfficeData(252,1).printRecord();
        System.out.println();

        journal.selectCipherData("Мытье окон").sortQuantityDesc().printRecord();


        //Запрос на удаление записей о выделяемых минутах
        System.out.println("\n----------------4.Запросы на удаление записей о выделяемых минутах.----------------");

        //Удаление существующей записи
        key = new Key(251,"Вынос мусора",4);
        if (journal.del(key)){
            System.out.println(String.format("Запись успешно удалена:\n%s\n", key));

            System.out.println(String.format("После удаления записи:\n"));
            journal.sortIdAscDateDesc().printRecord();
        } else
            System.out.println(String.format("Попытка удаления несуществующей записи:\n%s\n", key));


        //Попытка удаление записи, которой нет в журнала
        key = new Key(20,"Мытье светильников",2);
        if (journal.del(key))
            System.out.println(String.format("\nЗапись успешно удалена:\n%s", key));
        else
            System.out.println(String.format("\nПопытка удаления несуществующей записи:\n%s\n", key));

        //Удаляем из журнала все записи о выделяемых минутах офиса с определенным id
        System.out.println("Удаление всех записей о выделяемых минутах офиса с id 250");
        journal.del(250);
        System.out.println("После удаления всех записей о выделяемых минутах офиса c id 250:\n");
        journal.sortCipherAscDateDesc().printRecord();

    }
}