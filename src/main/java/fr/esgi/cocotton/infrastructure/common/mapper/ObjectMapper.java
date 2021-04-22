package fr.esgi.cocotton.infrastructure.common.mapper;

public interface ObjectMapper<T, S> {

    T toDomain(S jpaObject);

    S toEntity(T domainObject);
}
