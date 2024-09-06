package uno.fastcampus.testdata.dto.request;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uno.fastcampus.testdata.dto.TableSchemaDto;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of") // 정적 팩터리메서드
@Data
public class TableSchemaRequest {

    private String schemaName;
    private String userId;
    private List<SchemaFieldRequest> schemaFields;

    public TableSchemaDto toDto() {
        return TableSchemaDto.of(
            schemaName,
            userId,
            null,
            schemaFields.stream()
                    .map(SchemaFieldRequest::toDto)
                    .collect(Collectors.toUnmodifiableSet())
        );
    }
}
