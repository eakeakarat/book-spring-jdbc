import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        BookDao bookDao = context.getBean("bookDaoImp", BookDao.class);

        List<Book> bookList = bookDao.findAll();
        for (Book book : bookList) {
            System.out.println(book);
        }

        // ---- insert book ----
        Book newBook = new Book(4, "Basic of Spring Framework", 556);
        bookDao.save(newBook);

        // ---- select book ----
        Book spring = bookDao.findById(4);
        System.out.println("---findById: " + spring);

        bookList = bookDao.findAll();
        for (Book book : bookList) {
            System.out.println(book);
        }

        // ---- update book ----
        System.out.println("->> Update book price for book id 4");
        newBook.setPrice(600);
        bookDao.update(4, newBook);
//
        spring = bookDao.findById(4);
        System.out.println("---findById: " + spring);

        // ---- delete book ----
        System.out.println("->> Delete book id 4");
        bookDao.deleteById(4);

        bookList = bookDao.findAll();
        for (Book book : bookList) {
            System.out.println(book);
        }
    }
}
