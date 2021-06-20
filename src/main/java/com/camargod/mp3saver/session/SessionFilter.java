package com.camargod.mp3saver.session;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

public class SessionFilter extends AbstractHttpSessionApplicationInitializer {

    public SessionFilter(){
        super(SessionConnection.class);
    }


}
