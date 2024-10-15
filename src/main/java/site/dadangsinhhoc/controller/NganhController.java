package site.dadangsinhhoc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.NganhModel;
import site.dadangsinhhoc.services.nganh.INganhService;

@RestController
@RequestMapping("/api/nganh")
@CrossOrigin
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class NganhController {
    private final INganhService nganhService;

    @GetMapping("/getAllNganh")
    public ResponseObject getAllNganh() {
        return nganhService.getAllNganh();
    }

    @GetMapping("/getAllByLoai/{loai}")
    public ResponseObject getAllNganhByLoai(@PathVariable Boolean listNganh) {
        return nganhService.getAllNganhByLoai(listNganh);
    }

    @GetMapping("/getById/{id}")
    public ResponseObject getNganhById(@PathVariable Long id) {
        return nganhService.findById(id);
    }

    @GetMapping("/countAll")
    public ResponseObject countAllNganh() {
        return nganhService.countAllNganh();
    }

    @PostMapping("/addNew")
    public ResponseObject addNewNganh(@RequestBody NganhModel model) {
        return nganhService.saveNganh(model);
    }

    @PutMapping("/update/{id}")
    public ResponseObject updateNganh(@PathVariable Long id, @RequestBody NganhModel model) {
        model.setId(id);
        return nganhService.updateNganh(id, model);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseObject deleteNganh(@PathVariable Long id) {
        return nganhService.deleteByIdNganh(id);
    }

}
