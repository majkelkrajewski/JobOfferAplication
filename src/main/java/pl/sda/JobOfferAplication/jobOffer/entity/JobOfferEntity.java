package pl.sda.JobOfferAplication.jobOffer.entity;

import lombok.Getter;
import pl.sda.JobOfferAplication.user.entity.UserEntity;
import pl.sda.JobOfferAplication.user.model.UserOutput;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "joboffers")
@Getter
public class JobOfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Category category;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private UserEntity user;


    public JobOfferEntity(Category category, LocalDate endDate, UserEntity user) {
        this.category = category;
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now().plusWeeks(2);
        this.user = user;
    }
}
