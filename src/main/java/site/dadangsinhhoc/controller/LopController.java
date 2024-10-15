package site.dadangsinhhoc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.LopModel;
import site.dadangsinhhoc.services.lop.ILopService;

@RestController
@RequestMapping("/api/lop")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@CrossOrigin
public class LopController {
    private final ILopService lopService;

    @GetMapping("/getAllLop")
    public ResponseObject getAllLop() {
        return lopService.getAllLop();
    }

    @GetMapping("/getAllLopByLoai/{loai}")
    public ResponseObject getAllLopByLoai(@PathVariable Boolean loai) {
        return lopService.getAllLopByLoai(loai);
    }

    @GetMapping("/getLopById/{id}")
    public ResponseObject getLopById(@PathVariable Long id) {
        return lopService.findLopById(id);
    }

    @GetMapping("/countAllLop")
    public ResponseObject countAllLop() {
        return lopService.countAllLop();
    }

    @PostMapping("/addNewLop")
    public ResponseObject addNewLop(@RequestBody LopModel model) {
        return lopService.saveLop(model);
    }

    @PutMapping("/updateLop/{id}")
    public ResponseObject updateLop(@PathVariable Long id, @RequestBody LopModel model) {
        model.setId(id);
        return lopService.updateLop(id, model);
    }

    @DeleteMapping("/deleteLop/{id}")
    public ResponseObject deleteLop(@PathVariable Long id) {
        return lopService.deleteByIdLop(id);
    }

}
