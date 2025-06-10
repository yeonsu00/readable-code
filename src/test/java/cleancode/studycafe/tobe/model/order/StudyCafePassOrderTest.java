package cleancode.studycafe.tobe.model.order;

import static org.junit.jupiter.api.Assertions.*;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudyCafePassOrderTest {

    @DisplayName("주 이용권일 경우 총 결제 금액을 계산한다.")
    @Test
    void getTotalPrice_weekly_noLocker() {
        // given
        StudyCafeSeatPass weeklySeatPass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 4, 150000, 0.1);
        StudyCafePassOrder order = StudyCafePassOrder.of(weeklySeatPass, null);

        // when
        int totalPrice = order.getTotalPrice();

        // then
        Assertions.assertThat(totalPrice).isEqualTo(135000);
    }

    @DisplayName("고정석 이용권이고, 사물함을 선택했을 때 총 결제 금액을 계산한다.")
    @Test
    void getTotalPrice_fixed_withLocker() {
        // given
        StudyCafeSeatPass fixedSeatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 700000, 0.15); // 할인 105000
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 12, 30000);
        StudyCafePassOrder order = StudyCafePassOrder.of(fixedSeatPass, lockerPass);

        // when
        int totalPrice = order.getTotalPrice();

        // then
        Assertions.assertThat(totalPrice).isEqualTo(625000);
    }

}
