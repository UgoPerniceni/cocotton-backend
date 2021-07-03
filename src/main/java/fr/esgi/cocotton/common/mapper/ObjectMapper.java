package fr.esgi.cocotton.common.mapper;

public interface ObjectMapper<T, S> {

    T toDomain(S jpaObject);

    S toEntity(T domainObject);
}
