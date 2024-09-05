package uno.fastcampus.testdata.dto.response;

import java.util.List;
import uno.fastcampus.testdata.dto.TableSchemaDto;

public record TableSchemaResponse(
    String schemaName,
    String userId,
    List<SchemaFieldResponse> schemaFields
) {

    public static TableSchemaResponse fromDto(TableSchemaDto dto) {
        return new TableSchemaResponse(
            dto.schemaName(),
            dto.userId(),
            dto.schemaFields().stream().map(SchemaFieldResponse::fromDto).toList()
        );
    }

}
