package site.dadangsinhhoc.dto;

import lombok.Data;

@Data
public class SearchCriteriaDTO {
    private Long nganhId;
    private Long lopId;
    private Long boId;
    private Long hoId;
    private String mucDoBaoTonIUCN;
    private String mucDoBaoTonSDVN;
    private String mucDoBaoTonNDCP;
    private String mucDoBaoTonND64CP;
    private Boolean RPH;
    private String tenTiengViet;
    private String tenKhoaHoc;
}