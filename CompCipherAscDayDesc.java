import java.util.*;

//Сортировка данных по возрастанию шифра уборочной работы и убыванию номера дня недели
public class CompCipherAscDayDesc implements Comparator <Key> {
    public int compare (Key ex1, Key ex2){
        String cipher1 = ex1.getCipher();
        String cipher2 = ex2.getCipher();

        int date1 = ex1.getDay();
        int date2 = ex2.getDay();

        if (cipher1.compareTo(cipher2) < 0) return -1;
        if (cipher1.compareTo(cipher2) > 0) return 1;


            if ((date1)>((date2))) return -1;
            if ((date1)<(date2)) return 1;

        return 0;
    }
}