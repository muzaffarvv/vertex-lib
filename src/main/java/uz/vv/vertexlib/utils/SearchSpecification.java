package uz.vv.vertexlib.utils;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;

public class SearchSpecification {
    public static <T> Specification<T> globalStringSearch(String searchText, List<String> fields) {
        return (root, query, cb) -> {
            if (searchText == null || searchText.isBlank()) return null;

            String pattern = "%" + searchText.toLowerCase() + "%";

            var predicates = fields.stream()
                    .map(field -> {
                        Expression<String> expression;

                        if (field.contains(".")) {
                            Path<?> tempPath = root;
                            for (String part : field.split("\\.")) {
                                tempPath = tempPath.get(part);
                            }
                            expression = tempPath.as(String.class);
                        } else {
                            expression = root.get(field).as(String.class);
                        }

                        return cb.like(cb.lower(expression), pattern);
                    })
                    .toArray(Predicate[]::new);

            return cb.or(predicates);
        };
    }
}