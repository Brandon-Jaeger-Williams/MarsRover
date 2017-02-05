package mars.rover;

import custom.exception.ValidationException;
import mars.rover.entity.Coordinate;
import mars.rover.entity.RoboticRover;

import java.util.List;

public class MarsRover {

    private int maxPlateauXCoordinate;
    private int maxPlateauYCoordinate;
    private List<RoboticRover> roboticRoverList;

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final String ONE_SPACE = " ";
    private static final String LEFT = "L";
    private static final String RIGHT = "R";
    private static final String DIGIT = "\\d+";
    private static final String PROVIDE_PLATEAU_GRID_EXCEPTION = "Please provide a plateau grid.";
    private static final String PLATEAU_GRID_DOES_NOT_CONTAIN_TWO_CHARACTERS_SEPARATED_BY_ONE_SPACE_EXCEPTION = "Please make sure the plateau grid contains 2 characters separated by one space.";
    private static final String FIRST_CHARACTER_IS_NOT_A_DIGIT_EXCEPTION = "Please make sure the first character in the plateau grid is a digit.";
    private static final String SECOND_CHARACTER_IS_NOT_A_DIGIT_EXCEPTION = "Please make sure the second character in the plateau grid is a digit.";
    private static final String NO_ROBOTIC_ROVERS_EXCEPTION = "Please provide some robotic rovers.";
    private static final String ROBOTIC_ROVER_EXCEEDS_PLATEAU_GRID_EXCEPTION = "Please note that the robotic rover cannot leave the plateau grid.";

    public MarsRover(String plateauGrid, List<RoboticRover> roboticRoverList) throws ValidationException {
        validatePlateauGrid(plateauGrid);
        validateRoboticRoverList(roboticRoverList);
        setUpPlateauGrid(plateauGrid);
        this.roboticRoverList = roboticRoverList;
    }

    private void validateRoboticRoverList(List<RoboticRover> roboticRoverList) throws ValidationException {
        if (roboticRoverList == null || roboticRoverList.size() == ZERO)
            throw new ValidationException(NO_ROBOTIC_ROVERS_EXCEPTION);
    }

    private void setUpPlateauGrid(String plateauGrid) {
        String[] grids = splitPositionOnOneSpace(plateauGrid);
        this.maxPlateauXCoordinate = Integer.parseInt(grids[ZERO]);
        this.maxPlateauYCoordinate = Integer.parseInt(grids[ONE]);
    }

    private void validatePlateauGrid(String plateauGrid) throws ValidationException {
        checkIfPlateauGridIsEmpty(plateauGrid);

        String[] grids = splitPositionOnOneSpace(plateauGrid);
        checkIfPlateauGridsSizeIsTwo(grids);
        checkIfTheProvidedCharacterInThePlateauGridIsADigit(grids[ZERO], FIRST_CHARACTER_IS_NOT_A_DIGIT_EXCEPTION);
        checkIfTheProvidedCharacterInThePlateauGridIsADigit(grids[ONE], SECOND_CHARACTER_IS_NOT_A_DIGIT_EXCEPTION);
    }

    private void checkIfTheProvidedCharacterInThePlateauGridIsADigit(String position, String message) throws ValidationException {
        if (!position.matches(DIGIT))
            throw new ValidationException(message);
    }

    private String[] splitPositionOnOneSpace(String position) {
        return position.split(ONE_SPACE);
    }

    private void checkIfPlateauGridsSizeIsTwo(String[] plateauGrid) throws ValidationException {
        if (plateauGrid.length != TWO)
            throw new ValidationException(PLATEAU_GRID_DOES_NOT_CONTAIN_TWO_CHARACTERS_SEPARATED_BY_ONE_SPACE_EXCEPTION);
    }

    private void checkIfPlateauGridIsEmpty(String plateauGrid) throws ValidationException {
        if (isEmpty(plateauGrid))
            throw new ValidationException(PROVIDE_PLATEAU_GRID_EXCEPTION);
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().length() == ZERO;
    }

    public void executeReceivedCommandsForEachRoboticRover() throws ValidationException {
        for (RoboticRover roboticRover : roboticRoverList) {
            for (String command : roboticRover.getCommands()) {
                if (command.equals(LEFT) || command.equals(RIGHT)) {
                    roboticRover.setCardinalCompassPoint(determineNewCardinalCompassPoint(roboticRover.getCardinalCompassPoint(), command));
                } else {
                    updateCoordinates(roboticRover);
                }
            }
        }
    }

    private void updateCoordinates(RoboticRover roboticRover) throws ValidationException {
        Coordinate newCoordinates = determineNewCoordinates(roboticRover);
        doesRoboticRoverExceedTheCoordinatesOfThePlateauGrid(newCoordinates);
        roboticRover.setCoordinate(newCoordinates);
    }

    private void doesRoboticRoverExceedTheCoordinatesOfThePlateauGrid(Coordinate newCoordinates) throws ValidationException {
        if (newCoordinates.getY() > maxPlateauYCoordinate || newCoordinates.getX() > maxPlateauXCoordinate)
            throw new ValidationException(ROBOTIC_ROVER_EXCEEDS_PLATEAU_GRID_EXCEPTION);
    }

    private Coordinate determineNewCoordinates(RoboticRover roboticRover) {
        return roboticRover.getCardinalCompassPoint().move(roboticRover.getCoordinate());
    }

    private CardinalCompassPointEnum determineNewCardinalCompassPoint(CardinalCompassPointEnum currentCardinalCompassPoint, String turn) {
        if (turn.equals(LEFT)) {
            return currentCardinalCompassPoint.turnLeft();
        } else {
            return currentCardinalCompassPoint.turnRight();
        }
    }

    public int getMaxPlateauXCoordinate() {
        return maxPlateauXCoordinate;
    }

    public int getMaxPlateauYCoordinate() {
        return maxPlateauYCoordinate;
    }

    public List<RoboticRover> getRoboticRoverList() {
        return roboticRoverList;
    }
}