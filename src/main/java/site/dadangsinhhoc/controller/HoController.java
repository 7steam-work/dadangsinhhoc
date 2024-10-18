package site.dadangsinhhoc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.HoModel;
import site.dadangsinhhoc.services.ho.IHoService;

@RestController
@RequestMapping("/api/Ho")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@CrossOrigin
public class HoController {
    private final IHoService IHoService;

    @GetMapping("/getAllHo")
    public ResponseObject getAllHo() {
        return IHoService.getAllHo();
    }

    @GetMapping("/getAllHoByLoai/{loai}")
    public ResponseObject getAllHoByLoai(@PathVariable Boolean loai) {
        return IHoService.getAllHoByLoai(loai);
    }

    @GetMapping("/searchHo")
    public ResponseObject searchByNameOrNameLatinh(
            @RequestParam("keyword") String keyword,
            @RequestParam(value = "loai", required = false) Boolean loai,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return IHoService.searchByNameOrNameLatinh(keyword, loai, page, size);
    }

    @GetMapping("/checkDuplicateNameLatinh")
    public ResponseObject checkDuplicateNameLatinh(
            @RequestParam("nameLatinh") String nameLatinh,
            @RequestParam("isAddingNew") boolean isAddingNew,
            @RequestParam(value = "oldNameLatinh", required = false) String oldNameLatinh) {
        boolean isDuplicate = IHoService.checkDuplicateNameLatinh(nameLatinh, isAddingNew, oldNameLatinh);
        return ResponseObject.success(!isDuplicate); // Trả về true nếu không trùng, false nếu trùng
    }

        @GetMapping("/getHoById/{id}")
        public ResponseObject getHoById (@PathVariable Long id){
            return IHoService.findById(id);
        }

        @GetMapping("/countAllHo")
        public ResponseObject countAllHo () {
            return IHoService.countAllHo();
        }

        @PostMapping("/addNewHo")
        public ResponseObject addNewHo (@RequestBody HoModel model){
            return IHoService.saveHo(model);
        }

        @PutMapping("/updateHo/{id}")
        public ResponseObject updateHo (@PathVariable Long id, @RequestBody HoModel model){
            model.setId(id);
            return IHoService.updateHo(id, model);
        }

        @DeleteMapping("/deleteHo/{id}")
        public ResponseObject deleteHo (@PathVariable Long id){
            return IHoService.deleteByIdHo(id);
        }

    }
