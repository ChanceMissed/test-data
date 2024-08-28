package uno.fastcampus.testdata.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uno.fastcampus.testdata.domain.TableSchema;

public interface TableSchemaRepository extends JpaRepository<TableSchema, Long> {

    // 목록조회
    Page<TableSchema> findByUserId(String userId, Pageable pageable);

    // 특정 스키마 조회
    Optional<TableSchema> findByUserIdAndSchemaName(String userId, String schemaName);

    // 스키마 삭제
    void deleteByUserIdAndSchemaName(String userId, String schemaName);
}
