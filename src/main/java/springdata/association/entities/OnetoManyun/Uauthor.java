package springdata.association.entities.OnetoManyun;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Uauthor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String authorName;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "uauthor_id")
    private Set<Ubook> bookUnis;

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

    public Set<Ubook> getBookUnis() {
        return bookUnis;
    }

    public void setBookUnis(Set<Ubook> bookUnis) {
        this.bookUnis = bookUnis;
    }
}
