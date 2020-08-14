package top.mrl.server.servlet;

public class Entity {
    private String name;
    private String cls;

    public Entity() {
    }

    public Entity(String name, String cls) {
        this.name = name;
        this.cls = cls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }
}
