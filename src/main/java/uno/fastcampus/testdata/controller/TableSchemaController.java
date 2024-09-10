package uno.fastcampus.testdata.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uno.fastcampus.testdata.domain.constant.ExportFileType;
import uno.fastcampus.testdata.domain.constant.MockDataType;
import uno.fastcampus.testdata.dto.request.TableSchemaExportRequest;
import uno.fastcampus.testdata.dto.request.TableSchemaRequest;
import uno.fastcampus.testdata.dto.response.SchemaFieldResponse;
import uno.fastcampus.testdata.dto.response.TableSchemaResponse;

@RequiredArgsConstructor
@Controller
public class TableSchemaController {

    private final ObjectMapper mapper;

    @GetMapping("/table-schema")
     public String tableSchema(Model model){
        var tableSchema = defaultTableSchema();

        model.addAttribute("tableSchema", tableSchema);
        model.addAttribute("mockDataTypes", MockDataType.toObjects());
        model.addAttribute("fileTypes", Arrays.stream(ExportFileType.values()).toList());

        return "table-schema";
    }

    @PostMapping("/table-schema")
    public String createOrUpdateTableSchema(
        TableSchemaRequest tableSchemaRequest,
        RedirectAttributes redirectAttrs // 리다이렉트하면 입력한데이터가 그대로가게
    ){
        redirectAttrs.addFlashAttribute("tableSchemaRequest", tableSchemaRequest); // 리다이렉트후 쿼리스트링으로 데이터 노출안되게
        return "redirect:/table-schema";
    }

    @GetMapping("/table-schema/my-schemas")
    public String mySchemas(){
        return "my-schemas";
    }

    @PostMapping("/table-schema/my-schemas/{schemaName}")
    public  String deleteMySchema(@PathVariable String schemaName){
        return "redirect:/table-schema/my-schemas";
    }


    @GetMapping("/table-schema/export")
    public ResponseEntity<String> exportTableSchema(TableSchemaExportRequest tableSchemaExportRequest) {

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=table-schema.txt")
            .body(json(tableSchemaExportRequest)); // TODO: 나중에 데이터 바꿔야 함
    }


    private TableSchemaResponse defaultTableSchema() {
        return new TableSchemaResponse(
            "schema_name",
            "Uno",
            List.of(
                new SchemaFieldResponse("fieldName1", MockDataType.STRING, 1, 0, null, null),
                new SchemaFieldResponse("fieldName2", MockDataType.NUMBER, 2, 10, null, null),
                new SchemaFieldResponse("fieldName3", MockDataType.NAME, 3, 20, null, null)
            )
        );
    }

    private String json(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException jpe) {
            throw new RuntimeException(jpe);
        }
    }
}
