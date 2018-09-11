package com.spacex.concurrent.mis.pattern;

public class Factory {
    private static boolean FLAG_i18n_ENABLE = true;

    Consumer build() {
        Update update = FLAG_i18n_ENABLE ? new I18NUpdate() : new NonI18NUpdate();
        return new Consumer(update);
    }
}
