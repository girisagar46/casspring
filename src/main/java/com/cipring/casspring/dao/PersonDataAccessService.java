package com.cipring.casspring.dao;

import com.cipring.casspring.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public int insertPerson(UUID id, Person person) {
    return 0;
  }

  @Override
  public int insertPerson(Person person) {
    final String insert_sql = "INSERT INTO person (id, NAME) VALUES (?, ?);";
    System.out.println(person.getName() + person.getId());
    return jdbcTemplate.update(insert_sql, UUID.randomUUID(), person.getName());
  }

  @Override
  public List<Person> selectAllPeople() {
    final String select_all_sql = "SELECT id, name FROM person;";
    return jdbcTemplate.query(
        select_all_sql,
        (resultSet, i) -> {
          UUID id = UUID.fromString(resultSet.getString("id"));
          String name = resultSet.getString("name");
          return new Person(id, name);
        });
  }

  @Override
  public Optional<Person> selectPersonById(UUID id) {
    final String select_one_sql = "SELECT id, name FROM person WHERE id=?;";
    Person person =
        jdbcTemplate.queryForObject(
            select_one_sql,
            new Object[] {id},
            ((resultSet, i) -> {
              UUID personId = UUID.fromString(resultSet.getString("id"));
              String name = resultSet.getString("name");
              return new Person(personId, name);
            }));
    return Optional.ofNullable(person);
  }

  @Override
  public int deletePersonById(UUID id) {
    final String sql = "DELETE FROM person WHERE id=?;";
    Object[] args = new Object[] {id};
    return jdbcTemplate.update(sql, args);
  }

  @Override
  public int updatePersonById(UUID _id, Person newPerson) {
    final String sql = "UPDATE person SET name=? WHERE id=?;";
    Optional<Person> personToUpdate = selectPersonById(_id);
    if (personToUpdate.isPresent()) {
      personToUpdate.map(
          person -> {
            String name = newPerson.getName();
            UUID id = person.getId();
            Object[] args = new Object[] {name, id};
            return jdbcTemplate.update(sql, args);
          });
    }
    return 0;
  }
}
