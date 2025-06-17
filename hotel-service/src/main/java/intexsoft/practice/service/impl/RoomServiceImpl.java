package intexsoft.practice.service.impl;

import intexsoft.practice.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomServiceImpl {
    private final RoomRepository roomRepository;

}
