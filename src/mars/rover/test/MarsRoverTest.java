package mars.rover.test;

import custom.exception.ValidationException;
import mars.rover.CardinalCompassPointEnum;
import mars.rover.MarsRover;
import mars.rover.entity.RoboticRover;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MarsRoverTest {

    private List<RoboticRover> roboticRoverList;
    private RoboticRover roboticRoverOne;
    private RoboticRover roboticRoverTwo;

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int THREE = 3;
    private static final int FIVE = 5;
    private static final String PLATEAU_GRID_EMPTY_INPUT = "";
    private static final String PROVIDE_PLATEAU_GRID_EXCEPTION = "Please provide a plateau grid.";
    private static final String ROBOTIC_ROVER_EXCEEDS_PLATEAU_GRID_EXCEPTION = "Please note that the robotic rover cannot leave the plateau grid.";
    private static final String PLATEAU_GRID_DOES_NOT_CONTAIN_TWO_CHARACTERS_SEPARATED_BY_ONE_SPACE_EXCEPTION = "Please make sure the plateau grid contains 2 characters separated by one space.";
    private static final String PLATEAU_GRID_INPUT_DOES_NOT_CONTAIN_TWO_CHARACTERS_SEPARATED_BY_ONE_SPACE = "55";
    private static final String FIRST_CHARACTER_IS_NOT_A_DIGIT_EXCEPTION = "Please make sure the first character in the plateau grid is a digit.";
    private static final String SECOND_CHARACTER_IS_NOT_A_DIGIT_EXCEPTION = "Please make sure the second character in the plateau grid is a digit.";
    private static final String PLATEAU_GRID_INPUT_FIRST_CHARACTER_IS_NOT_A_DIGIT = "u 5";
    private static final String PLATEAU_GRID_INPUT_SECOND_CHARACTER_IS_NOT_A_DIGIT = "5 t";
    private static final String VALID_PLATEAU_GRID_INPUT = "5 5";
    private static final String NO_ROBOTIC_ROVERS_EXCEPTION = "Please provide some robotic rovers.";
    private static final String VALID_ROBOTIC_ROVER_POSITION_ONE = "1 2 N";
    private static final String VALID_ROBOTIC_ROVER_POSITION_TWO = "3 3 E";
    private static final String INVALID_ROBOTIC_ROVER_POSITION = "3 20 E";
    private static final String VALID_ROBOTIC_ROVER_COMMAND_ONE = "LMLMLMLMM";
    private static final String VALID_ROBOTIC_ROVER_COMMAND_TWO = "MMRMMRMRRM";

    @org.junit.Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        roboticRoverOne = new RoboticRover(VALID_ROBOTIC_ROVER_POSITION_ONE, VALID_ROBOTIC_ROVER_COMMAND_ONE);
        roboticRoverList = new ArrayList<>();
        roboticRoverList.add(roboticRoverOne);
    }

    @Test
    public void should_throw_exception_if_plateau_grid_is_not_provided() throws ValidationException {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage(PROVIDE_PLATEAU_GRID_EXCEPTION);
        new MarsRover(PLATEAU_GRID_EMPTY_INPUT, new ArrayList<RoboticRover>());
    }

    @Test
    public void should_throw_exception_if_plateau_grid_does_not_contain_two_characters_separated_by_one_space() throws ValidationException {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage(PLATEAU_GRID_DOES_NOT_CONTAIN_TWO_CHARACTERS_SEPARATED_BY_ONE_SPACE_EXCEPTION);
        new MarsRover(PLATEAU_GRID_INPUT_DOES_NOT_CONTAIN_TWO_CHARACTERS_SEPARATED_BY_ONE_SPACE, roboticRoverList);
    }

    @Test
    public void should_throw_exception_if_the_first_character_in_the_plateau_grid_is_not_a_digit() throws ValidationException {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage(FIRST_CHARACTER_IS_NOT_A_DIGIT_EXCEPTION);
        new MarsRover(PLATEAU_GRID_INPUT_FIRST_CHARACTER_IS_NOT_A_DIGIT, roboticRoverList);
    }

    @Test
    public void should_throw_exception_if_the_second_character_in_the_plateau_grid_is_not_a_digit() throws ValidationException {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage(SECOND_CHARACTER_IS_NOT_A_DIGIT_EXCEPTION);
        new MarsRover(PLATEAU_GRID_INPUT_SECOND_CHARACTER_IS_NOT_A_DIGIT, roboticRoverList);
    }

    @Test
    public void should_throw_exception_if_no_robotic_rovers_are_provided() throws ValidationException {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage(NO_ROBOTIC_ROVERS_EXCEPTION);
        new MarsRover(VALID_PLATEAU_GRID_INPUT, null);
    }

    @Test
    public void should_throw_exception_if_an_empty_list_of_robotic_rovers_are_provided() throws ValidationException {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage(NO_ROBOTIC_ROVERS_EXCEPTION);
        new MarsRover(VALID_PLATEAU_GRID_INPUT, new ArrayList<RoboticRover>());
    }

    @Test
    public void should_throw_exception_if_robotic_rover_attempts_to_leaves_the_plateau_grid() throws ValidationException {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage(ROBOTIC_ROVER_EXCEEDS_PLATEAU_GRID_EXCEPTION);

        roboticRoverTwo = new RoboticRover(INVALID_ROBOTIC_ROVER_POSITION, VALID_ROBOTIC_ROVER_COMMAND_TWO);
        roboticRoverList.add(roboticRoverTwo);
        MarsRover marsRover = new MarsRover(VALID_PLATEAU_GRID_INPUT, roboticRoverList);
        marsRover.executeReceivedCommandsForEachRoboticRover();
    }

    @Test
    public void plateau_grid_max_x_coordinate_should_be_a_digit_five() throws ValidationException {
        new ArrayList<RoboticRover>();
        assertEquals(new MarsRover(VALID_PLATEAU_GRID_INPUT, roboticRoverList).getMaxPlateauXCoordinate(), FIVE);
    }

    @Test
    public void plateau_grid_max_y_coordinate_should_be_a_digit_five() throws ValidationException {
        assertEquals(new MarsRover(VALID_PLATEAU_GRID_INPUT, roboticRoverList).getMaxPlateauYCoordinate(), FIVE);
    }

    @Test
     public void should_return_one_three_N_for_rover_one_given_rover_one_position_is_one_two_N_and_the_given_command_is_LMLMLMLMM() throws ValidationException {
        roboticRoverTwo = new RoboticRover(VALID_ROBOTIC_ROVER_POSITION_TWO, VALID_ROBOTIC_ROVER_COMMAND_TWO);
        roboticRoverList.add(roboticRoverTwo);
        MarsRover marsRover = new MarsRover(VALID_PLATEAU_GRID_INPUT, roboticRoverList);
        marsRover.executeReceivedCommandsForEachRoboticRover();

        RoboticRover roboticRoverOneChanged = marsRover.getRoboticRoverList().get(ZERO);
        assertEquals(roboticRoverOneChanged.getCoordinate().getX(), ONE);
        assertEquals(roboticRoverOneChanged.getCoordinate().getY(), THREE);
        Assert.assertEquals(roboticRoverOneChanged.getCardinalCompassPoint().getHeading(), CardinalCompassPointEnum.NORTH.getHeading());
    }

    @Test
    public void should_return_five_one_E_for_rover_two_given_rover_two_position_is_three_three_E_and_the_given_command_is_MMRMMRMRRM() throws ValidationException {
        roboticRoverTwo = new RoboticRover(VALID_ROBOTIC_ROVER_POSITION_TWO, VALID_ROBOTIC_ROVER_COMMAND_TWO);
        roboticRoverList.add(roboticRoverTwo);
        MarsRover marsRover = new MarsRover(VALID_PLATEAU_GRID_INPUT, roboticRoverList);
        marsRover.executeReceivedCommandsForEachRoboticRover();

        RoboticRover roboticRoverTwoChanged = marsRover.getRoboticRoverList().get(ONE);
        assertEquals(roboticRoverTwoChanged.getCoordinate().getX(), FIVE);
        assertEquals(roboticRoverTwoChanged.getCoordinate().getY(), ONE);
        assertEquals(roboticRoverTwoChanged.getCardinalCompassPoint().getHeading(), CardinalCompassPointEnum.EAST.getHeading());
    }
}