package spectra.recruitment.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spectra.recruitment.task.dto.*;
import spectra.recruitment.task.entity.ConsultChat;
import spectra.recruitment.task.entity.ConsultChatRoom;
import spectra.recruitment.task.entity.Customer;
import spectra.recruitment.task.repository.ConsultChatRepository;
import spectra.recruitment.task.repository.ConsultChatRoomRepository;
import spectra.recruitment.task.repository.CustomertRepository;
import spectra.recruitment.task.values.ConsultType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class ConsultService {
    private final CustomertRepository customertRepository;
    private final ConsultChatRepository consultChatRepository;
    private final ConsultChatRoomRepository consultChatRoomRepository;

    /**
     * 회원 정보 생성 및 채팅방 정보 생성
     * @param customerDto 고객 정보 DTO
     * @return
     */
    public ChatInfoDto startConsultByCustomer(CustomerDto customerDto){
        // 회원 정보 생성
        Customer customer = Customer.dtoToEntity(customerDto);
        customer.persist("system");
        customertRepository.save(customer);

        // 정보 설정
        customerDto.setSessionId(customer.getId());

        // 채팅방 생성
        ConsultChatRoom chatRoom = ConsultChatRoom.createByCustomer();
        chatRoom.persist(customerDto.getSessionId().toString());
        consultChatRoomRepository.save(chatRoom);

        // 정보 설정
        ChatInfoDto dto = new ChatInfoDto();
        dto.setConsultType(customerDto.getConsultType());
        dto.setRoomId(chatRoom.getId());
        dto.setRoomUrl("/consult/chats/" +   dto.getRoomId());
        return dto;
    }

    /**
     * 채팅방 정보 생성
     * @param userDto 사용자 정보 DTO (옵저버)
     * @param refRoomId 참조하는 채팅방의 PK
     * @return
     */
    public ChatInfoDto startConsultByObserver(UserDto userDto, Long refRoomId){
        // 채팅방 생성
        ConsultChatRoom chatRoom = ConsultChatRoom.createByObserver(refRoomId);
        chatRoom.persist(userDto.getSessionId().toString());
        consultChatRoomRepository.save(chatRoom);

        // 정보 설정
        ChatInfoDto dto = new ChatInfoDto();
        dto.setConsultType(ConsultType.OBSERVE.name());
        dto.setRoomId(chatRoom.getId());
        dto.setRoomUrl("/consult/chats/" +   dto.getRoomId());
        dto.setRefRoomId(refRoomId);
        return dto;
    }

    /**
     * 채팅 저장
     * @param dto 채팅 메시지 DTO
     */
    @Async
    public void saveChat(ChatMessageDto dto){
        ConsultChat entity = ConsultChat.dtoToEntity(dto);
        entity.persist(dto.getSessionId().toString());
        consultChatRepository.save(entity);
    }

    /**
     * 채팅방 목록 조회
     * @return
     */
    @Transactional(readOnly = true)
    public List<ConsultChatRoomDto> getRooms(){
        return consultChatRoomRepository.getChatRooms(ConsultType.COUNSEL);
    }

    /**
     * 채팅 목록 조회
     * @return
     */
    @Transactional(readOnly = true)
    public ConsultChatInfoDto getChats(Long roomId){
        ConsultChatInfoDto chatInfo = consultChatRoomRepository.getChatRoom(roomId);
        if(chatInfo != null){
            List<ConsultChat> mainChatEntitys = consultChatRepository.getChats(roomId);
            List<ChatMessageDto> mainChatDtos = mainChatEntitys.stream().map(ChatMessageDto::entityToDto).toList();
            chatInfo.setMainChats(mainChatDtos);

            if(chatInfo.getRefRoomId() != null){
                List<ConsultChat> subChatEntitys = consultChatRepository.getChats(chatInfo.getRefRoomId());
                List<ChatMessageDto> subChatDtos = subChatEntitys.stream().map(ChatMessageDto::entityToDto).toList();
                chatInfo.setSubChats(subChatDtos);
            }
            return chatInfo;
        }
        return new ConsultChatInfoDto();
    }
    
    /**
     * 상담 종료
     * @param consultEndDto 상담 종료 DTO
     * @param userDto 회원 DTO
     */
    public void endConsult(ConsultEndDto consultEndDto, UserDto userDto){
        // 메인 채팅방 종료 (고객 & 상담사)
        Optional<ConsultChatRoom> mainChatRoom = consultChatRoomRepository.findById(consultEndDto.getMainRoomId());
        mainChatRoom.ifPresent(chatRoom -> {
            chatRoom.end();
            chatRoom.update(userDto.getSessionId().toString());
        });

        // 서브 채팅방 종료 (상담사 & 옵저버)
        if(consultEndDto.getSaveYn() != null && "N".equals(consultEndDto.getSaveYn())){
            Optional<ConsultChatRoom> subChatRoom = consultChatRoomRepository.findById(consultEndDto.getSubRoomId());
            subChatRoom.ifPresent(chatRoom -> {
                chatRoom.end();
                chatRoom.update(userDto.getSessionId().toString());
            });
        }
    }
}
