package com.example.Book_My_Show.Repository;

import com.example.Book_My_Show.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    @Query(value = "select * from users where mob_no=:mob", nativeQuery = true)
    UserEntity deleteUser(String mob);
    @Query(value = "select name from users", nativeQuery = true)
    List<String> getAll();

}
