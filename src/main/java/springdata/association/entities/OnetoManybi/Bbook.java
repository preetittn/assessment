package springdata.association.entities.OnetoManybi;

import javax.persistence.*;

@Entity
public class Bbook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String bookName;
    @ManyToOne
    @JoinColumn(name = "bauthor_id",referencedColumnName = "id")
    private Bauthor author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Bauthor getAuthor() {
        return author;
    }

    public void setAuthor(Bauthor author) {
        this.author = author;
    }
}
