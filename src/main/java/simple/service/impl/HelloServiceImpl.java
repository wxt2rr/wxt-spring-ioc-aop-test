package simple.service.impl;

import simple.service.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String ping() {
        return "pong!";
    }
}
