package net.bahmed.hyperbee.service;

import net.bahmed.hyperbee.dao.NoteDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author bashir
 * @since 11/26/16
 */
@Service
@EnableScheduling
public class Scheduler {

    private static final Logger log = LogManager.getLogger(SimpleLogger.class);

    @Autowired
    NoteDao noteDao;

    @Scheduled(cron = "0 0 0 ? * *")
    public void scheduleNoteExpireJob() {

        log.debug("Archiving expired notes....");
        noteDao.markExpiredNoteAsInactive();
    }
}
