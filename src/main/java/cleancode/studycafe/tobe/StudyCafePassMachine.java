package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.FileHandler;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafeLockerPasses;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassList;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final FileHandler fileHandler;

    public StudyCafePassMachine(InputHandler inputHandler, OutputHandler outputHandler, FileHandler fileHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.fileHandler = fileHandler;
    }

    public void run() {
        try {
            outputHandler.showStartMessage();

            StudyCafePassType studyCafePassType = getPassTypeInputFromUser();
            StudyCafePass selectedPass = getPassByType(studyCafePassType);
            StudyCafeLockerPass selectedLockerPass = getLockerPassByType(selectedPass);

            outputHandler.showPassOrderSummary(selectedPass, selectedLockerPass);
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }


    private StudyCafePassType getPassTypeInputFromUser() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    private StudyCafePass getPassByType(StudyCafePassType studyCafePassType) {
        StudyCafePassList allStudyCafePassList = fileHandler.readStudyCafePasses();
        List<StudyCafePass> studyCafePassListByType = allStudyCafePassList.getPassListByType(studyCafePassType);
        outputHandler.showPassListForSelection(studyCafePassListByType);
        return inputHandler.getSelectPass(studyCafePassListByType);
    }

    private StudyCafeLockerPass getLockerPassByType(StudyCafePass selectedPass) {
        StudyCafeLockerPasses allStudyCafeLockerPasses = fileHandler.readLockerPasses();
        StudyCafeLockerPass lockerPassBySelectedPass = allStudyCafeLockerPasses.getLockerPassBySelectedPass(
                selectedPass).orElse(null);

        if (lockerPassBySelectedPass != null) {
            outputHandler.askLockerPass(lockerPassBySelectedPass);
            if (inputHandler.getLockerSelection()) {
                return lockerPassBySelectedPass;
            }
        }
        return null;
    }

}
