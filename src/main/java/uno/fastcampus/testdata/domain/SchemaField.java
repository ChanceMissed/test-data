package uno.fastcampus.testdata.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uno.fastcampus.testdata.domain.constant.MockDataType;

@Getter
@Setter
@ToString
public class SchemaField {

    private String fieldName;
    private MockDataType mockDataType;
    private Integer fieldOrder;
    private Integer blankPercent;
    private String typeOptionJson; // {min: 1, max: 5}
    private String forceValue;

}
