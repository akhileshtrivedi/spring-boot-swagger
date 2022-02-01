package com.springboot.swagger.model;

import lombok.*;
import org.jfairy.producer.person.Address;
import org.jfairy.producer.person.Person;
import org.joda.time.DateTime;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private Integer authorId;
    private Address address;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private Person.Sex sex;
    private String telephoneNumber;
    private DateTime dateOfBirth;
    private Integer age;
    private String companyEmail;
    private String nationalIdentityCardNumber;
    private String nationalIdentificationNumber;

    public Author(int authorId, Person p) {
        this.authorId = authorId;
        this.nationalIdentityCardNumber = p.nationalIdentificationNumber();
        this.address = p.getAddress();
        this.firstName = p.firstName();
        this.middleName = p.middleName();
        this.lastName = p.lastName();
        this.email = p.email();
        this.sex = p.sex();
        this.telephoneNumber = p.telephoneNumber();
        this.dateOfBirth = p.dateOfBirth();
        this.age = p.age();
        this.nationalIdentificationNumber = p.nationalIdentificationNumber();
        this.companyEmail = p.companyEmail();
    }

}
