package Models;

public class Unit {
    private String name;

    public Unit(String _name) {
        setName(_name);
    }

    public void setName(String _name) {
        name = _name.trim().toUpperCase();
    }

    public String getName() {
        return name.trim().toUpperCase();
    }
}
