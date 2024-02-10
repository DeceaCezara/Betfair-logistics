package com.betfair.logistics.config;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneOffset;

@Component
@Getter
public class CompanyInfo {

   // private Long currentDate= LocalDate.of(2021, 12, 14).atStartOfDay().toEpochSecond(ZoneOffset.UTC)*1000;
    private final LocalDate currentDate= LocalDate.of(2021, 12, 14);

    public Long getCurrentDateAsLong() {
        return currentDate.atStartOfDay().toEpochSecond(ZoneOffset.UTC)*1000;
    }
}
