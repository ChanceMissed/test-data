package uno.fastcampus.testdata.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 단위 테이블 스키마 정보.
 * 식별자 ({@link #userId})로 특정할 수 있는 회원이 소유한다.
 */
@Getter
@ToString(callSuper = true)
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"userId", "schemaName"})
    },
    indexes = {
        @Index(columnList = "createdAt"),
        @Index(columnList = "modifiedAt")
    }
)
@Entity
public class TableSchema extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(nullable = false) private String schemaName;
    @Setter @Column(nullable = false) private String userId;

    @Setter private LocalDateTime exportedAt; // 출력이 되었는지?

    @ToString.Exclude
    @OrderBy("fieldOrder ASC")
    @OneToMany(mappedBy = "tableSchema", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<SchemaField> schemaFields = new LinkedHashSet<>();

    protected TableSchema(){}

    public TableSchema(String schemaName, String userId) {
        this.schemaName = schemaName;
        this.userId = userId;
        this.exportedAt = null;
    }

    public static TableSchema of(String schemaName, String userId) {
        return new TableSchema(schemaName, userId);
    }

    public void markExported(){
        exportedAt = LocalDateTime.now();
    }

    public boolean isExported(){
        return exportedAt != null;
    }

    public void addSchemaField(SchemaField schemaField) {
        schemaField.setTableSchema(this);
        schemaFields.add(schemaField);
    }

    public void addSchemaFields(Collection<SchemaField> schemaFields) {
        schemaFields.forEach(this::addSchemaField);
    }

    public void clearSchemaFields(){
        schemaFields.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TableSchema that)) {
            return false;
        }
        if (that.getId() == null) {
            return Objects.equals(getSchemaName(), that.getSchemaName()) &&
                Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getExportedAt(), that.getExportedAt()) &&
                Objects.equals(getSchemaFields(), that.getSchemaFields());
        }
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        if (getId() == null) {
            return Objects.hash(getSchemaName(), getUserId(), getExportedAt(),
                getSchemaFields());
        }
        return Objects.hash(getId());
    }
}
