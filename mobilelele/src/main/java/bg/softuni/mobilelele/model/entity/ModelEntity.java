package bg.softuni.mobilelele.model.entity;

import bg.softuni.mobilelele.model.entity.enums.CategoryEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "models")
public class ModelEntity extends BaseEntity {
	private String name;

	@Enumerated(EnumType.STRING)
	private CategoryEnum category;

	private String imageUrl;

	private int startYear;

	private Long endYear;
}
