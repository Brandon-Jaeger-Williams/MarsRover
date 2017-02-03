package mars.rover.test;


import custom.exception.ValidationException;
import mars.rover.entity.RoboticRover;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class RoboticRoverTest {

    private static final int DIGIT_TWO = 2;
    private static final int DIGIT_THREE = 3;
    private static final int DIGIT_TEN = 10;
    private static final String PROVIDE_ROBOTIC_ROVER_POSITION_EXCEPTION = "Please provide robotic rover position.";
    private static final String FIRST_CHARACTER_IS_NOT_A_DIGIT_EXCEPTION = "Please make sure the first character in the robotic rover position is a digit.";
    private static final String SECOND_CHARACTER_IS_NOT_A_DIGIT_EXCEPTION = "Please make sure the second character in the robotic rover position is a digit.";
    private static final String THIRD_CHARACTER_IS_NOT_A_VALID_CARDINAL_COMPASS_POINT_EXCEPTION = "Please make sure the third character in the robotic rover position is a valid cardinal compass point.";
    private static final String POSITION_DOES_NOT_CONTAIN_THREE_CHARACTERS_SEPARATED_BY_ONE_SPACE_EXCEPTION = "Please make sure the robotic rover position contains 3 characters separated by one space.";
    private static final String PROVIDE_ROBOTIC_ROVER_COMMAND_EXCEPTION = "Please provide robotic rover command.";
    private static final String INVALID_ROBOTIC_ROVER_COMMAND_EXCEPTION = "Please provide a valid robotic rover command.";
    private static final String ROBOTIC_ROVER_EMPTY_INPUT = "";
    private static final String ROBOTIC_ROVER_INPUT_DOES_NOT_CONTAIN_THREE_CHARACTERS_SEPARATED_BY_ONE_SPACE = "23N";
    private static final String ROBOTIC_ROVER_INPUT_FIRST_CHARACTER_IS_NOT_A_DIGIT = "u 3 N";
    private static final String ROBOTIC_ROVER_INPUT_SECOND_CHARACTER_IS_NOT_A_DIGIT = "2 t N";
    private static final String ROBOTIC_ROVER_INPUT_THIRD_CHARACTER_IS_NOT_A_VALID_CARDINAL_COMPASS_POINT = "2 3 f";
    private static final String VALID_ROBOTIC_ROVER_POSITION = "2 3 N";
    private static final String INVALID_ROBOTIC_ROVER_COMMAND = "LMLMLMLMMT";
    private static final String VALID_ROBOTIC_ROVER_COMMAND = "MMRMMRMRRM";
    private static final String N_CARDINAL_COMPASS_POINT = "N";

    @org.junit.Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_throw_exception_if_robotic_rover_position_is_not_provided() throws ValidationException {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage(PROVIDE_ROBOTIC_ROVER_POSITION_EXCEPTION);
        new RoboticRover(ROBOTIC_ROVER_EMPTY_INPUT, ROBOTIC_ROVER_EMPTY_INPUT);
    }

    @Test
    public void should_throw_exception_if_robotic_rover_position_does_not_contain_three_characters_separated_by_one_space() throws ValidationException {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage(POSITION_DOES_NOT_CONTAIN_THREE_CHARACTERS_SEPARATED_BY_ONE_SPACE_EXCEPTION);
        new RoboticRover(ROBOTIC_ROVER_INPUT_DOES_NOT_CONTAIN_THREE_CHARACTERS_SEPARATED_BY_ONE_SPACE, ROBOTIC_ROVER_EMPTY_INPUT);
    }

    @Test
    public void should_throw_exception_if_the_first_character_in_the_robotic_rover_position_is_not_a_digit() throws ValidationException {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage(FIRST_CHARACTER_IS_NOT_A_DIGIT_EXCEPTION);
        new RoboticRover(ROBOTIC_ROVER_INPUT_FIRST_CHARACTER_IS_NOT_A_DIGIT, ROBOTIC_ROVER_EMPTY_INPUT);
    }

    @Test
    public void should_throw_exception_if_the_second_character_in_the_robotic_rover_position_is_not_a_digit() throws ValidationException {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage(SECOND_CHARACTER_IS_NOT_A_DIGIT_EXCEPTION);
        new RoboticRover(ROBOTIC_ROVER_INPUT_SECOND_CHARACTER_IS_NOT_A_DIGIT, ROBOTIC_ROVER_EMPTY_INPUT);
    }

    @Test
    public void should_throw_exception_if_the_third_character_in_the_robotic_rover_position_is_not_a_cardinal_compass_point() throws ValidationException {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage(THIRD_CHARACTER_IS_NOT_A_VALID_CARDINAL_COMPASS_POINT_EXCEPTION);
        new RoboticRover(ROBOTIC_ROVER_INPUT_THIRD_CHARACTER_IS_NOT_A_VALID_CARDINAL_COMPASS_POINT, ROBOTIC_ROVER_EMPTY_INPUT);
    }

    @Test
    public void should_throw_exception_if_the_given_command_for_robotic_rover_is_not_provided() throws ValidationException {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage(PROVIDE_ROBOTIC_ROVER_COMMAND_EXCEPTION);
        new RoboticRover(VALID_ROBOTIC_ROVER_POSITION, ROBOTIC_ROVER_EMPTY_INPUT);
    }

    @Test
    public void should_throw_exception_if_the_given_command_for_robotic_rover_is_invalid() throws ValidationException {
        expectedException.expect(ValidationException.class);
        expectedException.expectMessage(INVALID_ROBOTIC_ROVER_COMMAND_EXCEPTION);
        new RoboticRover(VALID_ROBOTIC_ROVER_POSITION, INVALID_ROBOTIC_ROVER_COMMAND);
    }

    @Test
    public void robotic_rover_x_coordinate_should_be_a_digit_two() throws ValidationException {
        assertEquals(new RoboticRover(VALID_ROBOTIC_ROVER_POSITION, VALID_ROBOTIC_ROVER_COMMAND).getCoordinate().getX(), DIGIT_TWO);
    }

    @Test
    public void robotic_rover_y_coordinate_should_be_a_digit_three() throws ValidationException {
        assertEquals(new RoboticRover(VALID_ROBOTIC_ROVER_POSITION, VALID_ROBOTIC_ROVER_COMMAND).getCoordinate().getY(), DIGIT_THREE);
    }

    @Test
    public void robotic_rover_compass_point_should_be_N() throws ValidationException {
        assertEquals(new RoboticRover(VALID_ROBOTIC_ROVER_POSITION, VALID_ROBOTIC_ROVER_COMMAND).getCardinalCompassPoint().getHeading(), N_CARDINAL_COMPASS_POINT);
    }

    @Test
    public void robotic_rover_command_length_should_be_ten_characters_long() throws ValidationException {
        assertTrue(new RoboticRover(VALID_ROBOTIC_ROVER_POSITION, VALID_ROBOTIC_ROVER_COMMAND).getCommands().size() == DIGIT_TEN);
    }
}