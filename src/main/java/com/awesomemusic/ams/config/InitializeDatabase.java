package com.awesomemusic.ams.config;

import com.awesomemusic.ams.model.Slot;
import com.awesomemusic.ams.model.enumerations.SlotName;
import com.awesomemusic.ams.repository.SlotRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;

@Configuration
public class InitializeDatabase {
    private final SlotRepository slotRepository;

    @Autowired
    public InitializeDatabase(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

//    @Bean
//    InitializingBean sendDatabase() {
//        return () -> {
//            List<Slot> slots = new LinkedList<>();
//            slots.add(new Slot(SlotName.MORNING));
//            slots.add(new Slot(SlotName.AFTERNOON));
//            slots.add(new Slot(SlotName.EVENING));
//            slotRepository.saveAll(slots);
//        };
//    }
}
