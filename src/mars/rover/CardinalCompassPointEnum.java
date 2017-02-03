package mars.rover;

import mars.rover.entity.Coordinate;

public enum CardinalCompassPointEnum {

    NORTH("N") {
        @Override
        public CardinalCompassPointEnum turnLeft() {
            return WEST;
        }

        @Override
        public CardinalCompassPointEnum turnRight() {
            return EAST;
        }

        @Override
        public Coordinate move(Coordinate coordinate) {
            coordinate.setY(coordinate.getY() + 1);
            return coordinate;
        }
    },
    EAST("E") {
        @Override
        public CardinalCompassPointEnum turnLeft() {
            return NORTH;
        }

        @Override
        public CardinalCompassPointEnum turnRight() {
            return SOUTH;
        }

        @Override
        public Coordinate move(Coordinate coordinate) {
            coordinate.setX(coordinate.getX() + 1);
            return coordinate;
        }
    },
    SOUTH("S") {
        @Override
        public CardinalCompassPointEnum turnLeft() {
            return EAST;
        }

        @Override
        public CardinalCompassPointEnum turnRight() {
            return WEST;
        }

        @Override
        public Coordinate move(Coordinate coordinate) {
            coordinate.setY(coordinate.getY() - 1);
            return coordinate;
        }
    },
    WEST("W") {
        @Override
        public CardinalCompassPointEnum turnLeft() {
            return SOUTH;
        }

        @Override
        public CardinalCompassPointEnum turnRight() {
            return NORTH;
        }

        @Override
        public Coordinate move(Coordinate coordinate) {
            coordinate.setX(coordinate.getX() - 1);
            return coordinate;
        }
    };

    private String heading;

    CardinalCompassPointEnum(String heading) {
        this.heading = heading;
    }

    public static boolean isValidCardinalCompassPoint(String cardinalCompassPoint) {
        for (CardinalCompassPointEnum point : CardinalCompassPointEnum.values()) {
            if (point.getHeading().equals(cardinalCompassPoint)) {
                return true;
            }
        }
        return false;
    }

    public static CardinalCompassPointEnum get(String cardinalCompassPoint) {
        for (CardinalCompassPointEnum point : CardinalCompassPointEnum.values()) {
            if (point.getHeading().equals(cardinalCompassPoint)) {
                return point;
            }
        }
        return null;
    }

    public String getHeading() {
        return heading;
    }

    public abstract CardinalCompassPointEnum turnLeft();

    public abstract CardinalCompassPointEnum turnRight();

    public abstract Coordinate move(Coordinate coordinate);
}
