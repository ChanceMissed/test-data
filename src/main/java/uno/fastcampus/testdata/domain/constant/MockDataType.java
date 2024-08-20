package uno.fastcampus.testdata.domain.constant;

import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MockDataType {
    STRING(Set.of("minLength", "maxLength", "pattern"), null),
    NUMBER(Set.of("min", "max", "decimals"), null),
    BOOLEAN(Set.of(), null),
    DATETIME(Set.of("from", "to"), null),
    ENUM(Set.of("elements"), null),

    SENTENCE(Set.of("minSentences", "maxSentences"), STRING),
    PARAGRAPH(Set.of("minParagraphs", "maxParagraph"), STRING),
    UUID(Set.of(), STRING),
    EMAIL(Set.of("from", "to"), STRING),
    CAR(Set.of("elements"), STRING),
    ROW_NUMBER(Set.of("start", "stem"), STRING),
    NAME(Set.of(), STRING);

    private final Set<String> requiredOptions;
    private final MockDataType baseType;

    public boolean isBaseType() {
        return baseType == null;
    }

    public MockDataTypeObject toObject(){
        return new MockDataTypeObject(
            this.name(),
            this.requiredOptions,
            this.baseType == null ? null : this.baseType.name()
        );
    }
    public record MockDataTypeObject(String name, Set<String> requiredOptions, String baseType) {
    }
}
