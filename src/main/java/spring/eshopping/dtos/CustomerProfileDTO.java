package spring.eshopping.dtos;

public class CustomerProfileDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private String contact;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public String getContact() {
        return contact;
    }


}
