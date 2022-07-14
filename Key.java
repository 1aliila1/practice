
public class Key implements Comparable <Key> {

    private final static String KEY_FORMAT_STRING = "ID: %1d | Шифр уборочной работы: %1s | День недели: %1s |";

    //Переменные экземпляра
    private int id;
    private String cipher;
    private int day;

    //Конструктор без параметров
    public Key(){
        id = 0; cipher = ""; day = 0;
    }

    //Конструктор с параметрами
    public Key(int id, String cipher, int day){
        this.id = id;
        this.cipher = cipher;
        this.day = day;
    }

    //Сеттеры и геттеры
    public void setId(int id){this.id = id;}
    public void setCipher(String cipher){this.cipher = cipher;}
    public void setDay(int day){this.day = day;}

    public int getId(){return id;}
    public String getCipher(){return cipher;}
    public int getDay(){return day;}

    //Строка-описание объекта
    public String toString(){
        return String.format(KEY_FORMAT_STRING, id, cipher, day);
    }

    //Метод, обеспечивающий сравнение объектов
    public boolean equals (Object object) {
        if (object == null) return false;
        if (object == this) return true;
        if (getClass() != object.getClass()) return false;
        Key key = (Key)object;
        return (id == key.id && cipher.equals(key.cipher) && day == (key.day));
    }

    //Метод, определяющий хэш-код объекта
    public int hashCode(){
        return 7 * (Integer.valueOf(id)).hashCode() + 11 * cipher.hashCode() + 13 * (Integer.valueOf(day)).hashCode();
    }

    @Override
    public int compareTo(Key that) {
        if (this.id != that.id) {
            return (this.id < that.id ? -1 : 1);
        }

        if (this.day != that.day) {
            return (this.day < that.day ? -1 : 1);
        }

        return 0;
    }
}