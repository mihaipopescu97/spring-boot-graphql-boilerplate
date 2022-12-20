package com.travelin.travelincronjobs.jobs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/29/2022, Thu
 **/

@Log4j2
@Transactional @Component @EnableAsync
public class TokenBlacklistMaintainerJob implements Job{

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    @Scheduled(cron = "0 0 6,19 * * *") // run every day at 6AM and 7PM
    public void runJob() {
        log.info("Running token blacklist maintainer job");
        entityManager.createNativeQuery("DELETE FROM travelin.token_blacklist WHERE EXPIRY_DATE < NOW() ")
                .executeUpdate();
    }
}
