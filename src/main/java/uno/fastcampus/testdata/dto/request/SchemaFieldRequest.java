package uno.fastcampus.testdata.dto.request;

import uno.fastcampus.testdata.domain.constant.MockDataType;
import uno.fastcampus.testdata.dto.SchemaFieldDto;

public record SchemaFieldRequest(
    String fieldName,
    MockDataType mockDataType,
    Integer fieldOrder,
    Integer blankPercent,
    String typeOptionJson,
    String forceValue
) {

    public SchemaFieldDto toDto(){
        return SchemaFieldDto.of(
            this.fieldName(),
            this.mockDataType(),
            this.fieldOrder(),
            this.blankPercent(),
            this.typeOptionJson(),
            this.forceValue()
        );
    }

}
