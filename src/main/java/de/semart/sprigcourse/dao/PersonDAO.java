package de.semart.sprigcourse.dao;

import de.semart.sprigcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }




    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new BeanPropertyRowMapper<>(Person.class), id)
                .stream().findAny().orElse(null);
    }

    public void save(Person person){

        jdbcTemplate.update("INSERT INTO Person VALUES (name, age, email)( ?, ?, ?)", person.getName(), person.getAge(),
                person.getEmail());

    }

    public void update(int id, Person updatedPerson){
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?", updatedPerson.getName(),
                updatedPerson.getAge(), updatedPerson.getEmail(), id);
    }

    public void delete (int id){
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public void testBatchUpdate() {
        List<Person> people = create1000People();
        long before =System.currentTimeMillis();


        jdbcTemplate.batchUpdate("INSERT INTO Person VALUES (?, ?, ?, ?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setInt(1, people.get(i).getId());
                preparedStatement.setString(2, people.get(i).getName());
                preparedStatement.setInt(3, people.get(i).getAge());
                preparedStatement.setString(4, people.get(i).getEmail());
            }

            @Override
            public int getBatchSize() {
                return people.size();
            }
        });
        long after =System.currentTimeMillis();

        System.out.println("Time: " + (after-before));
    }

    public void testMultipleUpdate() {
        List<Person>personList = create1000People();
        long before =System.currentTimeMillis();

        for (Person person:personList){
            jdbcTemplate.update("INSERT INTO Person VALUES (?, ?, ?, ?)", person.getId(), person.getName(), person.getAge(),
                    person.getEmail());
        }
        long after =System.currentTimeMillis();

        System.out.println("Time: " + (after-before));
    }

    private List<Person> create1000People() {
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i<1000; i++){
            personList.add(new Person(i, "name"+i, 30,"test"+i+"@gmail.com"));
        }
        return personList;
    }
}
