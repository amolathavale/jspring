package com.dgtek.jspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MyController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private UserRepository repo;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
//        repo.save(new User(0, "Amol", "AAA"));
//        User u = repo.findById(new Long(1)).get();
//        System.out.println("##### " + u.toString());
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") long uid) {
        return repo.findById(uid)
                .orElseThrow(() -> new RuntimeException("User with given id: " + uid + " not found"));
    }

    @GetMapping(value = "/users")
    public List<User> getUsers(@RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "nickName", required = false) String nickName) {
        System.out.println("@@@@@" + name);
        ArrayList<User> list = new ArrayList<>();

        if (name == null && nickName == null) {
            repo.findAll().forEach(list::add);
        } else if (name != null && nickName != null) {
            repo.findByNameAndNickName(name, nickName).addAll(list);
        } else if (name != null) {
            repo.findByName(name).addAll(list);
        } else {
            repo.findByNickName(nickName).addAll(list);
        }

        return list;
    }

}
