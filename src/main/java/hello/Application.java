package hello;

import com.dgtek.jspring.MsgPrinter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Application {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(Application.class);
        MessagePrinter printer = context.getBean(MessagePrinter.class);
//        printer.printMessage();
        context.getBean(MsgPrinter.class).print();
    }

    @Bean
    MessageService mockMessageService() {
/*
        return new MessageService() {
            @Override
            public String getMessage() {
                return "Hello World" ;
            }
        };
*/
        return () -> "Hello World"; //lambda expression for above
    }

}
