package com.fib.service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


@ApplicationPath("/api")
public class WebApplication extends Application
{
    private Set<Object> singletons = new HashSet<Object>();

    public WebApplication()
    {
        singletons.add(new FibResource());
    }

    @Override
    public Set<Object> getSingletons()
    {
        return singletons;
    }
}