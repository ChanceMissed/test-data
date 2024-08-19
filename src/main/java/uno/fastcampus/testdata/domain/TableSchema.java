package uno.fastcampus.testdata.domain;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TableSchema {

    private String schemaName;
    private String userId;
    private LocalDateTime exportedAt; // 출력이 되었는지?


}
