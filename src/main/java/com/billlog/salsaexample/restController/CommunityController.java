package com.billlog.salsaexample.restController;

import com.billlog.salsaexample.mapper.CommunityMapper;
import com.billlog.salsaexample.model.SalsaCommunity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class CommunityController {

    @Autowired
    private CommunityMapper communityMapper;

    // 모든 커뮤니티 글 목록 가져오기
    @GetMapping("/community/all")
    public List<SalsaCommunity> getAll() throws Exception{
        List<SalsaCommunity> list = communityMapper.getCommunityArticleAll();

        return list;
    }

    // community 게시글 등록
    @PostMapping("/community")
    public ResponseEntity<String> createCommunityArticle(SalsaCommunity salsaCommunity){

        if(salsaCommunity.getTitle().isEmpty()){
            return new ResponseEntity<String>("Err user msg : community need data is Empty!", HttpStatus.NO_CONTENT);
        }

        int result = communityMapper.createCommunityArticle(salsaCommunity);

        if(result == 1) {
            return new ResponseEntity<String>("success", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
        }
    }

    // 정보 idx를 통해 해당 정보 게시글 내용 변경
    @PutMapping("/community/{community_idx}")
    public ResponseEntity<String> modifyCommunityArticleByIdx(@PathVariable int community_idx, SalsaCommunity salsaCommunity){

        //유저 인덱스 번호(유저 고유정보)가 없을 경우 메시지와 함께 Bad Request를 리턴
        if(salsaCommunity.getCommunity_idx() == 0){
            return new ResponseEntity<String>("Err user msg : community_idx is Zero", HttpStatus.NO_CONTENT);
        }

        int result = communityMapper.modifyCommunityArticleByIdx(salsaCommunity);

        if(result == 1) {
            return new ResponseEntity<String>("success", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
        }
    }

    // community index로 특정 정보 게시글 가져오기.
    @GetMapping("/community/{community_idx}")
    public SalsaCommunity getCommunityByIdx(@PathVariable int community_idx) throws Exception{
        SalsaCommunity salsaCommunity = communityMapper.getCommunityByIdx(community_idx);
        return salsaCommunity;
    }

    // community index로 community 게시글 삭제(use_yn = 'N')
    @DeleteMapping("/community/{community_idx}")
    public ResponseEntity<String> deleteCommunityByIdx(@PathVariable int community_idx) throws Exception{

        if(community_idx == 0 ){ //값이 없을 경우
            return new ResponseEntity<String>("community_idx is Zero ", HttpStatus.BAD_REQUEST);
        }
        communityMapper.deleteCommunityArticleByIdx(community_idx);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

}

