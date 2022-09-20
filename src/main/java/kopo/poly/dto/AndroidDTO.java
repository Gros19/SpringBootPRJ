package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AndroidDTO {
    @JsonProperty("mem_no")
    private String mem_mo;
    @JsonProperty("mem_id")
    private String mem_id;
    @JsonProperty("mem_pw")
    private String mem_pw;
    @JsonProperty("mem_name")
    private String mem_name;
}
