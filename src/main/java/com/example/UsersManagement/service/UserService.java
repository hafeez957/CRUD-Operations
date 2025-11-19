package com.example.UsersManagement.service;

import com.example.UsersManagement.model.User;
import com.example.UsersManagement.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> findAll(){
        return usersRepository.findAll();
    }

    public void create(User user){
        usersRepository.createUser(user);
    }
    public void delete(Long id){
        usersRepository.deleteUser(id);
    }

    public User update(Long id) {
        return usersRepository.update(id);

    }

    public void updateUser(User user) {
        usersRepository.updateUser(user);
    }
}
