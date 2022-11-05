﻿package bg.softuni.mobilelele.repositories;

import bg.softuni.mobilelele.model.dto.offer.SearchOfferDTO;
import bg.softuni.mobilelele.model.entity.OfferEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class OfferSpecification implements Specification<OfferEntity> {
    private final SearchOfferDTO searchOfferDTO;

    public OfferSpecification(SearchOfferDTO searchOfferDTO) {
        this.searchOfferDTO = searchOfferDTO;
    }

    @Override
    public Predicate toPredicate(Root<OfferEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        var predicate = criteriaBuilder.conjunction();

        if (searchOfferDTO.getModel() != null && !searchOfferDTO.getModel().isEmpty()) {
            criteriaBuilder.and(criteriaBuilder.equal(root.join("model").get("name"), searchOfferDTO.getModel()));
        }

        if (searchOfferDTO.getMinPrice() != null) {
            predicate.getExpressions().add(
                    criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), searchOfferDTO.getMinPrice())));
        }

        if (searchOfferDTO.getMaxPrice() != null) {
            predicate.getExpressions().add(
                    criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("price"), searchOfferDTO.getMaxPrice()))
            );
        }

        return predicate;
    }
}
