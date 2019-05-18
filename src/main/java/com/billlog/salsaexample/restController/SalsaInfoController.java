package com.billlog.salsaexample.restController;

import com.billlog.salsaexample.mapper.CommunityMapper;
import com.billlog.salsaexample.mapper.InfoMapper;
import com.billlog.salsaexample.model.SalsaCommunity;
import com.billlog.salsaexample.model.SalsaInfo;
import com.billlog.salsaexample.model.SalsaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class SalsaInfoController {

    @Autowired
    private InfoMapper infoMapper;

    // 모든 커뮤니티 글 목록 가져오기
    @GetMapping("/info/all")
    public List<SalsaInfo> getAll() throws Exception{
        List<SalsaInfo> list = infoMapper.getInfoArticleAll();

        return list;
    }

    // info index로 특정 정보 게시글 가져오기.
    @GetMapping("/info/{info_idx}")
    public SalsaInfo getInfoArticleByIdx(@PathVariable int info_idx) throws Exception{
        SalsaInfo salsaInfo = infoMapper.getInfoArticleByIdx(info_idx);
        return salsaInfo;
    }

    // info 정보성 게시글 등록
    @PostMapping("/info")
    public ResponseEntity<String> createInfoArticle(SalsaInfo salsaInfo){

        if(salsaInfo.getTitle().isEmpty()){
            return new ResponseEntity<String>("Err user msg : Info need data is Empty!", HttpStatus.NO_CONTENT);
        }

        int result = infoMapper.createInfoArticle(salsaInfo);

        if(result == 1) {
            return new ResponseEntity<String>("success", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
        }
    }

    // 정보 idx를 통해 해당 정보 게시글 내용 변경
    @PutMapping("/info/{info_idx}")
    public ResponseEntity<String> modifyInfoArticleByIdx(@PathVariable int info_idx, SalsaInfo salsaInfo){

        //유저 인덱스 번호(유저 고유정보)가 없을 경우 메시지와 함께 Bad Request를 리턴
        if(salsaInfo.getInfo_idx() == 0){
            return new ResponseEntity<String>("Err user msg : Info_idx is Zero", HttpStatus.NO_CONTENT);
        }

        int result = infoMapper.modifyInfoArticleByIdx(salsaInfo);

        if(result == 1) {
            return new ResponseEntity<String>("success", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
        }
    }

    // info index로 info 게시글 삭제(use_yn = 'N')
    @DeleteMapping("/info/{info_idx}")
    public ResponseEntity<String>  deleteSalsaUserByIdx(@PathVariable int info_idx) throws Exception{

        if(info_idx == 0 ){ //값이 없을 경우
            return new ResponseEntity<String>("info_idx is Zero ", HttpStatus.BAD_REQUEST);
        }
        infoMapper.deleteInfoArticleByIdx(info_idx);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }
}
