package springdata.association.entities.ManytoMany;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Mauthor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String authorName;
    @ManyToMany
    @JoinTable(name = "author_book",joinColumns = @JoinColumn(name = "author_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "book_id",referencedColumnName = "id"))
    Set<Mbook> books;
}
