package uno.fastcampus.testdata.domain.constant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uno.fastcampus.testdata.domain.constant.MockDataType.MockDataTypeObject;

@DisplayName("[Domain] 테스트 데이터 자료형 테스트")
class MockDataTypeTest {

    @DisplayName("자료형이 주어지면, 해당원소의 이름을 리턴한다.")
    @Test
    void givenMockDataType_whenReading_theReturnsEnumElementName(){
        //given
        MockDataType mockDataType = MockDataType.STRING;

        //when
        //-- 직렬화 Jackson 사용해서  -> 변경
        String elementName = mockDataType.toString();

        //then
        System.out.println("elementName == "+ elementName);
        assertThat(elementName).isEqualTo(MockDataType.STRING.name());
    }

    @DisplayName("자료형의")
    @Test
    void givenMockDataType_whenReading_thenReturnsEnumElementObject(){
        // given
        MockDataType mockDataType = MockDataType.STRING;

        // when
        MockDataTypeObject result = MockDataType.STRING.toObject();

        System.out.println(result.toString());
        // then
        // JSON formatter - result
        assertThat(result.toString())
            .contains("name", "requiredOptions", "baseType");
    }
}