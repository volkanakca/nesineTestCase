package com.NesineTest.automation.driver;

public class CurrentPageContext {
    private static final ThreadLocal<PageGenarator> localCurrentPage = new ThreadLocal();

    public CurrentPageContext() {
    }

    public static PageGenarator getCurrentPage() {

        return localCurrentPage.get();
    }

    public static void setCurrentPage(PageGenarator driverThreadLocal) {

        localCurrentPage.set(driverThreadLocal);
    }
}
