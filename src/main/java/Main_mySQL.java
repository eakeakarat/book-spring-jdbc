import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main_mySQL {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config_mySQL.xml");
        BookDao bookDao = context.getBean("bookDaoImp", BookDao.class);

        Book tmpBook = new Book(0,"",0);

        List<Book> bookList = bookDao.findAll();
        for (Book book : bookList) {
            System.out.println(book);
        }

        // ---- insert book ----
        Book newBook = new Book(4, "Basic to connect with mySQL", 555);
        Book newBook2 = new Book(8, "New Book Too", 111);
        bookDao.save(newBook);
        bookDao.save(newBook2);

        // ---- select book ----
        Book spring = bookDao.findById(4);
        System.out.println("---findById: " + spring);
        Book spring2 = bookDao.findById(8);
        System.out.println("---findById: " + spring2);

        bookList = bookDao.findAll();
        for (Book book : bookList) {
            System.out.println(book);
        }

        // ---- update book ----
        System.out.println("->> Update book price for book id 4 & 445");
        newBook.setPrice(800);
        newBook2.setPrice(1000);
        newBook2.setName("Set new name to 445");
        bookDao.update(4, newBook);
        bookDao.update(445, newBook2);
//
        spring = bookDao.findById(4);
        System.out.println("---findById: " + spring);

        // ---- delete book ----
        System.out.println("->> Delete book id 4 & 8");
        bookDao.deleteById(4);
        bookDao.deleteById(8);

        bookList = bookDao.findAll();
        for (Book book : bookList) {
            System.out.println(book);
        }


        // ----- Reset Database to default ------
        tmpBook.setName("More book id");
        tmpBook.setPrice(20);
        bookDao.update(445,tmpBook);



    }
}
