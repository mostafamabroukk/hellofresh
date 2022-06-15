package com.hellofresh.demo.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;
import java.util.Vector;

import com.hellofresh.demo.model.Point;
import org.springframework.stereotype.Service;

@Service
public class CounterService {

//    This method formats the numbers into one string
    private String getResultString(Vector<Object> list) {
        String formattedStr0 = String.valueOf(list.get(0));
        String formattedStr1 = String.format("%.10f", list.get(1));
        String formattedStr2 = String.format("%.10f", list.get(2));
        String formattedStr3 = String.valueOf(list.get(3));
        String formattedStr4 = String.format("%.3f", list.get(4));
        return formattedStr0 +","+ formattedStr1 +","+ formattedStr2 +","+ formattedStr3 +","+ formattedStr4;
    }

    // check if each point fulfill the condition. Could be updated easily.
    private static boolean doesFulfill(Point example) {
        LocalDateTime localTimeNow = LocalDateTime.now(ZoneId.systemDefault());
        LocalDateTime localTimeBefore60Sec = localTimeNow.minusSeconds(60);
        LocalDateTime triggerTime =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(example.getTime()),
                        TimeZone.getDefault().toZoneId());

        if (triggerTime.isAfter(localTimeBefore60Sec)) {
            return true;
        }
        return false;
    }

    public String collectAllPoints(Vector<Object> list, String inputString) {
        if (inputString.isEmpty())
            return "";

        String[] pointsArray = inputString.split("\n");

        double sumX = 0;
        long sumY = 0;
        int counter = 0;

        // if the API called more than once, so each point should be added to the existing ones
        if(list.size()!=0)
        {
            counter = (int) list.get(0);
            sumX = (double) list.get(1);
            sumY = (long) list.get(3);
        }


        for (String pointStr : pointsArray) {
            pointStr = pointStr.replace("\n", "").replace("\r", "");
            String[] pointAttributes = pointStr.split(",");

            // a messed row
            if (pointAttributes.length != 3) {
                continue;
            }

            Point point = new Point(pointAttributes[0], pointAttributes[1], pointAttributes[2]);
            if (doesFulfill(point)) {
                sumX = sumX + point.getX();
                sumY = sumY + point.getY();
                counter++;
            }
        }
        double averageX = sumX / counter;
        double averageY = sumY / counter;

        // I cleared the list to update the value
        list.clear();
        list.add(counter);
        list.add(sumX);
        list.add(averageX);
        list.add(sumY);
        list.add(averageY);

        return getResultString(list);
    }

}
