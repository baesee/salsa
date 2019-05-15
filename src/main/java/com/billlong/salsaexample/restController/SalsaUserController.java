package com.billlong.salsaexample.restController;

import com.billlong.salsaexample.mapper.UserMapper;
import com.billlong.salsaexample.model.SalsaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class SalsaUserController {

    @Autowired
    private UserMapper userMapper;

    public SalsaUserController(UserMapper userMapper) { //이에러는 좀.. 그냥 툴 에러인거 같음. 그냥 놔둬도 댐
        this.userMapper = userMapper;
    }

    // 유저 index로 특정 유저 정보 가져오기.
    @GetMapping("/user/{user_idx}")
    public SalsaUser getSalsaUserById(@PathVariable int user_idx) throws Exception{
        SalsaUser salsaUser = userMapper.getUserById(user_idx);
        return salsaUser;
    }

    // 모든 유저 정보가져오기.
    @GetMapping("/user/all")
    public List<SalsaUser> getAll() throws Exception{
        List<SalsaUser> list = userMapper.getAll();
        return list;
    }

    // 유저 index로 유저 삭제(user_yn = 'N')
    @DeleteMapping("/user/{user_idx}")
    public ResponseEntity<String>  deleteSalsaUserById(@PathVariable int user_idx) throws Exception{

        if(user_idx == 0 ){ //값이 없을 경우
            return new ResponseEntity<String>("user_idx no value ", HttpStatus.BAD_REQUEST);
        }
        userMapper.deleteUserById(user_idx);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    // 유저 회원가입
    @PostMapping("/user")
    public ResponseEntity<String> createUser(SalsaUser user){

        if(user.getUser_id().isEmpty()){
            return new ResponseEntity<String>("User ID is Empty!", HttpStatus.NO_CONTENT);
        }

        int result = userMapper.createUser(user);

        if(result == 1) {
            return new ResponseEntity<String>("success", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
        }
    }

    // 유저 개인 정보 변경
    @PutMapping("/user/{user_idx}")
    public ResponseEntity<String> modifyUserById(@PathVariable int user_idx, SalsaUser user){

        //유저 인덱스 번호(유저 고유정보)가 없을 경우 메시지와 함께 Bad Request를 리턴
        if(user.getUser_idx() == 0){
            return new ResponseEntity<String>("User IDX is Empty!", HttpStatus.NO_CONTENT);
        }

        int result = userMapper.modifyUserById(user);

        if(result == 1) {
            return new ResponseEntity<String>("success", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
        }
    }
}
