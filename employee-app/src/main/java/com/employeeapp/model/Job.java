package com.employeeapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Job {

    @JsonProperty("job_id")
    private Integer jobId;
}
