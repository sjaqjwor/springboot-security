package com.example.demo.mappers;

import com.example.demo.dtos.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    UserDto getUser(String s);
}
