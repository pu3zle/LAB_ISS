package models.validators;

public interface Validator<T> {
    void validate(T entity) throws models.validators.ValidationException;
}