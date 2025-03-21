package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework002.model.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer instructorId;
    private String instructorName;
    private String instructorEmail;
}
