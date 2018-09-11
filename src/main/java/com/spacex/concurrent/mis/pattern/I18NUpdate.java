package com.spacex.concurrent.mis.pattern;

public class I18NUpdate extends Update {
    @Override
    public void execute() {
        System.out.println(String.format("[%s] executing...", I18NUpdate.class.getSimpleName()));
    }

    @Override
    public void render() {
        System.out.println(String.format("[%s] rendering...", I18NUpdate.class.getSimpleName()));
    }
}
