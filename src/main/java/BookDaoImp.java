import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookDaoImp implements BookDao {

    private JdbcTemplate jdbcTemplate;


    public BookDaoImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Book book) {
        String query = "INSERT INTO book (id, name, price) VALUES (?, ?, ?);";

//        String query = "insert into book (id,name,price) Select data.getId() Where not exists(select * from book where id= data.getId()";

        Object[] data = new Object[]
                { book.getId(), book.getName(), book.getPrice() };
        jdbcTemplate.update(query, data);

//        if not exists(select * from book where id= book.getId())
//        Begin
//        INSERT INTO book (id, name, price) VALUES (?, ?, ?)
//        End+
    }

    public void update(int id, Book book) {
        String query = "UPDATE book SET name = '" + book.getName() + "',price = '" + book.getPrice() + "' WHERE id = "+ id;
        jdbcTemplate.update(query);
    }

    public void deleteById(int id) {
        String query = "DELETE FROM book WHERE id = "+ id;
        jdbcTemplate.update(query);
    }

    public Book findById(int id) {
        String query = "SELECT * FROM book WHERE id = " + id;
        Book book = jdbcTemplate.queryForObject(query, new BookRowMapper());
        return book;
    }

    public List<Book> findAll() {
        String query = "SELECT * FROM book";
        List<Book> books = jdbcTemplate.query(query, new BookRowMapper());
        return books;
    }

    class BookRowMapper implements RowMapper<Book> {
        public Book mapRow(ResultSet rs, int i) throws SQLException {
            Book book = new Book(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"));
            return book;
        }

    }
}
