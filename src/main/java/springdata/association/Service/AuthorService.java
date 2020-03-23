package springdata.association.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import springdata.association.Repository.AuthorRepository;
import springdata.association.entities.Address;
import springdata.association.entities.Author;
import springdata.association.entities.Subject;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public void addAuthor(){
        Author author = new Author();
        author.setFirstName("Preeti");
        author.setLastName("Upadhyay");
        author.setAddress(new Address(11,"mehrauli","New Delhi"));
        Subject s1 = new Subject();
        s1.setSubjectName("Science");
        Subject s2 = new Subject();
        s2.setSubjectName("Maths");
        Subject s3 = new Subject();
        s3.setSubjectName("Hindi");

        author.addSubject(s1);
        author.addSubject(s2);
        author.addSubject(s3);

        authorRepository.save(author);
    }
}