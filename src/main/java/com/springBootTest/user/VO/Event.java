package com.springBootTest.user.VO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
  
    private String eventId;
    private String title;
    private String description;
    private String location;
    private String providerName;
    private String providerId;
    private String coverPhoto;
    private String date;
    private String time;
    private int seatsNumber;
    private int bookedSeats;
    private int availableSeats;
    private List<String> priceLists;
    private List<String> eventImages;

}
