import Server.HibernateManager;
import Server.Message;
import org.hibernate.Session;

import java.sql.Timestamp;

/**
 * Created by Windows on 26.02.2017.
 */
public class Main {
    public static void main(String[] args) {
//        System.out.println((char) 27 + "[31mWarning! " + (char)27 + "[0m");

//        Если хочешь другой цвет, то измени "[31mWarning!". Например, на "[35mWarning!". Текст будет пурпурным.
//        30 - черный. 31 - красный. 32 - зеленый. 33 - желтый. 34 - синий. 35 - пурпурный. 36 - голубой. 37 - белый.

//        System.out.println((char) 27 + "[32mWarning! " + (char)27 + "[0m");
//        System.out.println((char) 27 + "[33mWarning! " + (char)27 + "[0m");

//        String s = "Warning!";
//        System.out.println((char) 27 + "[31m(PM)Alex: dsfhg");

        HibernateManager manager = HibernateManager.getInstance();
        Session session = manager.getSession();
        session.beginTransaction();
        Timestamp timestamp = new Timestamp(123456789L);
        session.save(new Message("dsfasd","sad","sd",timestamp));
        session.getTransaction().commit();

    }
}
