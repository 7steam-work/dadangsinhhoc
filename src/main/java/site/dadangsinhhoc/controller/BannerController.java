package site.dadangsinhhoc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.BannerModel;
import site.dadangsinhhoc.services.banner.IBannerService;

@RestController
@RequestMapping("/api/banner")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@CrossOrigin
public class BannerController {
    private final IBannerService bannerService;

    @GetMapping("/getAllBanner")
    public ResponseObject getAllBanner() {
        return bannerService.getAllBanner();
    }

    @GetMapping("/countAllBanner")
    public ResponseObject countAllBanner() {
        return bannerService.countAllBanner();
    }

    @PostMapping("/addNewBanner")
    public ResponseObject addNewBanner(@RequestBody BannerModel bannerModel) {
        return bannerService.saveBanner(bannerModel);
    }

    @DeleteMapping("/deleteBanner/{id}")
    public ResponseObject deleteBanner(@PathVariable Long id) {
        return bannerService.deleteByIdBanner(id);
    }

}
