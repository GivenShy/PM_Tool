package am.hitech.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "task")
public class Task {
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private User assignee;

    private String summary;

    private String dsc;

    private String status;

    private String priority;

    private int progress;

    private long createdDate;

    private long dueDate;

    private int estimatedTime;

    private int projectId;
}
