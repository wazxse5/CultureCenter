package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Schedule implements Serializable {
    private static final long serialVersionUID = -3885248255323951505L;
    private int idSchedule;
    private ScheduleStatus scheduleStatus;
    private LocalDateTime stardDateTime;
    private LocalDateTime endDateTime;
    private LocalDateTime creationDateTime;
    private int idBranch;
    private int idEvent;
}

enum ScheduleStatus {
    APPROVED,
    VALID,
    INVALID
}