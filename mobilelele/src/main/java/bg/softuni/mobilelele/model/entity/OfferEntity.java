package bg.softuni.mobilelele.model.entity;

import bg.softuni.mobilelele.model.entity.enums.EngineEnum;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "offers")
public class OfferEntity {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Type(type = "uuid-char")
	private UUID id;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EngineEnum engine;

	private String imageUrl;

	private int mileage;

	private BigDecimal price;


	public UUID getId() {
		return id;
	}

	public OfferEntity setId(UUID id) {
		this.id = id;
		return this;
	}
}
