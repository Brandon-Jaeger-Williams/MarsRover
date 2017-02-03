package mars.rover.entity;

import custom.exception.ValidationException;
import mars.rover.CardinalCompassPointEnum;

import java.util.Arrays;
import java.util.List;

public class RoboticRover {

    private Coordinate coordinate;
    private CardinalCompassPointEnum cardinalCompassPoint;
    private List<String> commands;

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final String ONE_SPACE = " ";
    private static final String DIGIT = "\\d+";
    private static final String L_R_M_CHARACTERS = "[LRM]+";
    private static final String PROVIDE_ROBOTIC_ROVER_POSITION_EXCEPTION = "Please provide robotic rover position.";
    private static final String FIRST_CHARACTER_IS_NOT_A_DIGIT_EXCEPTION = "Please make sure the first character in the robotic rover position is a digit.";
    private static final String SECOND_CHARACTER_IS_NOT_A_DIGIT_EXCEPTION = "Please make sure the second character in the robotic rover position is a digit.";
    private static final String THIRD_CHARACTER_IS_NOT_A_VALID_CARDINAL_COMPASS_POINT_EXCEPTION = "Please make sure the third character in the robotic rover position is a valid cardinal compass point.";
    private static final String POSITION_DOES_NOT_CONTAIN_THREE_CHARACTERS_SEPARATED_BY_ONE_SPACE_EXCEPTION = "Please make sure the robotic rover position contains 3 characters separated by one space.";
    private static final String PROVIDE_ROBOTIC_ROVER_COMMAND_EXCEPTION = "Please provide robotic rover command.";
    private static final String INVALID_ROBOTIC_ROVER_COMMAND_EXCEPTION = "Please provide a valid robotic rover command.";

    public RoboticRover(String position, String command) throws ValidationException {
        validateRoboticRoverPosition(position);
        validateCommandGivenToRoboticRover(command);
        setUpRoboticRoverPosition(position);
        setUpRoboticRoverCommand(command);
    }

    private void setUpRoboticRoverCommand(String command) {
        this.commands = Arrays.asList(command.split(""));
    }

    private void validateCommandGivenToRoboticRover(String command) throws ValidationException {
        checkIfCommandIsEmpty(command);
        checkIfCommandOnlyContainsTheLettersLRM(command);
    }

    private void checkIfCommandOnlyContainsTheLettersLRM(String command) throws ValidationException {
        if (!command.matches(L_R_M_CHARACTERS))
            throw new ValidationException(INVALID_ROBOTIC_ROVER_COMMAND_EXCEPTION);
    }

    private void checkIfCommandIsEmpty(String command) throws ValidationException {
        if (isEmpty(command))
            throw new ValidationException(PROVIDE_ROBOTIC_ROVER_COMMAND_EXCEPTION);
    }

    private void setUpRoboticRoverPosition(String position) {
        String[] positions = splitPositionOnOneSpace(position);
        this.coordinate = new Coordinate(Integer.parseInt(positions[ZERO]), Integer.parseInt(positions[ONE]));
        this.cardinalCompassPoint = CardinalCompassPointEnum.get(positions[TWO]);
    }

    private String[] splitPositionOnOneSpace(String position) {
        return position.split(ONE_SPACE);
    }

    private void validateRoboticRoverPosition(String position) throws ValidationException {
        checkIfPositionIsEmpty(position);

        String[] positions = splitPositionOnOneSpace(position);
        checkIfPositionsSizeIsThree(positions);
        checkIfTheProvidedCharacterInThePositionIsADigit(positions[ZERO], FIRST_CHARACTER_IS_NOT_A_DIGIT_EXCEPTION);
        checkIfTheProvidedCharacterInThePositionIsADigit(positions[ONE], SECOND_CHARACTER_IS_NOT_A_DIGIT_EXCEPTION);
        checkIfTheThirdCharacterInThePositionIsAnInValidCardinalCompassPoint(positions[TWO]);
    }

    private void checkIfTheThirdCharacterInThePositionIsAnInValidCardinalCompassPoint(String position) throws ValidationException {
        if (!CardinalCompassPointEnum.isValidCardinalCompassPoint(position))
            throw new ValidationException(THIRD_CHARACTER_IS_NOT_A_VALID_CARDINAL_COMPASS_POINT_EXCEPTION);
    }

    private void checkIfTheProvidedCharacterInThePositionIsADigit(String position, String message) throws ValidationException {
        if (!position.matches(DIGIT))
            throw new ValidationException(message);
    }

    private void checkIfPositionsSizeIsThree(String[] positions) throws ValidationException {
        if (positions.length != 3)
            throw new ValidationException(POSITION_DOES_NOT_CONTAIN_THREE_CHARACTERS_SEPARATED_BY_ONE_SPACE_EXCEPTION);
    }

    private void checkIfPositionIsEmpty(String position) throws ValidationException {
        if (isEmpty(position))
            throw new ValidationException(PROVIDE_ROBOTIC_ROVER_POSITION_EXCEPTION);
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().length() == ZERO;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public CardinalCompassPointEnum getCardinalCompassPoint() {
        return cardinalCompassPoint;
    }

    public void setCardinalCompassPoint(CardinalCompassPointEnum cardinalCompassPoint) {
        this.cardinalCompassPoint = cardinalCompassPoint;
    }

    public List<String> getCommands() {
        return commands;
    }
}