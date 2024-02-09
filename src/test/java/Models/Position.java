package Models;

public class Position {

    private String name;
    private Unit unit;

    public Position(String _name, Unit _unit) {
        setName(_name);
        setUnit(_unit);
    }

    public Position(String _name, String unitName) {
        this(_name, new Unit(unitName));
    }

    public String getName() {
        return name.split("\n")[0].trim().toUpperCase();
    }

    public void setName(String name) {
        this.name = name.trim().toUpperCase();
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
