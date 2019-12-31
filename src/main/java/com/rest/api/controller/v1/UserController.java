package com.rest.api.controller.v1;

import com.rest.api.advice.exception.CUserNotFoundException;
import com.rest.api.entity.User;
import com.rest.api.model.response.CommonResult;
import com.rest.api.model.response.ListResult;
import com.rest.api.model.response.SingleResult;
import com.rest.api.repo.UserJpaRepo;
import com.rest.api.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"1. User"})
@RequiredArgsConstructor
@RestController // 결과 값을 JSON으로 출력
@RequestMapping(value = "/v1")
public class UserController {
    private final UserJpaRepo userJpaRepo;
    private final ResponseService responseService;

    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다.")
    @GetMapping(value = "/users")
    public ListResult<User> findAllUser() {
        return responseService.getListResult(userJpaRepo.findAll());
    }

    @ApiOperation(value = "회원 단건 조회", notes = "userId로 조회한다.")
    @GetMapping(value = "/user/{msrl}")
    public SingleResult<User> findUserById(
            @ApiParam(value = "회원 번호", required = true) @PathVariable Long msrl,
            @ApiParam(value = "언어", defaultValue = "ko") @RequestParam String lang) {

        return responseService.getSingleResult(userJpaRepo.findById(msrl).orElseThrow(CUserNotFoundException::new));
    }

    @ApiOperation(value = "회원 추가", notes = "회원을 추가한다.")
    @PostMapping(value = "/user")
    public SingleResult<User> save(
            @ApiParam(value = "회원 아이디", required = true) @RequestParam String uid,
            @ApiParam(value = "회원 이름", required = true) @RequestParam String name) {

        User user = User.builder()
                        .uid(uid)
                        .name(name)
                        .build();

        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    @ApiOperation(value = "회원 수정", notes = "회원 정보를 수정한다.")
    @PutMapping(value = "/user")
    public SingleResult<User> modify(
            @ApiParam(value = "회원 번호", required = true) @RequestParam Long msrl,
            @ApiParam(value = "회원 아이디", required = true) @RequestParam String uid,
            @ApiParam(value = "회원 이름", required = true) @RequestParam String name) {

        User user = User.builder()
                        .msrl(msrl)
                        .uid(uid)
                        .name(name)
                        .build();

        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    @ApiOperation(value = "회원 삭제", notes = "회원 번호로 회원 정보를 삭제한다.")
    @DeleteMapping(value = "/user/{msrl}")
    public CommonResult delete(
            @ApiParam(value = "회원 번호", required = true) @PathVariable Long msrl) {

        userJpaRepo.deleteById(msrl);

        // 성공 정보만 필요한 경우에는 getSuccessResult() 호출
        return responseService.getSuccessResult();
    }
}
