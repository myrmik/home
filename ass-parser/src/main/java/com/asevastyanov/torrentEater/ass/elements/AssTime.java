package com.asevastyanov.torrentEater.ass.elements;

import com.asevastyanov.torrentEater.ass.AssElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AssTime extends AssElement {
    private static final Logger logger = LoggerFactory.getLogger(AssTime.class);
    private GregorianCalendar time = new GregorianCalendar(0,0,0);

    public static final String NAME = "Time";

    public AssTime() {
        super(NAME);
    }

    @Override
    public void parse(String time) {
        if (time == null) return;

        String[] parts = time.split(":|\\.");
        try {
            this.time.add(Calendar.HOUR, Integer.parseInt(parts[0]));
            this.time.add(Calendar.MINUTE, Integer.parseInt(parts[1]));
            this.time.add(Calendar.SECOND, Integer.parseInt(parts[2]));
            this.time.add(Calendar.MILLISECOND, Integer.parseInt(parts[3]) * 10);
        } catch (Exception e) {
            logger.debug("Parsing error", e);
        }
    }

    @Override
    public String toString() {
        String res = new SimpleDateFormat("H:mm:ss.SSS").format(time.getTime());
        return res.substring(0, res.length() - 1);
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(GregorianCalendar time) {
        this.time = time;
    }
}
