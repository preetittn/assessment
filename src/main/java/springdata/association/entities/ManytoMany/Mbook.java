package springdata.association.entities.ManytoMany;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Mbook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bookName;
    @ManyToMany(mappedBy = "books")
    Set<Mauthor> authors;
}
