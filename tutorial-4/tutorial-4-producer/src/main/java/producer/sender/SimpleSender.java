package producer.sender;

import domain.Student;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class SimpleSender {
    @Autowired
    RabbitTemplate rabbitTemplate;

    public void send() {
        Student student = new Student();
        IntStream.range(0, 10)
                .forEach(i -> {
                    student.id = i;
                    student.name = "TEST" + i;
                    student.score = 100 - i;

                    String key = "mod" + i % 3;
                    rabbitTemplate.convertAndSend("tutorial-4-exchange", key, student);
                });
    }
}
