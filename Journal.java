
import java.util.*;
public class Journal {

    //Формат записи о группе записей о выделяемых минутах:
    private final static String JOURNAL_FORMAT_STRING = "\"Журнал учета: %s, количество записей: %d\"";

    //Переменные экземпляра:
    private String name;
    private Map<Key, Integer> review;

    //Конструктор без параметров
    public Journal() {
        name = "";
        review = new TreeMap<Key, Integer>();
    }

    //Конструктор с заданным названием
    public Journal(String name) {
        this.name = name;
        review = new TreeMap<Key, Integer>();
    }

    //Конструктор с заданным названием и правилом, по которому будут упорядочиваться элементы
    public Journal(String name, Comparator comparator) {
        this.name = name;
        review = new TreeMap<Key, Integer>(comparator);
    }

    //Сеттеры и геттеры
    public void setRegisterName(String name) {
        this.name = name;
    }

    public String getRegisterName() {
        return name;
    }
    public Map<Key, Integer> getReview() { return review; }

    //Строка-описание объекта
    public String toString() {
        return String.format(JOURNAL_FORMAT_STRING, name, review.size());
    }

    //Запрос на добавление записи
    public boolean add(Key key, Integer mark) {
        if (review.containsKey(key)) return false;
        review.put(key, mark);
        return true;
    }

    //Запрос на удаление записи
    public boolean del(Key key) {
        if (review.containsKey(key)) {
            review.remove(key);
            return true;
        } else return false;
    }

    //Запрос на удаление всех записей о выделяемых минутах конкретного офиса
    public boolean del(int id) {
        Set<Map.Entry<Key, Integer>> setE = review.entrySet();
        Iterator<Map.Entry<Key, Integer>> it = setE.iterator();
        int i = 0;
        while (it.hasNext()) {
            Map.Entry<Key, Integer> keyVal = it.next();
            if (keyVal.getKey().getId() == id) {
                it.remove();
                i = i + 1;
            }
        }
        if (i == 0) return false;
        return true;
    }

    //Запрос на обновление выделяемых минут на ключе
    public boolean update(Key key, Integer mark) {
        if (!review.containsKey(key)) return false;
        review.put(key, mark);
        return true;
    }

    //Метод, возвращающий число записей о выделяемых минутах
    public int getSize() {
        return review.size();
    }

    //Метод, возвращающий карту записей о выделяемых минутах заданного офиса
    public Journal selectOfficeData(int id) {
        Journal subExReg = new Journal(String.format("%s: выборка по офису %7d", name, id));
        Set<Map.Entry<Key, Integer>> setE = review.entrySet();
        for (Map.Entry<Key, Integer> keyVal : setE)
            if (keyVal.getKey().getId() == id)
                subExReg.review.put(keyVal.getKey(), keyVal.getValue());
        return subExReg;
    }

    //Метод, возвращающий карту записей о выделяемых минутах по заданному шифру уборочной работы
    public Journal selectCipherData(String cipher) {
        Journal subExReg = new Journal(String.format("%s: выборка по шифру уборочной работы \"%s\"", name, cipher));
        Set<Map.Entry<Key, Integer>> setE = review.entrySet();
        for (Map.Entry<Key, Integer> keyVal : setE)
            if (keyVal.getKey().getCipher().equals(cipher))
                subExReg.review.put(keyVal.getKey(), keyVal.getValue());
        return subExReg;
    }

    //Метод, возвращающий карту записей о выделяемых минутах заданного офиса в заданном дне недели
    public Journal selectOfficeData(int id, int day) {
        Journal subExReg = new Journal(String.format("%s: выборка по офису с id %1d и дне недели \"%1s\"", name, id, day));
        Set<Map.Entry<Key, Integer>> setE = review.entrySet();
        for (Map.Entry<Key, Integer> keyVal : setE)
            if ((keyVal.getKey().getId() == id) && (keyVal.getKey().getDay()==(day)))
                subExReg.review.put(keyVal.getKey(), keyVal.getValue());
        return subExReg;
    }

    //Сортировка данных по возрастанию id и уменьшению дня недели
    public Journal sortIdAscDateDesc() {
        Journal journal = new Journal(String.format("%s\n(сортировка по возрастанию id и убыванию дня недели):\n", name), new CompIdAscDayDesc());
        Set<Map.Entry<Key, Integer>> setE = this.review.entrySet();
        for (Map.Entry<Key, Integer> keyVal : setE)
            journal.add(keyVal.getKey(), keyVal.getValue());
        return journal;
    }

    //Сортировка данных по возрастанию шифра уборочной работы и убыванию дня недели
    public Journal sortCipherAscDateDesc() {
        Journal journal = new Journal(String.format("%s\n(сортировка по возрастанию шифра уборочной работы и убыванию дня недели):\n", name), new CompCipherAscDayDesc());
        Set<Map.Entry<Key, Integer>> setE = this.review.entrySet();
        for (Map.Entry<Key, Integer> keyVal : setE) journal.add(keyVal.getKey(), keyVal.getValue());
        return journal;
    }

    //Сортировка данных по убыванию выделяемых минут
    public Journal sortQuantityDesc() {
        Journal journal = new Journal(String.format("%s\n(сортировка по убыванию выделяемых минут):\n", name), new CompMinuteDesc(review));
        Set<Map.Entry<Key, Integer>> setE = this.review.entrySet();
        for (Map.Entry<Key, Integer> keyVal : setE)
            journal.add(keyVal.getKey(), keyVal.getValue());
        return journal;
    }

    //Вывод журнала в окно терминала
    public void printRecord() {
        System.out.println(name);
        System.out.printf("%5s%20s%26s%20s%30s\n", "Номер", "ID офиса", "Шифр уборочной работы", "День недели", "Выделяемое время в минутах");
        int i = 1;
        Set<Map.Entry<Key, Integer>> setE = review.entrySet();
        for (Map.Entry<Key, Integer> keyVal : setE) {
            Key key = keyVal.getKey();
            System.out.printf("%3d %18d %24s %19s %20d\n", i, key.getId(), key.getCipher(), key.getDay(), keyVal.getValue());
            i = i + 1;
        }
    }
}
