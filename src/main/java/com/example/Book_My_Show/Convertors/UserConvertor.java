package com.example.Book_My_Show.Convertors;

import com.example.Book_My_Show.Entity.UserEntity;
import com.example.Book_My_Show.EntryDto.UserEntryDto;

public class UserConvertor {
    public static UserEntity convertDtoTOEntity(UserEntryDto userEntryDto){
        UserEntity userEntity=  UserEntity.builder().age(userEntryDto.getAge()).address(userEntryDto.getAddress()).email(userEntryDto.getEmail()).name(userEntryDto.getName()).mobNo(userEntryDto.getMobNo()).build();
        return userEntity;
    }
}
