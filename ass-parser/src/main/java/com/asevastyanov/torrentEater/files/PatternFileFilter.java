package com.asevastyanov.torrentEater.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

class PatternFileFilter implements FileFilter {
    private static final Logger logger = LoggerFactory.getLogger(PatternFileFilter.class);
    private Pattern pattern;


    PatternFileFilter(String regExp) {
        try {
            pattern = Pattern.compile(regExp);
        } catch (Exception e) {
            logger.debug("Invalide regExp", e);
        }
    }

    public boolean accept(File pathname) {
        if (pattern == null) {
            return false;
        }

        String fileName = pathname.getName().toLowerCase();
        return pattern.matcher(fileName).find();
    }
}

