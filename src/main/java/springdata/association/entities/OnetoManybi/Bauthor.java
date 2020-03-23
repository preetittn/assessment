package springdata.association.entities.OnetoManybi;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Bauthor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String authorName;
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private Set<Bbook> book;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Set<Bbook> getBook() {
        return book;
    }

    public void setBook(Set<Bbook> book) {
        this.book = book;
    }
}
