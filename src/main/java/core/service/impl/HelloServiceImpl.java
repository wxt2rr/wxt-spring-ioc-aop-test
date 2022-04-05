package core.service.impl;

import core.service.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String ping() {
        return "pong!";
    }
}
