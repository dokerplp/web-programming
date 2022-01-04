package dokerplp.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "responses")
@Getter
@Setter
@NoArgsConstructor
public class ResponseEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "responses_next_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "x")
    private Float x;

    @Column(name = "y")
    private Float y;

    @Column(name = "r")
    private Float r;

    @Column(name = "curtime")
    @CreationTimestamp
    private Timestamp curTime;

    @Column(name = "extime")
    private Long exTime;

    @Column(name = "res")
    private Boolean res;

    @Column(name = "left")
    private Float left;

    @Column(name = "top")
    private Float top;

    public ResponseEntity(Long userId, Float x, Float y, Float r, Long exTime, Boolean res, Float left, Float top) {
        this.userId = userId;
        this.x = x;
        this.y = y;
        this.r = r;
        this.exTime = exTime;
        this.res = res;
        this.left = left;
        this.top = top;
    }

    @Override
    public String toString() {
        return String.format(
                "{" +
                        "\"id\": \"%d\"," +
                        "\"x\": \"%.2f\"," +
                        "\"y\": \"%.2f\"," +
                        "\"r\": \"%d\"," +
                        "\"curTime\": \"%s\"," +
                        "\"exTime\": \"%s\"," +
                        "\"res\": \"%b\"," +
                        "\"left\": \"%f\"," +
                        "\"top\": \"%f\"" +
                "}",
                id, x, y, r.intValue(), curTime, exTime, res, left, top
        );
    }
}