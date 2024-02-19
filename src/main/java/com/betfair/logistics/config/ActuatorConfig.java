package com.betfair.logistics.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
@RequiredArgsConstructor
public class ActuatorConfig implements InfoContributor {

    private final CompanyInfo companyInfo;

    @Override
    public void contribute(Info.Builder builder) {


        LocalDate currentDate = companyInfo.getCurrentDate();

        Map<String, Object> values = new HashMap<>();
        values.put("currentDate", currentDate);
        values.put("companyProfit", companyInfo.getCompanyProfit());

        builder.withDetail("companyInfo", values);
    }
}