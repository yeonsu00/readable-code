package cleancode.studycafe.tobe.model.pass;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudyCafePassTypeTest {

    @DisplayName("1인 고정석은 사물함 사용 가능 이용권이다.")
    @Test
    void fixedTypeIsLockerType() {
        // given
        StudyCafePassType passType = StudyCafePassType.FIXED;

        // when
        boolean isLockerType = passType.isLockerType();
        boolean isNotLockerType = passType.isNotLockerType();

        // then
        Assertions.assertThat(isLockerType).isTrue();
        Assertions.assertThat(isNotLockerType).isFalse();
    }

}
