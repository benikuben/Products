package ru.neoflex.products.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.format.annotation.DateTimeFormat;
import ru.neoflex.products.dtos.ProductType;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Audited
@AuditTable("product_aud")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "product_type")
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "tariff", referencedColumnName = "id")
    @NotAudited
    private Tariff tariff;
    @Column(name = "author")
    private UUID author;
    @Column(name = "version")
//    @Version
    private Integer version;

    public void updateVersion() {
        version++;
    }
}
