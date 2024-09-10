package uno.fastcampus.testdata.dto.request;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uno.fastcampus.testdata.domain.constant.ExportFileType;
import uno.fastcampus.testdata.dto.TableSchemaDto;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of") // 정적 팩터리메서드
@Data
public class TableSchemaExportRequest {

    private String schemaName;
    private Integer rowCount; // 몇줄 출력할거냐
    private ExportFileType fileType;
    private List<SchemaFieldRequest> schemaFields;

    public TableSchemaDto toDto(String userId) {
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
