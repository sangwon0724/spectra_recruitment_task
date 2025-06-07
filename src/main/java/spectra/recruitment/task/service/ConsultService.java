package spectra.recruitment.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spectra.recruitment.task.dto.CustomerDto;
import spectra.recruitment.task.entity.ConsultChatRoom;
import spectra.recruitment.task.entity.Customer;
import spectra.recruitment.task.repository.ConsultChatRoomRepository;
import spectra.recruitment.task.repository.CustomertRepository;

@RequiredArgsConstructor
@Service
public class ConsultService {
    private final CustomertRepository customertRepository;
    private final ConsultChatRoomRepository consultChatRoomRepository;

    /**
     * 회원 정보 생성 및
     * @param customerDto 고객 정보 DTO
     * @return
     */
    public void startConsultByCustomer(CustomerDto customerDto){
        // 회원 정보 생성
        Customer customer = Customer.dtoToEntity(customerDto);
        customer.persist("system");
        customertRepository.save(customer);

        // 채팅방 생성
        ConsultChatRoom chatRoom = ConsultChatRoom.createByCustomer();
        consultChatRoomRepository.save(chatRoom);
        
        // 정보 설정
        customerDto.setSessionId(customer.getId());
        customerDto.setRoomId(chatRoom.getId());
    }
}
