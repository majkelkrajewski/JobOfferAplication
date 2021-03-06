package pl.sda.JobOfferAplication.user.entity;

//import lombok.AccessLevel;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import pl.sda.JobOfferAplication.jobOffer.entity.JobOfferEntity;
import pl.sda.JobOfferAplication.user.model.UserOutput;
import javax.persistence.Table;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Getter
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;


    private String name;
    private String login;
    private LocalDate creationDate;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<JobOfferEntity> jobOffers;


    public UserEntity(String name, String login, LocalDate creationDate, String password) {
        this.name = name;
        this.login = login;
        this.creationDate = creationDate;
        this.password = password;
    }

    public UserEntity() {

    }

    public UserOutput toOutput() {
        return new UserOutput(id, name, login, creationDate);

    }

}
