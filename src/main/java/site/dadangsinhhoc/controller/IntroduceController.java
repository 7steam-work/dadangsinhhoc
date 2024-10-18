package site.dadangsinhhoc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.IntroduceModel;
import site.dadangsinhhoc.services.introduce.IIntroduceService;

@RestController
@RequestMapping("/api/introduce")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
public class IntroduceController {
    private final IIntroduceService introduceService;

    @GetMapping("/getAllIntroduce")
    public ResponseObject getAllIntroduce() {
        return introduceService.getAllIntroduce();
    }

    @GetMapping("/countAllIntroduce")
    public ResponseObject countAllIntroduce() {
        return introduceService.countAllIntroduce();
    }

    @PutMapping("/updateIntroduce/{id}")
    public ResponseObject updateIntroduce(@PathVariable Long id, @RequestBody IntroduceModel introduceModel) {
        introduceModel.setId(id);
        return introduceService.updateIntroduce(id, introduceModel);
    }

}
