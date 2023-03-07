package com.example.Book_My_Show.Service;

import com.example.Book_My_Show.Convertors.UserConvertor;
import com.example.Book_My_Show.EntryDto.UserEntryDto;
import com.example.Book_My_Show.Repository.UserRepository;
import com.example.Book_My_Show.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public String addUser(UserEntryDto userEntryDto)throws Exception  {


            UserEntity userEntity = UserConvertor.convertDtoTOEntity(userEntryDto);
            userRepository.save(userEntity);
            return "User added successfully";
    }
    public String deleteUser(String mob){
        UserEntity userEntity = userRepository.deleteUser(mob);
        if(userEntity==null) return "User not found";
        userRepository.delete(userEntity);
        return "Deleted successfully";
    }
    public List<String> getAll() {
        return userRepository.getAll();
    }

    public String updateAddress(String mob, String address){
        UserEntity userEntity = userRepository.findMob(mob);
        userEntity.setAddress(address);
        userRepository.save(userEntity); //new address saved
        return "Your address has been updated";
    }

}
