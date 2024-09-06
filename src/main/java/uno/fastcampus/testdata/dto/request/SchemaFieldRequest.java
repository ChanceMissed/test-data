package uno.fastcampus.testdata.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uno.fastcampus.testdata.domain.constant.MockDataType;
import uno.fastcampus.testdata.dto.SchemaFieldDto;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Data
public class SchemaFieldRequest{

    private String fieldName;
    private MockDataType mockDataType;
    private Integer fieldOrder;
    private Integer blankPercent;
    private String typeOptionJson;
    private String forceValue;

    public SchemaFieldDto toDto() {
        return SchemaFieldDto.of(
            this.fieldName,
            this.mockDataType,
            this.fieldOrder,
            this.blankPercent,
            this.typeOptionJson,
            this.forceValue
        );
    }

}
