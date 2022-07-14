import java.util.*;

//Сортировка данных по возрастанию номера офиса и убыванию дня недели
public class CompIdAscDayDesc implements Comparator <Key>{
    public int compare (Key ex1, Key ex2){
        int id1 = ex1.getId();
        int id2 = ex2.getId();

        int date1 = ex1.getDay();
        int date2 = ex2.getDay();

        if (id1<id2) return -1;
        if (id1>id2) return 1;


        if ((date1)>((date2))) return -1;
        if ((date1)<(date2)) return 1;
        return 0;
    }
}