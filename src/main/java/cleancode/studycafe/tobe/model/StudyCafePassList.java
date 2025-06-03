package cleancode.studycafe.tobe.model;

import java.util.List;

public class StudyCafePassList {
    private final List<StudyCafePass> studyCafePasses;

    public StudyCafePassList(List<StudyCafePass> studyCafePasses) {
        this.studyCafePasses = studyCafePasses;
    }

    public List<StudyCafePass> getPassListByType(StudyCafePassType studyCafePassType) {
        return studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.equalsPassType(studyCafePassType))
                .toList();
    }
}
