package com.example.UsersManagement.repository;

import com.example.UsersManagement.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UsersRepository {

    private final JdbcTemplate jdbcTemplate;


    public UsersRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // read
    public List<User> findAll(){
        return jdbcTemplate.query("select * from tabl_user",userRowMapper);

    }

    private RowMapper<User> userRowMapper=new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
             User user=new User();
             user.setId(rs.getInt("id"));
             user.setName(rs.getString("name"));
             user.setEmail(rs.getString("email"));
             return user;
        }

    };

    // insert
    public void createUser(User user){
        String sql="insert into tabl_user(name,email) values (?,?)";
        jdbcTemplate.update(sql,user.getName(),user.getEmail());
    }

    // delete

    public void deleteUser(Long id){
        String sql="delete from tabl_user where id=?";
        jdbcTemplate.update(sql,id);
    }

    public User update(Long id) {
        String sql="select * from tabl_user where id=?";
        return jdbcTemplate.queryForObject(sql,userRowMapper,id);
    }

    public void updateUser(User user) {
        String sql="update tabl_user set name=? , email=? where id=?";
        jdbcTemplate.update(sql,user.getName(),user.getEmail(),user.getId());
    }
}
