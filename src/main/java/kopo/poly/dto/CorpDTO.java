package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class CorpDTO {
    @JsonProperty(value = "corp_code")
    String corpCode;
    @JsonProperty(value = "corp_name")
    String corpName;
    Long amount;

}
