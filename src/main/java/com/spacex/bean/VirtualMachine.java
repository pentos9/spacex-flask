package com.spacex.bean;

public class VirtualMachine {
    private Long id;
    private String name;
    private String engine;
    private String version;

    public VirtualMachine() {
    }

    public VirtualMachine(Long id, String name, String engine, String version) {
        this.id = id;
        this.name = name;
        this.engine = engine;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "VirtualMachine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", engine='" + engine + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
