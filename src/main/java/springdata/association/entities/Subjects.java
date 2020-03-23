package springdata.association.entities;

import springdata.association.entities.Author;

import javax.persistence.*;

@Entity
public class Subjects {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    private String subjectName;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Subjects() {
    }

    public Subjects(String subjectName, Author author) {
        this.subjectName = subjectName;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Subjects{" +
                "id=" + id +
                ", subjectName='" + subjectName + '\'' +
                ", author=" + author +
                '}';
    }
}