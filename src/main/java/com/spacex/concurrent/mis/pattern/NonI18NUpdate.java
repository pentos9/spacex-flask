package com.spacex.concurrent.mis.pattern;

public class NonI18NUpdate extends Update {
    @Override
    public void execute() {
        System.out.println(String.format("[%s] executing...", NonI18NUpdate.class.getSimpleName()));

    }

    @Override
    public void render() {
        System.out.println(String.format("[%s] rendering...", NonI18NUpdate.class.getSimpleName()));
    }
}
