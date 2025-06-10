package cleancode.studycafe.tobe.model.pass;

import static org.junit.jupiter.api.Assertions.*;

import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudyCafeSeatPassTest {

    @DisplayName("1인 고정석 이용권인 경우 사물함을 선택할 수 있다.")
    @Test
    void fixedTypeCanUseLocker() {
        // given
        StudyCafeSeatPass fixedSeatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 700000, 0.15);

        // when
        boolean canUseLocker = fixedSeatPass.cannotUseLocker();

        // then
        Assertions.assertThat(canUseLocker).isFalse();
    }

    @DisplayName("시간 단위 이용권인 경우 사물함을 선택할 수 없다.")
    @Test
    void hourlyTypeCannotUseLocker() {
        // given
        StudyCafeSeatPass hourlySeatPass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 12, 13000, 0);

        // when
        boolean canUseLocker = hourlySeatPass.cannotUseLocker();

        // then
        Assertions.assertThat(canUseLocker).isTrue();
    }


    @DisplayName("사물함 이용권의 타입과 기간이 좌석 이용권의 타입과 기간과 동일해야 한다.")
    @Test
    void sameDurationAndType() {
        // given
        StudyCafeSeatPass fixedSeatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 700000, 0.15);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 12, 30000);

        // when
        boolean isSameDurationAndType = fixedSeatPass.isSameDurationType(lockerPass);

        // then
        Assertions.assertThat(isSameDurationAndType).isTrue();
    }

    @DisplayName("할인율에 따라 할인 금액을 계산한다.")
    @Test
    void calculateDiscountPrice() {
        // given
        StudyCafeSeatPass fixedSeatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 700000, 0.15);

        // when
        int discountPrice = fixedSeatPass.getDiscountPrice();

        // then
        Assertions.assertThat(discountPrice).isEqualTo(105000);
    }

}
