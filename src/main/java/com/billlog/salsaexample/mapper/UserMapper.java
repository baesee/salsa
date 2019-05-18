package com.billlog.salsaexample.mapper;

import com.billlog.salsaexample.model.SalsaUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    //유저 아이디로 유저정보 가져오기
    @Select("Select * from salsa_user where user_idx = #{user_idx}")
    SalsaUser getUserById(@Param("user_idx") int user_idx);

    //유저 전체 목록 가져오기
    @Select("select * from salsa_user")
    List<SalsaUser> getAll();

    //유저 사용 여부 변경 ( 삭제 )
    @Delete("UPDATE salsa_user SET user_yn='N' WHERE user_idx = #{user_idx}")
    boolean deleteUserById(@Param("user_idx") int user_idx);

    //유저 등록
    @Insert("INSERT INTO salsa_user(`user_id`, `user_pwd`, `user_name`, `user_addr`, `user_phone`, `user_gender`, `user_birth`, `user_email`, `user_type`) " +
            "VALUES (#{user_id}, #{user_pwd}, #{user_name}, #{user_addr}, #{user_phone}, #{user_gender}, #{user_birth}, #{user_email}, #{user_type})")
    int createUser(SalsaUser user);

    //유저 정보 수정
    @Update("UPDATE salsa_user SET user_name = #{user_name} ,user_addr = #{user_addr} " +
            ",user_phone = #{user_phone} ,user_gender =#{user_gender} ,user_email = #{user_email} " +
            "WHERE user_idx = #{user_idx}")
    int modifyUserById(SalsaUser user);
}
