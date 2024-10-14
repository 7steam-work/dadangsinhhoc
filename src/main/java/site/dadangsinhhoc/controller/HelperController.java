package site.dadangsinhhoc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.dadangsinhhoc.dto.SearchCriteriaDTO;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.services.helper.IHelperService;

@RestController
@RequestMapping("/api/helper")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class HelperController {

    private final IHelperService helperService;

    @PostMapping("/search")
    public ResponseObject search(@RequestBody SearchCriteriaDTO criteria) {
        return helperService.SearchLoai(criteria);
    }
}