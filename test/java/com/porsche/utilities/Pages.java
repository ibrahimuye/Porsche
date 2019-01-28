package com.porsche.utilities;

import com.porsche.pages.*;

public class Pages {
    private Model718 m718;
    private Model718CaymanS m718CS;
    private StartPage start;

    public Model718 m718() {
        if (m718 == null) {
            m718 = new Model718();
        }
        return m718;
    }

    public Model718CaymanS m718CS() {
        if (m718CS == null) {
            m718CS = new Model718CaymanS();
        }
        return m718CS;
    }

    public StartPage start() {
        if (start == null) {
            start = new StartPage();
        }
        return start;
    }


}
