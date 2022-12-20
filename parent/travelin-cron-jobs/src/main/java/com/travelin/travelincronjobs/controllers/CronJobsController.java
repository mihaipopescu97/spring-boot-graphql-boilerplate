package com.travelin.travelincronjobs.controllers;

import com.travelin.travelincronjobs.jobs.RefreshTokenMaintainerJob;
import com.travelin.travelincronjobs.jobs.TokenBlacklistMaintainerJob;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/29/2022, Thu
 **/

@Log4j2
@RestController
@RequestMapping("/cron/jobs")
public class CronJobsController {

    @Autowired
    private RefreshTokenMaintainerJob refreshTokenMaintainerJob;

    @Autowired
    private TokenBlacklistMaintainerJob tokenBlacklistMaintainerJob;

    @PostMapping("/refreshToken")
    public ResponseEntity<?> runRefreshTokenMaintainerJob() {
        try {
            refreshTokenMaintainerJob.runJob();
            return ResponseEntity.ok("Refresh token maintainer has run successfully");
        } catch (Exception exception) {
            log.error("Exception while executing refresh token maintainer job.");
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }

    @PostMapping("/tokenBlacklist")
    public ResponseEntity<?> runTokenBlacklistMaintainerJob() {
        try {
            tokenBlacklistMaintainerJob.runJob();
            return ResponseEntity.ok("Token blacklist maintainer has run successfully");
        } catch (Exception exception) {
            log.error("Exception while executing token blacklist maintainer job.");
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }
}
