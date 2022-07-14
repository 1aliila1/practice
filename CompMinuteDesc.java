import java.util.*;

//Сортировка данных по убыванию выделяемых минут
public class CompMinuteDesc implements Comparator <Key>{
    Map <Key, Integer> register;
    public CompMinuteDesc(Map <Key, Integer> register){ //конструктор
        this.register = register;
    }
    public int compare (Key ex1, Key ex2){
        Integer result1 = register.get(ex1);
        Integer result2 = register.get(ex2);
        if (result1 > result2) return -1;
        return 1;
    }
}